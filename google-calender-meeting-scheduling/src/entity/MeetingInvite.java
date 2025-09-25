package entity;

public class MeetingInvite {
    private User participant;
    private RSVPStatus status;

    public MeetingInvite(User user) {
        this.participant = user;
        this.status = RSVPStatus.PENDING;
    }

    public void updateStatus(RSVPStatus newStatus){
        this.status = newStatus;
    }

    public User getParticipant() {
        return participant;
    }
}
