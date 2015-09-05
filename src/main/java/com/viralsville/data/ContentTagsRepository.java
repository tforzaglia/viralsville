package com.viralsville.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ContentTagsRepository {

    @Autowired
    protected JdbcTemplate jdbc;

    public List<Long> getTagIdsForContentId( long contentId ) {
        return this.jdbc.query( "SELECT * FROM content_tags WHERE content_id=?", contentTagMapper, contentId );
    }

    public void createContentTagAssociation( long contentId, long tagId ) {
        String sql = "INSERT INTO content_tags (content_id, tag_id) VALUES(?,?)";
        this.jdbc.update( sql, new Object[] {
                contentId,
                tagId
        } );
    }

    private static final RowMapper<Long> contentTagMapper = new RowMapper<Long>() {
        @Override
        public Long mapRow( ResultSet rs, int rowNum ) throws SQLException {
            return rs.getLong( "tag_id" );
        }
    };
}
