package src.main.calendar.event;

import java.util.List;

public class Participants {
    private List<String> participants;

    public Participants(List<String> participants) {
        this.participants = participants;
    }

    public List<String> getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return participants.toString();
    }
}