package com.viralsville.model;

import java.util.Date;
import java.util.List;

public class Content {

    private long id;
    private String title;
    private String externalLink;
    private ContentType contentType;
    private Date createdDate;
    private long views;
    private List<Tag> tags;

    public Content() {
    };

    public Content( String title, String externalLink, ContentType contentType, Date createdDate, int views, List<Tag> tags ) {
        this.title = title;
        this.externalLink = externalLink;
        this.contentType = contentType;
        this.createdDate = createdDate;
        this.views = views;
        this.tags = tags;
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

    public String getExternalLink() {
        return this.externalLink;
    }

    public void setExternalLink( String externalLink ) {
        this.externalLink = externalLink;
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    public void setContentType( ContentType contentType ) {
        this.contentType = contentType;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate( Date createdDate ) {
        this.createdDate = createdDate;
    }

    public long getViews() {
        return this.views;
    }

    public void setViews( long views ) {
        this.views = views;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags( List<Tag> tags ) {
        this.tags = tags;
    }
}
