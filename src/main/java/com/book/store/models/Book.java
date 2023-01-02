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

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getWriter() {
        return this.writer;
    }

    public Category getCategory() {
        return this.category;
    }

    public Boolean getIsForSale() {
        return this.isForSale;
    }

    public List<Chapter> getChapters() {
        return this.chapters;
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
        } else {
            Book other = (Book)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getId() != other.getId()) {
                return false;
            } else {
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$writer = this.getWriter();
                Object other$writer = other.getWriter();
                if (this$writer == null) {
                    if (other$writer != null) {
                        return false;
                    }
                } else if (!this$writer.equals(other$writer)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Book;
    }

    public int hashCode() {
       return Objects.hash(name, writer); 
    }

    public String toString() {
        return "Book(id=" + this.getId() + ", name=" + this.getName() + ", writer=" + this.getWriter() + ")";
    }

    public Book() {
    }
}
