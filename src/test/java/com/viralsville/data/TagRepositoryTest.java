package com.viralsville.data;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.viralsville.application.Application;
import com.viralsville.model.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({
    "server.port=0"
})
public class TagRepositoryTest {

    @Autowired
    TagRepository tagRepository;

    @Test
    public void testCreateTag() {
        Tag tag = new Tag();
        tag.setName( "lulz" );

        this.tagRepository.createTag( tag );
    }

    @Test
    public void testGetAllTags() {
        List<Tag> tags = this.tagRepository.getAllTags();
        for ( Tag tag : tags ) {
            System.out.println( tag.getId() + " : " + tag.getName() );
        }
    }

    @Test
    public void createMultipleTags() {
        Tag tag1 = new Tag( "dafuq" );
        Tag tag2 = new Tag( "0morale" );
        Tag tag3 = new Tag( "fail" );
        Tag tag4 = new Tag( "filthy" );
        Tag tag5 = new Tag( "mygawd" );
        Tag tag6 = new Tag( "best" );
        Tag tag7 = new Tag( "worst" );
        Tag tag8 = new Tag( "sciencebitch" );
        Tag tag9 = new Tag( "pets" );
        Tag tag10 = new Tag( "cats" );

        List<Tag> tags = new ArrayList<>();
        tags.add( tag1 );
        tags.add( tag2 );
        tags.add( tag3 );
        tags.add( tag4 );
        tags.add( tag5 );
        tags.add( tag6 );
        tags.add( tag7 );
        tags.add( tag8 );
        tags.add( tag9 );
        tags.add( tag10 );

        for ( Tag tag : tags ) {
            this.tagRepository.createTag( tag );
        }
    }
}
