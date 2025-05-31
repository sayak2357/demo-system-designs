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
        Integer hashLength = Constants.SHORT_URL_LENGTH;
        UrlMappings existingMapping = urlMappingsRepository.findByLongUrl(longUrl);
        if(existingMapping!=null){
            return new Url(existingMapping.getTinyUrl(), Constants.SHORT_TYPE);
        }
        String hash = hashService.getHash(longUrl, hashLength);
        Url response = new Url(hash, Constants.SHORT_TYPE);
        UrlMappings existing = urlMappingsRepository.findByTinyUrl(hash);
        String oldLongUrl = existing!=null ? existing.getLongUrl() : Constants.EMPTY_STRING;
        while(existing!=null && !oldLongUrl.equals(longUrl)){
            hash = hashService.getHash(longUrl, ++hashLength);
            existing = urlMappingsRepository.findByTinyUrl(hash);
            oldLongUrl = existing!=null ? existing.getLongUrl():null;
        }
        if(longUrl.equals(oldLongUrl)){
            response.setUrl(hash);
        }
        if(existing==null) {
            UrlMappings newUrlMapping = new UrlMappings(longUrl, hash);
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
