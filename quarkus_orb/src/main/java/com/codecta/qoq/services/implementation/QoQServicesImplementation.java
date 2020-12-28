package com.codecta.qoq.services.implementation;

import com.codecta.qoq.model.*;
import com.codecta.qoq.repository.*;
import com.codecta.qoq.services.QoQServices;
import com.codecta.qoq.services.dto.*;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Transactional
public class QoQServicesImplementation implements QoQServices {
    @Inject
    DungeonRepository dungeonRepository;

    @Inject
    GameMapRepository gameMapRepository;

    @Inject
    MonsterRepository monsterRepository;

    @Inject
    PlayerRepository playerRepository;

    @Inject
    WeaponRepository weaponRepository;

    private List<MonsterDto> generateMonsters(Integer noOfMonsters){
        List<MonsterDto> monsterList = new ArrayList<>();
        List<String> monsterNames = new ArrayList<>();
        monsterNames.add("Grimesnare");
        monsterNames.add("Auracrackle");
        monsterNames.add("The Jagged Thing");
        monsterNames.add("Netherwing");
        monsterNames.add("The Dirty Revenant");
        monsterNames.add("The Ancient Keeper");
        monsterNames.add("The Titanic Berserker Pig");
        monsterNames.add("Gloomhound");
        monsterNames.add("The Twin Witch");
        for(int i = 0; i < noOfMonsters; i++){
            ModelMapper modelMapper = new ModelMapper();
            Monster monster=new Monster();
            monster.setName(monsterNames.get((int) (Math.random()*(monsterNames.size()))));
            monster.setHealth((int) (Math.random()*(11)+10));
            monster.setDamage((int) (Math.random()*(5)+1));
            monster.setWeapon(null);
            monster = monsterRepository.add(monster);
            monsterList.add(modelMapper.map(monster, MonsterDto.class));
        }
        return monsterList;
    }
    private List<WeaponDto> generateWeapons(Integer noOfWeapons){
        List<WeaponDto> weaponDtoList = new ArrayList<>();
        List<String> weaponNames = new ArrayList<>();
        weaponNames.add("Worldbreaker");
        weaponNames.add("Purifier");
        weaponNames.add("Soulless Katana");
        weaponNames.add("Sting, Carver of Dismay");
        weaponNames.add("Slice of Life, Legacy of the Champion");
        weaponNames.add("Blight's Plight");
        weaponNames.add("Soulcursed Gold Warblade");
        weaponNames.add("Silver Saber, Ender of the Wind");
        weaponNames.add("Darkheart");
        for(int i = 0; i < noOfWeapons; i++){
            ModelMapper modelMapper = new ModelMapper();
            Weapon weapon = new Weapon();
            weapon.setName(weaponNames.get((int) (Math.random()*(weaponNames.size()))));
            weapon.setDamage((int)(Math.random()*5+1));
            weapon = weaponRepository.add(weapon);
            weaponDtoList.add(modelMapper.map(weapon, WeaponDto.class));
        }
        return weaponDtoList;
    }

    @Override
    public GameMapDto createMap(){
        Integer brojac = 9;
        ModelMapper mapper = new ModelMapper();
        System.out.println(brojac);
        GameMap map = new GameMap();
        Player player = new Player();
        player.setDamage(3);
        player.setHealing_potion(0);
        player.setHealth(20);
        player.setWeapon(null);
        player.setName("Ajdin");
        player = playerRepository.add(player);
        map.setPlayer(player);
        List<DungeonDto> dungeonDtoList = new ArrayList<>(10);
        List<WeaponDto> weaponDtoList = generateWeapons(3);
        List<MonsterDto> monsterDtoList = generateMonsters(4);
        for (int i = 0; i<monsterDtoList.size(); i++) {
            ModelMapper modelMapper = new ModelMapper();
            Dungeon dungeon = new Dungeon();
            dungeon.setMap(map);
            dungeon.setMonster(monsterRepository.findById(monsterDtoList.get(i).getId()));
            dungeon.setWeapon(null);
            dungeon.setHealing_potion(null);
            dungeon = dungeonRepository.add(dungeon);
            Monster monster = monsterRepository.findById(monsterDtoList.get(i).getId());
            monster.setDungeon(dungeon);
            monsterRepository.save(monster);
            dungeonDtoList.add(modelMapper.map(dungeon, DungeonDto.class));
        }
        for (int i = 0; i<weaponDtoList.size(); i++) {
            ModelMapper modelMapper = new ModelMapper();
            Dungeon dungeon = new Dungeon();
            dungeon.setMap(map);
            dungeon.setMonster(null);
            dungeon.setWeapon(weaponRepository.findById(weaponDtoList.get(i).getId()));
            dungeon.setHealing_potion(null);
            dungeon = dungeonRepository.add(dungeon);
            Weapon weapon = weaponRepository.findById(weaponDtoList.get(i).getId());
            weapon.setDungeon(dungeon);
            weaponRepository.save(weapon);
            dungeonDtoList.add(modelMapper.map(dungeon, DungeonDto.class));
        }
        for (int i = 0; i<2; i++) {
            ModelMapper modelMapper = new ModelMapper();
            Dungeon dungeon = new Dungeon();
            dungeon.setMap(map);
            dungeon.setMonster(null);
            dungeon.setWeapon(null);
            dungeon.setHealing_potion((int)(Math.random()*(5)+1));
            dungeon = dungeonRepository.add(dungeon);
            dungeonDtoList.add(modelMapper.map(dungeon, DungeonDto.class));
        }
        Collections.shuffle(dungeonDtoList);
        //ovdje final boss treba dodati
        for (DungeonDto dungeon:
             dungeonDtoList) {
            Dungeon dungeon1 = mapper.map(dungeon, Dungeon.class);
            map.getDungeons().add(dungeon1);
        }
        map = gameMapRepository.add(map);
        return mapper.map(map, GameMapDto.class);
    }

    @Override
    public DungeonDto getNextDungeon(GameMapDto gameMapDto) {
        if(gameMapDto == null) return null;
        ModelMapper modelMapper = new ModelMapper();
        Dungeon dungeon = dungeonRepository.findById(gameMapDto.getDungeons().get(0).getId());
        GameMap gameMap = gameMapRepository.findById(gameMapDto.getId());
        gameMap.getDungeons().remove(0);
        System.out.println(dungeon.getId());
        System.out.println(dungeon.getMonster());
        gameMapRepository.save(gameMap);
        dungeonRepository.delete(dungeon);
        return modelMapper.map(dungeon, DungeonDto.class);
    }

    @Override
    public GameMapDto getMapById(Integer id) {
        GameMap mapa = gameMapRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        if (mapa != null)
            return modelMapper.map(mapa, GameMapDto.class);
        return null;
    }

    @Override
    public PlayerDto findPlayerById(Integer id) {
        Player player = playerRepository.findById(id);
        if(player == null) return null;
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(player, PlayerDto.class);
    }

    @Override
    public PlayerDto heal(PlayerDto playerDto) {
        ModelMapper modelMapper = new ModelMapper();
        Player player = modelMapper.map(findPlayerById(playerDto.getId()), Player.class);
        if(player == null || player.getHealing_potion() == null) return null;
        player.setHealth(player.getHealth()+player.getHealing_potion());
        player.setHealing_potion(null);
        player = playerRepository.save(player);
        return modelMapper.map(player, PlayerDto.class);
    }
}
