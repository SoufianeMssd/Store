package com.book.store.dto;

import com.book.store.models.Category;
import com.book.store.models.Chapter;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;

public class BookDto {
    private long id;
    private @NotBlank String name;
    private @NotBlank String writer;
    private boolean isForSale;
    private Category category;
    private int nbrChapters;
    private List<Chapter> chapters = new ArrayList();

    public BookDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNbrChapters() {
        return nbrChapters;
    }

    public void setNbrChapters(int nbrChapters) {
        this.nbrChapters = nbrChapters;
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

    public Boolean getIsForSale() {
        return isForSale;
    }

    public void setIsForSale(Boolean isForSale) {
        this.isForSale = isForSale;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String toString() {
        return "{ id='" + getId() + "'" + ", name='" + getName() + "'" + ", writer='" + getWriter() + "'" + ", chapters='" + getChapters() + "'" + "}";
    }
}
