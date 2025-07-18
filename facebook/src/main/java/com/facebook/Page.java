package com.facebook;

import java.util.List;

public class Page {

    private Integer pageId;
    private String name;
    private String description;
    private String type;
    private int totalMembers;
    private List<Recommendation> recommendations;

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }
}
