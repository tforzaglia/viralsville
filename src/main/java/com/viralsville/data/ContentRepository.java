package com.viralsville.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.viralsville.model.Content;

@Repository
public class ContentRepository {

    @Autowired
    protected JdbcTemplate jdbc;

    public Content getContent( long id ) {
        return this.jdbc.queryForObject( "SELECT * FROM content WHERE id=?", contentMapper, id );
    }

    public List<Content> getContents( long[] ids ) {
        String inIds = StringUtils.arrayToCommaDelimitedString( ObjectUtils.toObjectArray( ids ) );
        return this.jdbc.query( "SELECT * FROM content WHERE id IN (" + inIds + ")", contentMapper );
    }

    public void createContent( String title ) {
        String sql = "INSERT INTO content (title) VALUES(?)";
        this.jdbc.update( sql, new Object[] {
            title
        } );
    }

    private static final RowMapper<Content> contentMapper = new RowMapper<Content>() {
        @Override
        public Content mapRow( ResultSet rs, int rowNum ) throws SQLException {
            Content content = new Content( rs.getString( "title" ) );
            content.setId( rs.getLong( "id" ) );
            content.setTitle( rs.getString( "title" ) );
            return content;
        }
    };
}
