package org.gly.tiny_url.service;

import org.gly.tiny_url.constants.Constants;
import org.gly.tiny_url.dao.UrlMappingsRepository;
import org.gly.tiny_url.dto.Url;
import org.gly.tiny_url.entity.UrlMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlMappingsServiceImpl implements UrlMappingsService{
    @Autowired
    private HashService hashService;

    @Autowired
    private UrlMappingsRepository urlMappingsRepository;

    @Override
    public Url getShortUrl(String longUrl) {
        String hash = hashService.getHash(longUrl);
        Url response = new Url(hash, Constants.SHORT_TYPE);
        UrlMappings existing = urlMappingsRepository.findByTinyUrl(hash);
        if(existing==null){
            UrlMappings newUrlMapping = new UrlMappings(longUrl,hash);
            urlMappingsRepository.save(newUrlMapping);
        }
        return response;
    }

    @Override
    public Url getLongUrl(String shortUrl) {
        UrlMappings existing = urlMappingsRepository.findByTinyUrl(shortUrl);
        if(existing==null)
            return null;
        Url longUrl = new Url(existing.getLongUrl(),Constants.LONG_TYPE);
        return longUrl;
    }
}
