package com.viralsville.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viralsville.data.ContentTagsRepository;
import com.viralsville.model.Content;
import com.viralsville.model.ContentType;
import com.viralsville.model.vo.ContentVO;

@RestController
@RequestMapping("/content")
public class ContentController extends BaseController {

    private Logger log = Logger.getLogger( ContentController.class );

    @Autowired
    private ContentTagsRepository contentTagsRepository;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Content getContent( @RequestParam("id") long id ) {
        return this.contentRepository.getContent( id );
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    public List<Content> getContentListByPageNumber( @RequestParam("page") int pageNumber ) {
        return this.contentRepository.getContentListByPageNumber( pageNumber );
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Content createContent( @RequestBody ContentVO contentVO ) {
        Content content = new Content();
        content.setTitle( contentVO.getTitle() );

        // modify the external url if content is a facebook video
        if ( ContentType.FACEBOOK.name().equals( contentVO.getContentType() ) ) {
            if ( contentVO.getExternalLink().endsWith( "/" ) ) {
                contentVO.setExternalLink( contentVO.getExternalLink().substring( 0, contentVO.getExternalLink().length() - 1 ) );
            }
            String videoId = contentVO.getExternalLink().substring( contentVO.getExternalLink().lastIndexOf( "/" ) + 1 );
            String convertedUrl = "https://www.facebook.com/video/embed?video_id=" + videoId;

            contentVO.setExternalLink( convertedUrl );
        }

        content.setExternalLink( contentVO.getExternalLink() );
        content.setContentType( ContentType.valueOf( contentVO.getContentType() ) );
        content.setCreatedDate( new Date() );
        content.setViews( 0L );

        Number newContentId = this.contentRepository.createContent( content );

        this.log.info( "Created new content with id " + newContentId );
        for ( int i = 0; i < contentVO.getTagNames().length; i++ ) {
            this.contentTagsRepository.createContentTagAssociation( newContentId.longValue(), this.tagMap.get( contentVO.getTagNames()[i] ) );
        }

        return content;
    }
}
