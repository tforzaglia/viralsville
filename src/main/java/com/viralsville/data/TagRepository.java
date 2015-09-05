package com.viralsville.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.viralsville.model.Tag;

@Repository
public class TagRepository {

    @Autowired
    protected JdbcTemplate jdbc;

    public Tag getTag( long id ) {
        return this.jdbc.queryForObject( "SELECT * FROM tags WHERE id=?", tagMapper, id );
    }

    public void createTag( Tag tag ) {
        String sql = "INSERT INTO tags (name) VALUES(?)";
        this.jdbc.update( sql, new Object[] {
                tag.getName()
        } );
    }

    private static final RowMapper<Tag> tagMapper = new RowMapper<Tag>() {
        @Override
        public Tag mapRow( ResultSet rs, int rowNum ) throws SQLException {
            Tag tag = new Tag();
            tag.setId( rs.getLong( "id" ) );
            tag.setName( rs.getString( "name" ) );

            return tag;
        }
    };
}
