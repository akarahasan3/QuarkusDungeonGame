package com.codecta.qoq.services.implementation;

import com.codecta.qoq.model.*;
import com.codecta.qoq.repository.*;
import com.codecta.qoq.services.QoQServices;
import com.codecta.qoq.services.dto.DungeonDto;
import com.codecta.qoq.services.dto.GameMapDto;
import com.codecta.qoq.services.dto.MonsterDto;
import com.codecta.qoq.services.dto.WeaponDto;
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
        System.out.println(brojac);
        GameMapDto map = new GameMapDto();
        List<DungeonDto> dungeonDtoList = new ArrayList<>(10);
        brojac -= ((int)(Math.random()*(brojac+1)));
        System.out.println(brojac);
        List<MonsterDto> monsterDtoList = generateMonsters(brojac);
        brojac -= ((int)(Math.random()*(brojac+1)));
        System.out.println(brojac);
        List<WeaponDto> weaponDtoList = generateWeapons(brojac);
        for (int i = 0; i<monsterDtoList.size(); i++) {
            ModelMapper modelMapper = new ModelMapper();
            Dungeon dungeon = new Dungeon();
            dungeon.setMap(modelMapper.map(map, GameMap.class));
            dungeon.setMonster(modelMapper.map(monsterDtoList.get(i), Monster.class));
            dungeon.setWeapon(null);
            dungeon.setHealing_potion(null);
            dungeon = dungeonRepository.add(dungeon);
            dungeonDtoList.add(modelMapper.map(dungeon, DungeonDto.class));
        }
        for (int i = 0; i<weaponDtoList.size(); i++) {
            ModelMapper modelMapper = new ModelMapper();
            Dungeon dungeon = new Dungeon();
            dungeon.setMap(modelMapper.map(map, GameMap.class));
            dungeon.setMonster(null);
            dungeon.setWeapon(modelMapper.map(weaponDtoList.get(i), Weapon.class));
            dungeon.setHealing_potion(null);
            dungeon = dungeonRepository.add(dungeon);
            dungeonDtoList.add(modelMapper.map(dungeon, DungeonDto.class));
        }
        brojac -= ((int)(Math.random()*(brojac)));
        System.out.println(brojac);
        for (int i = 0; i<brojac; i++) {
            ModelMapper modelMapper = new ModelMapper();
            Dungeon dungeon = new Dungeon();
            dungeon.setMap(modelMapper.map(map, GameMap.class));
            dungeon.setMonster(null);
            dungeon.setWeapon(null);
            dungeon.setHealing_potion((int)(Math.random()*(5)+1));
            dungeon = dungeonRepository.add(dungeon);
            dungeonDtoList.add(modelMapper.map(dungeon, DungeonDto.class));
        }
        Collections.shuffle(dungeonDtoList);
        Player player = new Player();
        player.setDamage(3);
        player.setHealing_potion(0);
        player.setHealth(20);
        player.setWeapon(null);
        player.setName("Ajdin");
        map.setPlayerId(1);
        return map;
    }
}
