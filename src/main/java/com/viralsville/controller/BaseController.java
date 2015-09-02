package com.viralsville.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viralsville.constants.Constants;
import com.viralsville.data.ContentRepository;
import com.viralsville.model.Content;

@Controller
public class BaseController {

    @Autowired
    private ContentRepository contentRepository;

    @RequestMapping("/")
    public String index( Model model ) {
        List<Content> contents = this.contentRepository.getContentListByPageNumber( 1 );
        model.addAttribute( "contents", contents );
        model.addAttribute( "nextPage", 2 );

        return "redirect:/latest?page=1";
    }

    @RequestMapping("/latest")
    public String latest( @RequestParam("page") int pageNumber, Model model ) {
        if ( pageNumber < 1 ) {
            return "redirect:/latest?page=1";
        }
        List<Content> contents = this.contentRepository.getContentListByPageNumber( pageNumber );
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
