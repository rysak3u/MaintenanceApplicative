package src.main.calendar.event.evenementvalidation;

import java.time.LocalDateTime;

import src.main.calendar.event.DateEvenement;
import src.main.calendar.event.DureeEvenement;
import src.main.calendar.event.Event;
import src.main.calendar.event.TitreEvenement;
import src.main.calendar.event.reunion.Proprietaire;

/*
 * 
 * Ce Type d'évenement est un évenement qui nécessite une validation par le propriétaire
 * 
 */
public class EvenementAvecValidation extends Event {
    private Valide valide;  // Indique si l'événement a été validé ou non

    public EvenementAvecValidation(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, proprietaire, dateDebut, duree);
        this.valide = new Valide();
    }

    @Override
    public String description() {
        String validation = valide.isValide()?" (Validé par le propriétaire)":" (Validation requise)";
        return "Événement avec validation : " + titre + validation;
    }

    public void valider() {
        // Méthode permettant de valider l'événement
        this.valide.setValide(true);
    }

    public boolean estValide() {
        return valide.isValide();
    }

    @Override
    public boolean appartientAPeriode(LocalDateTime debut, LocalDateTime fin){
        // Si l'événement n'est pas validé, il ne provoque pas de conflit
        if (!valide.isValide()) {
            return false;
        }
        // Sinon, on applique la logique normale de détection de conflit
        return !getDateDebut().getDate().isBefore(debut) && !getDateDebut().getDate().isAfter(fin);
    }
}
