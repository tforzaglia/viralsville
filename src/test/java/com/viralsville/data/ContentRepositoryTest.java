package com.viralsville.data;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.viralsville.application.Application;
import com.viralsville.model.Content;
import com.viralsville.model.ContentType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({
    "server.port=0"
})
public class ContentRepositoryTest {

    @Autowired
    protected ContentRepository contentRepository;

    @Test
    public void testGetContent() {
        Content content = this.contentRepository.getContent( 1L );
        System.out.println( "Content Id = " + content.getId() );
        System.out.println( "Title = " + content.getTitle() );
        System.out.println( "External link = " + content.getExternalLink() );
        System.out.println( "Content type = " + content.getContentType().name() );
        System.out.println( "Created date = " + content.getCreatedDate().toString() );
        System.out.println( "Number of views = " + content.getViews() );
    }

    @Test
    public void testGetContentListByPageNumber() {
        List<Content> contentList = this.contentRepository.getContentListByPageNumber( 2 );
        for ( Content content : contentList ) {
            System.out.println( content.getTitle() );
        }
    }

    @Test
    public void testInsertContent() {
        Content content = new Content();
        content.setTitle( "Cool" );
        content.setExternalLink( "https://video-lga1-1.xx.fbcdn.net/hvideo-xtf1/v/t43.1792-2/11014183_326058660938294_1442671678_n.mp4?efg=eyJybHIiOjE1MDAsInJsYSI6MTAyNH0%3D&rl=1500&vabr=367&oh=86b1ceeae08e76aa86076a67f4f5655d&oe=55DFDAD9" );
        content.setContentType( ContentType.VIDEO );
        content.setCreatedDate( new Date() );
        content.setViews( 0L );
        this.contentRepository.createContent( content );
    }
}
