package org.gly.tiny_url.entity;

import jakarta.persistence.*;

@Entity
@Table(name="url_mappings")
public class UrlMappings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="tiny_url")
    private String tinyUrl;

    @Column(name="long_url")
    private String longUrl;

    public UrlMappings(){

    }

    public UrlMappings(String longUrl, String tinyUrl) {
        this.longUrl = longUrl;
        this.tinyUrl = tinyUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }

    @Override
    public String toString() {
        return "UrlMappings{" +
                "id=" + id +
                ", tinyUrl='" + tinyUrl + '\'' +
                ", longUrl='" + longUrl + '\'' +
                '}';
    }
}
