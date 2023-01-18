package com.book.store.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Chapter {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long id;
    private String name;
    private int start;
    private int end;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "book_id"
    )
    private Book book;

    public Chapter() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public Book getBook() {
        return book;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Chapter)) {
            return false;
        }
        Chapter chapter = (Chapter) o;
        return Objects.equals(name, chapter.getName());
    }

    public int hashCode() {
        return Objects.hash(name, id);
    }

    public String toString() {
        return "Chapter(id=" + getId() + ", name=" + getName() +
     ", start=" + getStart() + ", end=" + getEnd() + ", book=" + getBook() + ")";
    }
}
