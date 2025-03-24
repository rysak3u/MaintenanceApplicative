package src.main.calendar.event;

import java.util.UUID;

public class EventId {
    private final String id;

    public EventId() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public EventId(String id){
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventId eventId = (EventId) o;
        return id.equals(eventId.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}
