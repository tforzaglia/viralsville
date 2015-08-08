package com.viralsville.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viralsville.data.ContentRepository;
import com.viralsville.model.Content;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentRepository contentRepo;

    @RequestMapping("/get")
    public Content getUser( @RequestParam("id") long id ) {
        return this.contentRepo.getContent( id );
    }

    // TODO: this will need to take an object as the parameter as we add more fields to the Content object
    @RequestMapping("/create")
    public String createContent( @RequestParam("title") String title ) {
        this.contentRepo.createContent( title );
        return title + " created";
    }
}
