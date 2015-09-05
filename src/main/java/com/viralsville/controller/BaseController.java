package com.viralsville.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
    private ContentRepository contentRepository;

    @Autowired
    private static TagRepository tagRepository;

    private static Map<String, Long> tagMap;

    static {
        List<Tag> tags = tagRepository.getAllTags();
        tagMap = new HashMap<String, Long>();
        for ( Tag tag : tags ) {
            tagMap.put( tag.getName(), tag.getId() );
        }
    }

    @RequestMapping("/")
    public String index( Model model ) {
        List<Content> contents = this.contentRepository.getContentListByPageNumber( 1 );
        model.addAttribute( "contents", contents );
        model.addAttribute( "currentPage", 1 );

        return "redirect:/latest?page=1";
    }

    @RequestMapping("/latest")
    public String latest( @RequestParam("page") int pageNumber, @RequestParam(value = "tag", required = false) String tag, Model model ) {
        // immediately redirect to first page if entered page number is less than 1
        if ( pageNumber < 1 ) {
            return "redirect:/latest?page=1";
        }

        List<Content> contents = new ArrayList<>();
        if ( tag != null ) {
            Long tagId = tagMap.get( tag );
            contents = this.contentRepository.getContentListByPageNumberAndTag( pageNumber, tagId );
        } else {
            contents = this.contentRepository.getContentListByPageNumber( pageNumber );
        }

        if ( contents.size() == 0 ) {
            return "redirect:/latest?page=1";
        }

        if ( contents.size() < Constants.CONTENT_PER_PAGE || this.contentRepository.getNumberOfRows() == Constants.CONTENT_PER_PAGE * pageNumber ) {
            model.addAttribute( "onLastPage", "true" );
        }
        model.addAttribute( "contents", contents );
        model.addAttribute( "trending", this.getTrendingContent() );
        model.addAttribute( "currentPage", pageNumber );

        return "index";
    }

    @RequestMapping("/content")
    public String content( @RequestParam("id") long id, Model model ) {
        Content content = this.contentRepository.getContent( id );
        content.setViews( content.getViews() + 1 );
        this.contentRepository.updateContentViews( content );
        model.addAttribute( "content", content );
        model.addAttribute( "trending", this.getTrendingContent() );

        return "content";
    }

    private List<Content> getTrendingContent() {
        return this.contentRepository.getTrendingContentList();
    }
}
