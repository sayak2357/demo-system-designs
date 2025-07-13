package com.facebook;

public class Message {
    private Integer messageId;
    private Member[] sendTo;
    private String messageBody;
    private byte[] media;

    public boolean addMember(Member member){
        return true;
    }

    public void sendMessage(){
        // iterate over all members of 'sendTo' and send them ntfn, persist in db
    }

}
