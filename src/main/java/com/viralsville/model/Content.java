package com.viralsville.model;

public class Content {

    private long id;
    private String title;

    public Content() {
    };

    public Content( String title ) {
        this.title = title;
    }

    public long getId() {
        return this.id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }
}
