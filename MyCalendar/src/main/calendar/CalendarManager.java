package src.main.calendar;
import java.time.LocalDateTime;
import java.util.List;

import src.main.calendar.event.EvenementPeriodique;
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

    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.getDateDebut().getDate().plusMinutes(e1.getDuree().getDureeMinutes());
        LocalDateTime fin2 = e2.getDateDebut().getDate().plusMinutes(e2.getDuree().getDureeMinutes());

        if (e1 instanceof EvenementPeriodique || e2 instanceof EvenementPeriodique) {
            return false; // Simplification abusive
        }

        if (e1.getDateDebut().getDate().isBefore(fin2) && fin1.isAfter(e2.getDateDebut().getDate())) {
            return true;
        }
        return false;
    }

    public void afficherEvenements() {
        for (Event e : this.getEvents()) {
            System.out.println(e.description());
        }
    }
}