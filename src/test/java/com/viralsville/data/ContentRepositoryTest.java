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
        content.setTitle( "Purrito" );
        content.setExternalLink( "http://i.ytimg.com/vi/tntOCGkgt98/maxresdefault.jpg" );
        content.setContentType( ContentType.IMAGE );
        content.setCreatedDate( new Date() );
        content.setViews( 0L );
        this.contentRepository.createContent( content );
    }

    @Test
    public void testUpdateContentViews() {
        Content content = this.contentRepository.getContent( 1L );
        System.out.print( "Old views number = " + content.getViews() );
        content.setViews( content.getViews() + 1 );
        this.contentRepository.updateContentViews( content );
        System.out.print( "New views number = " + content.getViews() );
    }

    @Test
    public void testGetTotalNumberOfRows() {
        System.out.println( this.contentRepository.getNumberOfRows() );
    }

    @Test
    public void testGetTrending() {
        List<Content> trending = this.contentRepository.getTrendingContentList();
        System.out.println( trending.size() );
    }

    @Test
    public void testGetContentByTag() {
        List<Content> contentList = this.contentRepository.getContentListByPageNumberAndTag( 1, 10 );
        for ( Content content : contentList ) {
            System.out.println( content.getTitle() );
        }
    }
}
