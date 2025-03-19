package src.main.calendar;

import java.util.List;
import src.main.calendar.event.Event;
import java.util.ArrayList;

public class EventManager {
    private final List<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();
    }

    public void ajouterEvent(Event e) {
        events.add(e);
    }

    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }
}

