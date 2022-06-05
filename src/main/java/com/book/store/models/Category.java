package com.book.store.models;

public enum Category {
    // @formatter:off
    SCIENCE("science"),
    ADVENTURE("adventure"),
    POLITICS("politics");
    // @formatter:on

    private final String label;

    Category(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Category{" + "label='" + label + '\'' + '}';
    };
}
