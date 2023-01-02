package com.book.store.models;

public enum Category {
    SCIENCE("science"),
    ADVENTURE("adventure"),
    POLITICS("politics");

    private final String label;

    private Category(String label) {
        this.label = label;
    }

    public String toString() {
        return "Category{label='" + this.label + '\'' + '}';
    }
}
