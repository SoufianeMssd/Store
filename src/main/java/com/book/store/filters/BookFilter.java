package com.book.store.filters;

import com.book.store.common.filters.Filter;
import com.book.store.models.Category;

public class BookFilter extends Filter {
    private String name;
    private String writer;
    private Category category;

    public BookFilter() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
