package com.book.store.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Book {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long id;
    @Column(
        name = "name"
    )
    private String name;
    @Column(
        name = "writer"
    )
    private String writer;
    @Enumerated(EnumType.STRING)
    @Column(
        name = "category"
    )
    private Category category;
    @Column(
        name = "is_for_sale"
    )
    private Boolean isForSale;
    @OneToMany(
        mappedBy = "book",
        fetch = FetchType.LAZY,
        cascade = {CascadeType.PERSIST}
    )
    private List<Chapter> chapters = new ArrayList();

    public Book() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWriter() {
        return writer;
    }

    public Category getCategory() {
        return category;
    }

    public Boolean getIsForSale() {
        return isForSale;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setIsForSale(Boolean isForSale) {
        this.isForSale = isForSale;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(name, book.getName());
    }

    public int hashCode() {
       return Objects.hash(name, writer); 
    }

    public String toString() {
        return "Book(id=" + getId() + ", name=" + getName() + ", writer=" + getWriter() + ")";
    }
}
