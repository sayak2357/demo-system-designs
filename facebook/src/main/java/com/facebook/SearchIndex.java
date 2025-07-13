package com.facebook;

import java.util.HashMap;
import java.util.List;

public class SearchIndex implements Search{

    private HashMap<String, List<Member>> memberNames;
    private HashMap<String, List<Group>> groupNames;
    private HashMap<String, List<Page>> pageTitles;
    private HashMap<String, List<Post>> posts;

    public boolean addMember(Member member){
        if(memberNames.containsKey(member.getName())){
            memberNames.get(member.getName()).add(member);
        }
        else{
            memberNames.put(member.getName(),(List<Member>) member);
        }
        return true;
    }

    public boolean addGroup(Group group){
        return false;
    }

    public boolean addPage(Page page){
        return false;
    }

    public boolean addPost(Post post){
        return false;
    }

    @Override
    public List<Member> searchMember(String name) {
        return memberNames.get(name);
    }

    @Override
    public List<Group> searchGroup(String name) {
        return groupNames.get(name);
    }

    @Override
    public List<Page> searchPage(String title) {
        return pageTitles.get(title);
    }

    @Override
    public List<Post> searchPost(String word) {
        return posts.get(word);
    }
}
