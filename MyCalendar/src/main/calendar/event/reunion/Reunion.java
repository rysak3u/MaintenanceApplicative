package src.main.calendar.event.reunion;

import src.main.calendar.event.DateEvenement;
import src.main.calendar.event.DureeEvenement;
import src.main.calendar.event.Event;
import src.main.calendar.event.TitreEvenement;

public class Reunion extends Event {
    private final Lieu lieu;
    private final Participants participants;

    public Reunion(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree, Lieu lieu,Participants participants) {
        super(titre, proprietaire, dateDebut, duree);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + titre + " à " + lieu + " avec " + participants;
    }
}
