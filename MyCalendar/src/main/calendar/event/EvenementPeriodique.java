package src.main.calendar.event;

import java.time.LocalDateTime;

public class EvenementPeriodique extends Event {
    private final FrequenceJours frequenceJours;

    public EvenementPeriodique(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree, FrequenceJours frequenceJours) {
        super(titre, proprietaire, dateDebut, duree);
        this.frequenceJours = frequenceJours;
    }
    
    @Override
    public String description() {
        return "Événement périodique : " + titre + " tous les " + frequenceJours + " jours";
    }

    public FrequenceJours getFrequenceJours() {
        return frequenceJours;
    }
    @Override
    public  boolean appartientAPeriode(LocalDateTime debut, LocalDateTime fin){
        LocalDateTime temp = getDateDebut().getDate();
        while (temp.isBefore(fin)) {
            if (!temp.isBefore(debut)) {
                return true;
            }
            temp = temp.plusDays(getFrequenceJours().getFrequenceJours());
        }
        return false;
    }

    @Override
    public boolean conflit(Event e) {
        return false;
    }
}