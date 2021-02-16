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
import java.util.Comparator;
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
            int monsterNumber = (int) (Math.random()*(monsterNames.size()));
            monster.setName(monsterNames.get(monsterNumber));
            monster.setMonsterImage("monster"+monsterNumber+".jpg");
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
    private MonsterDto finalBoss(){
        ModelMapper modelMapper = new ModelMapper();
        Monster monster=new Monster();
        Weapon weapon = new Weapon();
        weapon.setName("Orb of Quarkus");
        weapon.setDamage(9);
        weapon = weaponRepository.add(weapon);
        monster.setName("Quarken");
        monster.setMonsterImage("monster10.jpg");
        monster.setHealth(40);
        monster.setDamage(3);
        monster.setWeapon(weapon);
        monster = monsterRepository.add(monster);
        return modelMapper.map(monster, MonsterDto.class);
    }

    @Override
    public GameMapDto createMap(){
        ModelMapper mapper = new ModelMapper();
        GameMap map = new GameMap();
        Player player = new Player();
        player.setDamage(5);
        player.setHealing_potion(5);
        player.setHealth(30);
        player.setWeapon(null);
        player.setName("Legolas");
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
        Integer counter = 1;
        Dungeon dungeon = new Dungeon();
        Monster boss=monsterRepository.findById(finalBoss().getId());
        dungeon.setMap(map);
        dungeon.setMonster(boss);
        dungeon.setWeapon(null);
        dungeon.setHealing_potion(null);
        dungeon = dungeonRepository.add(dungeon);
        boss.setDungeon(dungeon);
        monsterRepository.save(boss);
        DungeonDto dungeonDto = mapper.map(dungeon, DungeonDto.class);
        dungeonDtoList.add(dungeonDto);
        for (DungeonDto dungeondto:
                dungeonDtoList) {
            Dungeon dungeon1 = dungeonRepository.findById(dungeondto.getId());
            dungeon1.setDungeonOrder(counter++);
            dungeon1 = dungeonRepository.save(dungeon1);
            map.getDungeons().add(dungeon1);
        }
        map = gameMapRepository.add(map);
        return mapper.map(map, GameMapDto.class);
    }

    @Override
    public List<DungeonDto> getAllDungeons() {
        List<Dungeon> dungeonList = dungeonRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        List<DungeonDto> dungeonDtoList = new ArrayList<>();
        for (Dungeon dungeon1 :
                dungeonList) {
            DungeonDto dungeonDto = mapper.map(dungeon1, DungeonDto.class);
            dungeonDtoList.add(dungeonDto);
        }
        return dungeonDtoList;
    }

    @Override
    public DungeonDto getNextDungeon(GameMapDto gameMapDto) {
        GameMap gameMap = gameMapRepository.findById(gameMapDto.getId());
        if (gameMap == null) return null;
        Collections.sort(gameMap.getDungeons(), new Comparator<Dungeon>() {
            public int compare(Dungeon s1, Dungeon s2) {
                return s1.getDungeonOrder().compareTo(s2.getDungeonOrder());
            }
        });
        ModelMapper modelMapper = new ModelMapper();
        Dungeon dungeon = dungeonRepository.findById(gameMap.getDungeons().get(0).getId());
        DungeonDto dungeonDto = modelMapper.map(dungeon, DungeonDto.class);
        if(dungeon.getId()%10 == 0 && dungeon.getMonster().getHealth()<=0){
            DungeonDto dungeonDto1 = modelMapper.map(dungeon, DungeonDto.class);
            dungeonDto1.setGameOver("Victory");
            return dungeonDto1;
        }
        Player player = playerRepository.findById(gameMap.getPlayer().getId());
        if(player.getHealth()<=0){
            DungeonDto dungeonDto1 = modelMapper.map(dungeon, DungeonDto.class);
            dungeonDto1.setGameOver("Defeat");
            return dungeonDto1;
        }
        if(dungeon.getMonster() != null)
            if(dungeon.getMonster().getHealth()>0){
                dungeonDto.setMonsterName(dungeon.getMonster().getName());
                dungeonDto.setMonsterImage(dungeon.getMonster().getMonsterImage());
                return dungeonDto;
            }
        if(dungeon.getWeapon() != null){
            Weapon weapon = weaponRepository.findById(dungeon.getWeapon().getId());
            dungeon.setWeapon(null);
            player.setWeapon(weapon);
            player.setDamage(player.getDamage() + weapon.getDamage());
            player = playerRepository.save(player);
            dungeonDto.setWeaponName(weapon.getName());
        }
        if(dungeon.getHealing_potion() != null && dungeon.getHealing_potion()>0){
            player = playerRepository.findById(gameMap.getPlayer().getId());
            player.setHealing_potion(player.getHealing_potion() + dungeon.getHealing_potion());
            player = playerRepository.save(player);
        }
        gameMap.getDungeons().remove(0);
        gameMapRepository.save(gameMap);
        dungeonRepository.delete(dungeon);
        return dungeonDto;
    }

    @Override
    public GameMapDto getMapById(Integer id) {
        GameMap mapa = gameMapRepository.findById(id);
        Collections.sort(mapa.getDungeons(), new Comparator<Dungeon>() {
            public int compare(Dungeon s1, Dungeon s2) {
                return s1.getDungeonOrder().compareTo(s2.getDungeonOrder());
            }
        });
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
        Player player = playerRepository.findById(playerDto.getId());
        if(player == null || player.getHealing_potion() == null) return null;
        player.setHealth(player.getHealth()+player.getHealing_potion());
        player.setHealing_potion(0);
        player = playerRepository.save(player);
        return modelMapper.map(player, PlayerDto.class);
    }

    @Override
    public MonsterDto getMonsterById(Integer monsterId) {
        ModelMapper modelMapper = new ModelMapper();
        Monster monster = monsterRepository.findById(monsterId);
        if(monster == null) return null;
        return modelMapper.map(monster, MonsterDto.class);
    }

    @Override
    public DungeonDto monsterDefeated(Integer id) {
        Monster monster = monsterRepository.findById(id);
        Dungeon dungeon = dungeonRepository.findById(monster.getDungeon().getId());
        monsterRepository.delete(monster);
        if(dungeon == null) return null;
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dungeon, DungeonDto.class);
    }

    @Override
    public MonsterDto monsterFight(Integer id, Integer idPlayer) {
        Monster monster = monsterRepository.findById(id);
        Player player = playerRepository.findById(idPlayer);
        if(monster==null || player==null || monster.getHealth()<=0) {
            return null;
        }

        monster.setHealth(monster.getHealth() - player.getDamage()*(int)(Math.ceil((Math.random()*6+1)/5)));
        monster = monsterRepository.save(monster);
        if(monster.getHealth()<=0){
            ModelMapper mapper = new ModelMapper();
            MonsterDto monsterDto = mapper.map(monster, MonsterDto.class);
            if(monster.getName().contains("Quarken")){
                monsterDto.setPlayerHealth(player.getHealth());
                player.setWeapon(monster.getWeapon());
                monster.setWeapon(null);
                monsterRepository.save(monster);
                playerRepository.save(player);
                return monsterDto;
            }
            getNextDungeon(getMapById(getDungeonById(monsterDto.getDungeonId()).getMapId()));
            monsterDto.setPlayerHealth(player.getHealth());
            return monsterDto;
        }

        player.setHealth(player.getHealth() - monster.getDamage()*(int)(Math.ceil((Math.random()*6+1)/5)));
        player = playerRepository.save(player);
        if(player.getHealth()<=0) {
            ModelMapper mapper = new ModelMapper();
            MonsterDto monsterDto = mapper.map(monster, MonsterDto.class);
            monsterDto.setPlayerHealth(player.getHealth());
            monsterDto.setName(monsterDto.getName()+" defeated you, good luck on your next adventure!");
            return monsterDto;
        }

        ModelMapper mapper = new ModelMapper();
        MonsterDto monsterDto = mapper.map(monster, MonsterDto.class);
        monsterDto.setPlayerHealth(player.getHealth());
        return monsterDto;
    }

    @Override
    public DungeonDto getDungeonById(Integer id) {
        Dungeon dungeon = dungeonRepository.findById(id);
        if(dungeon == null || dungeon.getMap() == null) return null;
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dungeon, DungeonDto.class);
    }

    @Override
    public PlayerDto getPlayerById(Integer playerId) {
        Player player = playerRepository.findById(playerId);
        if(player == null || player.getMap()==null) return null;
        ModelMapper mapper = new ModelMapper();
        return mapper.map(player, PlayerDto.class);
    }

    @Override
    public DungeonDto flee(Integer id) {
        GameMap gameMap = gameMapRepository.findById(id);
        if(gameMap.getDungeons().size() < 2) return null;

        Dungeon dungeon = dungeonRepository.findById(gameMap.getDungeons().get(1).getId());
        Dungeon dungeonTemp = dungeonRepository.findById(gameMap.getDungeons().get(0).getId());

        gameMap.getDungeons().remove(0);
        gameMapRepository.save(gameMap);
        dungeonRepository.delete(dungeonTemp);
        Player player = playerRepository.findById(gameMap.getPlayer().getId());
        player.setHealth(player.getHealth()-2);
        playerRepository.save(player);
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dungeon, DungeonDto.class);
    }
}