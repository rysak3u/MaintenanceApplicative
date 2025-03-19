package src.main.calendar;
import java.time.LocalDateTime;
import java.util.List;

import src.main.calendar.event.Event;



public class CalendarManager {
    public EventManager eventManager;

    public CalendarManager() {
        this.eventManager = new EventManager();
    }

    public List<Event> getEvents(){
        return eventManager.getEvents();
    }

    public void ajouterEvent(Event e) {
        eventManager.ajouterEvent(e);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return eventManager.getEvents().stream()
                .filter(e -> e.appartientAPeriode(debut, fin)).toList();
    }


    public void afficherEvenements() {
        for (Event e : this.getEvents()) {
            System.out.println(e.description());
        }
    }
}