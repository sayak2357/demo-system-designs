package org.gly.tiny_url.service;

import org.gly.tiny_url.dto.Url;

public interface UrlMappingsService {

    Url getShortUrl(String longUrl);
    Url getLongUrl(String shortUrl);

}
