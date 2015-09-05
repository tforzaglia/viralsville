package com.viralsville.data;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.viralsville.application.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({
    "server.port=0"
})
public class ContentTagRepositoryTest {

    @Autowired
    protected ContentTagsRepository contentTagsRepository;

    @Test
    public void testCreateContentTag() {
        this.contentTagsRepository.createContentTagAssociation( 19L, 11L );
    }

    @Test
    public void testGetTagIds() {
        List<Long> tagIds = this.contentTagsRepository.getTagIdsForContentId( 19L );
        for ( Long tagId : tagIds ) {
            System.out.println( tagId );
        }
    }
}
