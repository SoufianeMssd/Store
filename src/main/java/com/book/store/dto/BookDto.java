package com.book.store.dto;

import java.util.ArrayList;
import java.util.List;

import com.book.store.models.Chapter;



public class BookDto {
    private long id;
    private String name;
    private String writer;
    private List<Chapter> chapters = new ArrayList<Chapter>();


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return this.writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public List<Chapter> getChapters() {
        return this.chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", writer='" + getWriter() + "'" +
            ", chapters='" + getChapters() + "'" +
            "}";
    }

}
