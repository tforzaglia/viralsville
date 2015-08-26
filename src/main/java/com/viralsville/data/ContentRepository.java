package com.viralsville.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.viralsville.model.Content;
import com.viralsville.model.ContentType;

@Repository
public class ContentRepository {

    private static final int CONTENT_PER_PAGE = 5;

    @Autowired
    protected JdbcTemplate jdbc;

    public Content getContent( long id ) {
        return this.jdbc.queryForObject( "SELECT * FROM content WHERE id=?", contentMapper, id );
    }

    public List<Content> getContents( long[] ids ) {
        String inIds = StringUtils.arrayToCommaDelimitedString( ObjectUtils.toObjectArray( ids ) );
        return this.jdbc.query( "SELECT * FROM content WHERE id IN (" + inIds + ")", contentMapper );
    }

    public List<Content> getContentListByPageNumber( int pageNumber ) {
        return this.jdbc.query( "SELECT * FROM content LIMIT ?,?", contentMapper, CONTENT_PER_PAGE * ( pageNumber - 1 ), CONTENT_PER_PAGE );
    }

    public void createContent( Content content ) {
        String sql = "INSERT INTO content (title, external_link, content_type, created_date, views) VALUES(?,?,?,?,?)";
        this.jdbc.update( sql, new Object[] {
                content.getTitle(),
                content.getExternalLink(),
                content.getContentType().name(),
                content.getCreatedDate(),
                content.getViews()
        } );
    }

    private static final RowMapper<Content> contentMapper = new RowMapper<Content>() {
        @Override
        public Content mapRow( ResultSet rs, int rowNum ) throws SQLException {
            Content content = new Content();
            content.setId( rs.getLong( "id" ) );
            content.setTitle( rs.getString( "title" ) );
            content.setExternalLink( rs.getString( "external_link" ) );
            content.setContentType( ContentType.valueOf( rs.getString( "content_type" ) ) );
            content.setCreatedDate( new Date( rs.getTimestamp( "created_date" ).getTime() ) );
            content.setViews( rs.getInt( "views" ) );

            return content;
        }
    };
}
