package com.viralsville.controller;

import java.util.List;

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
    public Content getContent( @RequestParam("id") long id ) {
        return this.contentRepository.getContent( id );
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    public List<Content> getContentListByPageNumber( @RequestParam("page") int pageNumber ) {
        return this.contentRepository.getContentListByPageNumber( pageNumber );
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Content createContent( @RequestBody Content content ) {
        this.contentRepository.createContent( content );
        return content;
    }
}
