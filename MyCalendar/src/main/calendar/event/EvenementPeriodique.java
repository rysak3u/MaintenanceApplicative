package src.main.calendar.event;

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
}