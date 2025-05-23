package src.main.calendar.menu;
import java.time.LocalDateTime;
import java.util.List;

import src.main.calendar.event.Event;
import src.main.calendar.event.EventId;
import src.main.calendar.menu.user.User;



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

    public boolean supprimerEvent(EventId id){
        return eventManager.supprimerEvent(id);
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
    public void afficherEvenementsUtilisateur(User user){
        for(Event e : this.getEvents()){
            if(e.getProprietaire().getNom().equals(user.getUsername())){
                System.out.println(e.description());
            }
        }
    }
}