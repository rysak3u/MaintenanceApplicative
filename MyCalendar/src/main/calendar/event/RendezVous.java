package src.main.calendar.event;


public class RendezVous extends Event {
    public RendezVous(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, proprietaire, dateDebut, duree);
    }

    @Override
    public String description() {
        return "RDV : " + titre + " à " + dateDebut;
    }

}