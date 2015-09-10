package com.viralsville.model.vo;

public class ContentVO {

    private String title;
    private String externalLink;
    private String contentType;
    private String[] tagNames;

    public String getTitle() {
        return this.title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getExternalLink() {
        return this.externalLink;
    }

    public void setExternalLink( String externalLink ) {
        this.externalLink = externalLink;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType( String contentType ) {
        this.contentType = contentType;
    }

    public String[] getTagNames() {
        return this.tagNames;
    }

    public void setTagNames( String[] tagNames ) {
        this.tagNames = tagNames;
    }
}
