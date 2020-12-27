package com.codecta.qoq.model;

import javax.persistence.*;

@Entity
@Table(schema = "quarkusorb", name = "POTION")
public class Potion extends ModelObject{
    @SequenceGenerator(
            name = "PotionSeq",
            sequenceName = "POTION_SEQ",
            schema = "quarkusorb",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PotionSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }
}
