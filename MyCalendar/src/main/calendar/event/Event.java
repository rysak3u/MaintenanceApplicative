package src.main.calendar.event;

import java.time.LocalDateTime;

import src.main.calendar.event.periodique.EvenementPeriodique;
import src.main.calendar.event.reunion.Proprietaire;

public abstract class Event {
    protected final TitreEvenement titre;
    protected final Proprietaire proprietaire;
    protected final DateEvenement dateDebut;
    protected final DureeEvenement duree;
    protected final EventId id;
    public Event(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        this.titre = titre;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.id = new EventId();
    }   

    

    public TitreEvenement getTitre() {
        return titre;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public DateEvenement getDateDebut() {
        return dateDebut;
    }

    public DureeEvenement getDuree() {
        return duree;
    }
    

    public abstract String description();

    public boolean appartientAPeriode(LocalDateTime debut, LocalDateTime fin){
        return !getDateDebut().getDate().isBefore(debut) && !getDateDebut().getDate().isAfter(fin);
    }

    public boolean conflit(Event e){
        LocalDateTime fin1 = getDateDebut().getDate().plusMinutes(getDuree().getDureeMinutes());
        LocalDateTime fin2 = e.getDateDebut().getDate().plusMinutes(e.getDuree().getDureeMinutes());
        
        if ((e.getDateDebut().getDate().isBefore(fin2) && fin1.isAfter(e.getDateDebut().getDate()))&& !(e instanceof EvenementPeriodique)) {
                   return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id.equals(event.getId());
    }

    public EventId getId() {
        return id;
    }
}