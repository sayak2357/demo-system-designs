package com.facebook;

import java.util.List;

public class Profile {

    private byte[] profilePicture;
    private byte[] coverPhoto;
    private String gender;
    private List<Work> workExperience;

    public boolean addExperience(Work work){
        workExperience.add(work);
        return true;
    }
}
