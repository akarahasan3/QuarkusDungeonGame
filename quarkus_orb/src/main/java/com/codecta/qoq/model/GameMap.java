package com.codecta.qoq.model;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "map", fetch = FetchType.LAZY)
    private List<Dungeon> dungeons;

    @OneToOne
    private Player player;

    @Override
    public Integer getId() {
        return this.id;
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
