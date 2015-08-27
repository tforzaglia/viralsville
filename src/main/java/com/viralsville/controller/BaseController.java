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
        return "redirect:/content?page=1";
    }

    @RequestMapping("/content")
    public String content( @RequestParam("page") int pageNumber, Model model ) {
        List<Content> contents = this.contentRepository.getContentListByPageNumber( pageNumber );
        model.addAttribute( "contents", contents );
        return "index";
    }
}
