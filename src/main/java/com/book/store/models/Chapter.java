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

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public Book getBook() {
        return this.book;
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
        } else {
            Chapter other = (Chapter)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getId() != other.getId()) {
                return false;
            } else if (this.getStart() != other.getStart()) {
                return false;
            } else if (this.getEnd() != other.getEnd()) {
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

                Object this$book = this.getBook();
                Object other$book = other.getBook();
                if (this$book == null) {
                    if (other$book != null) {
                        return false;
                    }
                } else if (!this$book.equals(other$book)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Chapter;
    }

    public int hashCode() {
        return Objects.hash(name, id);
    }

    public String toString() {
        return "Chapter(id=" + this.getId() + ", name=" + this.getName() + ", start=" + this.getStart() + ", end=" + this.getEnd() + ", book=" + this.getBook() + ")";
    }

    public Chapter() {
    }
}
