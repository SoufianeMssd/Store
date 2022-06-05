package com.book.store.dto;

import java.util.ArrayList;
import java.util.List;

import com.book.store.models.Category;
import com.book.store.models.Chapter;



public class BookDto {
    private long id;
    private String name;
    private String writer;
    private Boolean isForSale = false;
    private Category category;
    private int nbrChapters;
    private List<Chapter> chapters = new ArrayList<Chapter>();


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNbrChapters() {
        return this.nbrChapters;
    }

    public void setNbrChapters(int nbrChapters) {
        this.nbrChapters = nbrChapters;
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

    public Boolean getIsForSale() {
        return this.isForSale;
    }

    public void setIsForSale(Boolean isForSale) {
        this.isForSale = isForSale;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
