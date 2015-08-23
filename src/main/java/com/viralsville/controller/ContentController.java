package com.viralsville.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viralsville.data.ContentRepository;
import com.viralsville.model.Content;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentRepository contentRepository;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Content getUser( @RequestParam("id") long id ) {
        return this.contentRepository.getContent( id );
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Content createContent( @RequestBody Content content ) {
        this.contentRepository.createContent( content );
        return content;
    }
}
