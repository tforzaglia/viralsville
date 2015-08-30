package com.viralsville.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "redirect:/latest?page=1";
    }

    @RequestMapping("/latest")
    public String latest( @RequestParam("page") int pageNumber, Model model ) {
        List<Content> contents = this.contentRepository.getContentListByPageNumber( pageNumber );
        model.addAttribute( "contents", contents );
        return "index";
    }

    @RequestMapping("/content")
    public String content( @RequestParam("id") long id, Model model ) {
        Content content = this.contentRepository.getContent( id );
        content.setViews( content.getViews() + 1 );
        this.contentRepository.updateContentViews( content );
        model.addAttribute( "content", content );

        return "content";
    }
}
