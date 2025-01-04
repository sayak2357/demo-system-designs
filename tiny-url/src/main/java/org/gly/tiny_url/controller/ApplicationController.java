package org.gly.tiny_url.controller;

import org.gly.tiny_url.constants.Constants;
import org.gly.tiny_url.dto.Url;
import org.gly.tiny_url.service.UrlMappingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApplicationController {


    @Autowired
    private UrlMappingsService urlMappingsService;

//    public ApplicationController(UrlMappingsService urlMappingsService){
//        this.urlMappingsService = urlMappingsService;
//    }

    @PostMapping("/getTinyUrl")
    public Url getTinyUrl(@RequestBody Url longUrl){
        Url tinyUrl = urlMappingsService.getShortUrl(longUrl.getUrl());
        return tinyUrl;
    }

    @GetMapping("/getLongUrl/{shortUrl}")
    public ResponseEntity<Url> getTinyUrl(@PathVariable String shortUrl) throws Exception {
        Url longUrl = urlMappingsService.getLongUrl(shortUrl);
        if(longUrl==null)
            return ResponseEntity.internalServerError().body(new Url("invalid short url", Constants.UNKNOWN_TYPE));
        return ResponseEntity.status(200).body(longUrl);
    }
}
