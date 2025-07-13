package com.facebook;

import java.util.Date;
import java.util.HashSet;

public class Member extends Person{
    private Integer memberId;
    private Date dateOfMembership;
    private String name;
    private Profile profile;
    private HashSet<Integer> memberFollows;
    private HashSet<Integer> memberConnections;
    private HashSet<Integer> memberSuggestions;
    private HashSet<ConnectionInvitation> connectionInvitations;
    private HashSet<Integer> groupFollows;

    public boolean sendMessage(Message message){
        return true;
    }

    private boolean createPost(Post post){
        return true;
    }

    public boolean addComment(Post post){
        return true;
    }

    public boolean sendConnectionInvitation(ConnectionInvitation invitation){
        return true;
    }
}
