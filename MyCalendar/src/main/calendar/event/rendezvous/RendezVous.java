package src.main.calendar.event.rendezvous;

import src.main.calendar.event.DateEvenement;
import src.main.calendar.event.DureeEvenement;
import src.main.calendar.event.Event;
import src.main.calendar.event.TitreEvenement;
import src.main.calendar.event.reunion.Proprietaire;

public class RendezVous extends Event {
    public RendezVous(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, proprietaire, dateDebut, duree);
    }

    @Override
    public String description() {
        return "RDV : " + titre + " à " + dateDebut;
    }

}