package src.main.calendar.event;

import java.time.LocalDateTime;

public class RendezVous extends Event {
    public RendezVous(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, proprietaire, dateDebut, duree);
    }

    @Override
    public String description() {
        return "RDV : " + titre + " à " + dateDebut;
    }

    @Override
    public boolean appartientAPeriode(LocalDateTime debut, LocalDateTime fin) {
        return !getDateDebut().getDate().isBefore(debut) && !getDateDebut().getDate().isAfter(fin);
    }
}