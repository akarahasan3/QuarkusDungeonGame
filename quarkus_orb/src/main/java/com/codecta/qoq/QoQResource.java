package com.codecta.qoq;

import com.codecta.qoq.model.Player;
import com.codecta.qoq.services.QoQServices;
import com.codecta.qoq.services.dto.DungeonDto;
import com.codecta.qoq.services.dto.GameMapDto;
import com.codecta.qoq.services.dto.MonsterDto;
import com.codecta.qoq.services.dto.PlayerDto;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/game")
public class QoQResource {


    @Inject
    QoQServices qoQServices;

    @GET
    @Path("/hi")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(){
        return Response.ok().build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response start(){
        GameMapDto game = qoQServices.createMap();
        if(game != null) {
            return Response.ok(game).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Path("/{id}/move")
    @Produces({MediaType.APPLICATION_JSON})
    public Response move(@PathParam("id") Integer id){
        GameMapDto gameMapDto = qoQServices.getMapById(id);
        PlayerDto player = qoQServices.findPlayerById(gameMapDto.getPlayerId());
        if(gameMapDto == null || player == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        if(player.getHealth()<=0) return Response.ok().entity("Game Over, try again!").build();

        DungeonDto dungeonDto = qoQServices.getNextDungeon(gameMapDto);

        if(dungeonDto == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(dungeonDto).build();
    }

    @POST
    @Path("{id}/heal")
    @Produces({MediaType.APPLICATION_JSON})
    public Response heal(@PathParam("id") Integer id){
        PlayerDto playerDto = qoQServices.findPlayerById(id);
        if(playerDto.getHealth()<=0) return Response.ok().entity("Game Over, try again!").build();
        if(playerDto == null) return Response.status(404).entity("Player not found").build();
        GameMapDto gameMapDto = qoQServices.getMapById(playerDto.getMapId());
        if(gameMapDto == null) return Response.status(404).entity("Map not found").build();
        if(playerDto.getHealing_potion() == 0) return Response.status(200).entity("Can't heal, no potions!").build();
        playerDto = qoQServices.heal(playerDto);
        if(playerDto == null) return Response.status(Response.Status.BAD_REQUEST).entity("Error while healing").build();
        return Response.ok(playerDto).build();
    }

    @POST
    @Path("{id}/flee")
    @Produces({MediaType.APPLICATION_JSON})
    public Response flee(@PathParam("id") Integer id){
        GameMapDto gameMapDto = qoQServices.getMapById(id);
        PlayerDto playerDto=qoQServices.findPlayerById(gameMapDto.getPlayerId());
        if(gameMapDto == null || playerDto==null)
            return Response.status(Response.Status.NOT_FOUND).build();
        if(playerDto.getHealth()<=0) return Response.ok().entity("Game Over, try again!").build();
        DungeonDto dungeonDto = qoQServices.flee(gameMapDto.getId());
        if(dungeonDto == null)
            return Response.status(Response.Status.FORBIDDEN).build();
        return Response.ok(dungeonDto).build();
    }

    @POST
    @Path("{id}/fight")
    @Produces({MediaType.APPLICATION_JSON})
    public Response fight(@PathParam("id") Integer id){
        GameMapDto gameMapDto = qoQServices.getMapById(id);
        if(gameMapDto == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        DungeonDto dungeonDto = qoQServices.getNextDungeon(gameMapDto);
        MonsterDto monsterDto = qoQServices.getMonsterById(dungeonDto.getMonsterId());
        PlayerDto playerDto = qoQServices.getPlayerById(gameMapDto.getPlayerId());
        if(playerDto.getHealth()<=0) return Response.ok().entity("Game Over, try again!").build();
        if(monsterDto != null && monsterDto.getHealth()>0){
            monsterDto = qoQServices.monsterFight(monsterDto.getId(), playerDto.getId());
            return Response.ok(monsterDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
