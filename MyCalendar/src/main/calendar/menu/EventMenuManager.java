package src.main.calendar.menu;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import src.main.calendar.CalendarManager;
import src.main.calendar.event.Event;

public class EventMenuManager {
        private final Scanner scanner;
    private final UserManager userManager;
    private final CalendarManager calendar;

    public EventMenuManager(Scanner scanner, UserManager userManager, CalendarManager calendar) {
        this.scanner = scanner;
        this.userManager = userManager;
        this.calendar = calendar;
    }
    public void afficherEventMenu(){
        System.out.println("\n=== Menu de visualisation d'Événements ===");
        System.out.println("1 - Afficher TOUS les événements");
        System.out.println("2 - Afficher les événements d'un MOIS précis");
        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
        System.out.println("4 - Afficher les événements d'un JOUR précis");
        System.out.println("5 - Retour");
        System.out.print("Votre choix : ");
        String choix = scanner.nextLine();
        traiterChoix(choix);
    }
    private void traiterChoix(String choix){
        // Création de la Map d'actions
        Map<String, Runnable> actionsMenu = new HashMap<>();
        actionsMenu.put("1", ()-> {
            calendar.afficherEvenementsUtilisateur(userManager.getCurrentUser());
        });
        actionsMenu.put("2", () -> afficherEventMois());
        actionsMenu.put("3", () -> afficherEventSemaine());
        actionsMenu.put("4", () -> afficherEventJour());
        actionsMenu.getOrDefault(choix, () -> System.out.println("Choix invalide")).run();
    }
    private void afficherEventJour() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int moisJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le jour (1-31) : ");
        int jour = Integer.parseInt(scanner.nextLine());
   
        LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
        LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);
   
        afficherListe(calendar.eventsDansPeriode(debutJour, finJour));
    }
    private void afficherEventSemaine() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeSemaine = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le numéro de semaine (1-52) : ");
        int semaine = Integer.parseInt(scanner.nextLine());
   
        LocalDateTime debutSemaine = LocalDateTime.now()
                .withYear(anneeSemaine)
                .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                .withHour(0).withMinute(0);
        LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);
   
        afficherListe(calendar.eventsDansPeriode(debutSemaine, finSemaine));
    }
    private void afficherEventMois() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeMois = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int mois = Integer.parseInt(scanner.nextLine());
   
        LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
        LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);
   
        afficherListe(calendar.eventsDansPeriode(debutMois, finMois));
    }
    private static void afficherListe(List<Event> evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Event e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }
}
