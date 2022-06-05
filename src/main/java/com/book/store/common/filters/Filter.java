package com.book.store.common.filters;

public class Filter {
    private int page = 0;
    private int size = 10;


    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public String toString() {
        return "{" +
            " page='" + getPage() + "'" +
            ", size='" + getSize() + "'" +
            "}";
    }

}
