package com.codecta.qoq.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "quarkusorb", name = "GAMEMAP")
public class GameMap extends ModelObject{
    @SequenceGenerator(
            name = "GameMapSeq",
            sequenceName = "GAMEMAP_SEQ",
            schema = "quarkusorb",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GameMapSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "story", nullable = false)
    private String story = "When the hero is out wandering the cave or is otherwise all alone, a dying man bumps into him, hands him a scribbled map, says a few words, and dies. The map shows a treasure of some kind but is otherwise scribbled. Good luck adventurer!";

    @OneToMany(mappedBy = "map", fetch = FetchType.LAZY)
    private List<Dungeon> dungeons = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(schema = "quarkusorb", name = "map_player",
            joinColumns =
                    { @JoinColumn(name = "map_id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "player_id") })
    private Player player;

    @Override
    public Integer getId() {
        return this.id;
    }

    public String getStory() {
        return story;
    }

    public List<Dungeon> getDungeons() {
        return dungeons;
    }

    public void setDungeons(List<Dungeon> dungeons) {
        this.dungeons = dungeons;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
