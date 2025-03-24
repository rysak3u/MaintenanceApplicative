package src.main.calendar.menu;

import java.util.List;
import src.main.calendar.event.Event;
import java.util.ArrayList;

public class EventManager {
    private final List<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();
    }

    public void ajouterEvent(Event e) {
        if(!isConflicting(e)){
            events.add(e);
        }
    }

    public boolean isConflicting(Event e){
        for(Event e2 : events){
            if(e2.conflit(e)){
                return true;
            }
        }
        return false;
    }   

    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }
}

