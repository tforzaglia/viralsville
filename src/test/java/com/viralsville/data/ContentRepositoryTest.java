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
import com.viralsville.model.vo.ContentVO;

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
        content.setTitle( "Careful! They're ruffles!" );
        content.setExternalLink( "https://www.facebook.com/video/embed?video_id=982105495181066" );
        content.setContentType( ContentType.FACEBOOK );
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

    @Test
    public void testGetContentBySearchTerm() {
        List<Content> contentList = this.contentRepository.getContentListBySearchTerm( "hello world" );
        for ( Content content : contentList ) {
            System.out.println( content.getId() );
            System.out.println( content.getTitle() );
        }
    }

    @Test
    public void testFacebookUrlTranslation() {
        ContentVO contentVO = new ContentVO();
        contentVO.setExternalLink( "https://www.facebook.com/TheSimpsonsBestMoments/videos/982041821854100/" );

        if ( contentVO.getExternalLink().endsWith( "/" ) ) {
            contentVO.setExternalLink( contentVO.getExternalLink().substring( 0, contentVO.getExternalLink().length() - 1 ) );
        }
        String videoId = contentVO.getExternalLink().substring( contentVO.getExternalLink().lastIndexOf( "/" ) + 1 );
        String convertedUrl = "https://www.facebook.com/video/embed?video_id=" + videoId;

        contentVO.setExternalLink( convertedUrl );

        System.out.println( contentVO.getExternalLink() );
    }
}
