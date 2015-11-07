package com.viralsville.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viralsville.constants.Constants;
import com.viralsville.data.ContentRepository;
import com.viralsville.data.TagRepository;
import com.viralsville.model.Content;
import com.viralsville.model.Tag;

@Controller
public class BaseController {

    @Autowired
    protected ContentRepository contentRepository;

    @Autowired
    protected TagRepository tagRepository;

    protected Map<String, Long> tagMap;

    private Logger log = Logger.getLogger( BaseController.class );

    @PostConstruct
    private void fillInTagMap() {
        List<Tag> tags = this.tagRepository.getAllTags();
        this.tagMap = new HashMap<String, Long>();
        for ( Tag tag : tags ) {
            this.tagMap.put( tag.getName(), tag.getId() );
        }
    }

    @RequestMapping("/")
    public String index( Model model, HttpServletRequest request ) {
        List<Content> contents = this.contentRepository.getContentListByPageNumber( 1 );
        model.addAttribute( "contents", contents );
        model.addAttribute( "currentPage", 1 );

        return "redirect:/latest?page=1";
    }

    @RequestMapping("/latest")
    public String latest( Device device, @RequestParam("page") int pageNumber, @RequestParam(value = "tag", required = false) String tag, Model model, HttpServletRequest request ) {
        // immediately redirect to first page if entered page number is less than 1
        if ( pageNumber < 1 ) {
            return "redirect:/latest?page=1";
        }

        List<Content> contents = new ArrayList<>();
        if ( tag != null ) {
            Long tagId = this.tagMap.get( tag );
            this.log.info( "Tag name = " + tag + " tag id = " + tagId );
            model.addAttribute( "hasTag", "true" );
            model.addAttribute( "tag", tag );
            contents = this.contentRepository.getContentListByPageNumberAndTag( pageNumber, tagId );
        } else {
            contents = this.contentRepository.getContentListByPageNumber( pageNumber );
        }

        // redirect to first page if no content is returned
        if ( contents.size() == 0 ) {
            this.log.info( "No content returned. Redirecting to page 1" );
            return "redirect:/latest?page=1";
        }

        // check if we have reached the last page
        if ( contents.size() < Constants.CONTENT_PER_PAGE || this.contentRepository.getNumberOfRows() == Constants.CONTENT_PER_PAGE * pageNumber ) {
            model.addAttribute( "onLastPage", "true" );
            this.log.info( "Now on the last page" );
        }

        model.addAttribute( "contents", contents );
        model.addAttribute( "trending", this.getTrendingContent() );
        model.addAttribute( "currentPage", pageNumber );
        model.addAttribute( "baseUrl", request.getRequestURL().toString().replace( request.getRequestURI(), request.getContextPath() ) );

        if ( device.isMobile() || device.isTablet() ) {
            System.out.println( "Accessed from mobile - returning mobile index page" );
            return "index-mobile";
        }

        System.out.println( "Accessed from web" );
        return "index";
    }

    @RequestMapping("/content")
    public String content( Device device, @RequestParam("id") long id, Model model, HttpServletRequest request ) {
        Content content = this.contentRepository.getContent( id );
        content.setViews( content.getViews() + 1 );
        this.contentRepository.updateContentViews( content );
        model.addAttribute( "content", content );
        model.addAttribute( "trending", this.getTrendingContent() );
        model.addAttribute( "baseUrl", request.getRequestURL().toString().replace( request.getRequestURI(), request.getContextPath() ) );

        if ( device.isMobile() || device.isTablet() ) {
            System.out.println( "Accessed from mobile - returning mobile content page" );
            return "content-mobile";
        }

        return "content";
    }

    @RequestMapping("/search")
    public String search( @RequestParam("searchTerm") String searchTerm, Model model, HttpServletRequest request ) {
        this.log.info( "Searching for searchTerm : " + searchTerm );
        List<Content> contents = this.contentRepository.getContentListBySearchTerm( searchTerm );
        model.addAttribute( "contents", contents );
        model.addAttribute( "trending", this.getTrendingContent() );
        model.addAttribute( "baseUrl", request.getRequestURL().toString().replace( request.getRequestURI(), request.getContextPath() ) );

        return "search";
    }

    @RequestMapping("/admin")
    public String admin( Model model, HttpServletRequest request ) {
        model.addAttribute( "baseUrl", request.getRequestURL().toString().replace( request.getRequestURI(), request.getContextPath() ) );

        return "admin";
    }

    private List<Content> getTrendingContent() {
        return this.contentRepository.getTrendingContentList();
    }
}
