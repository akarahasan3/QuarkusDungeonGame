package com.library.model;

import javax.persistence.*;

@Entity
@Table(schema = "library", name = "Book")
public class Book extends ModelObject {
    @SequenceGenerator(
            name = "BookSeq",
            sequenceName = "BOOK_SEQ",
            schema = "library",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BookSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    private String name;
    private String writer;
    private String cover;
    private Integer year;
    private String genre;


    @Override
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
