package com.bookMyShowV2.model;

import java.util.List;

public interface Search {

    List<Movie> searchByTitle(String tlte);
    List<Movie> searchByLanguage(String language);
    List<Movie> searchByGenre(String genre);
    List<Movie> searchByCity(String city);
}
