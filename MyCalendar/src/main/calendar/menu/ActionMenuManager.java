package src.main.calendar.menu;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import src.main.calendar.CalendarManager;
import src.main.calendar.event.DateEvenement;
import src.main.calendar.event.DureeEvenement;
import src.main.calendar.event.EvenementPeriodique;
import src.main.calendar.event.FrequenceJours;
import src.main.calendar.event.Lieu;
import src.main.calendar.event.Participants;
import src.main.calendar.event.Proprietaire;
import src.main.calendar.event.RendezVous;
import src.main.calendar.event.Reunion;
import src.main.calendar.event.TitreEvenement;

public class ActionMenuManager {
    private final Scanner scanner;
    private final UserManager userManager;
    private final CalendarManager calendar;

    public ActionMenuManager(Scanner scanner, UserManager userManager, CalendarManager calendar) {
        this.scanner = scanner;
        this.userManager = userManager;
        this.calendar = calendar;
    }

    public void afficherActionMenu(){
         Map<String, Runnable> gestionEvenements = new HashMap<>();
        gestionEvenements.put("1", () -> afficherEvenements());
        gestionEvenements.put("2", () -> ajouterRendezVous());
        gestionEvenements.put("3", () -> ajouterReunion());
        gestionEvenements.put("4", () -> ajouterEvenementPeriodique());
        gestionEvenements.put("5", () -> seDeconnecter());

        System.out.print("Votre choix : ");
        String choix = scanner.nextLine();
        gestionEvenements.getOrDefault(choix, () -> System.out.println("Choix invalide")).run();
    }
    private void afficherEvenements() {
        EventMenuManager eventMenuManager = new EventMenuManager(scanner, userManager, calendar);
        eventMenuManager.afficherEventMenu();
    }
        private void ajouterRendezVous() {
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int mois = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jour = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());
        calendar.ajouterEvent(new RendezVous(new TitreEvenement(titre), new Proprietaire(userManager.getCurrentUser().getUsername()), new DateEvenement(LocalDateTime.of(annee, mois, jour, heure, minute)), new DureeEvenement(duree)));
        System.out.println("Événement ajouté.");
    }

    // Ajouter une réunion
    private void ajouterReunion() {
        System.out.print("Titre de l'événement : ");
                        String titre2 = scanner.nextLine();
                        System.out.print("Année (AAAA) : ");
                        int annee2 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Mois (1-12) : ");
                        int moisRdv2 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Jour (1-31) : ");
                        int jourRdv2 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Heure début (0-23) : ");
                        int heure2 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Minute début (0-59) : ");
                        int minute2 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Durée (en minutes) : ");
                        int duree2 = Integer.parseInt(scanner.nextLine());
                        System.out.println("Lieu :");
                        String lieu = scanner.nextLine();
                        
                        String participants = userManager.getCurrentUser().getUsername();
                        
                        System.out.println("Ajouter un participant ? (oui / non)");
                        while (scanner.nextLine().equals("oui"))
                        {
                            System.out.print("Participants : ");
                            participants += ", " + scanner.nextLine();
                            System.out.println("Ajouter un participant ? (oui / non)");
                        }

                        calendar.ajouterEvent(new Reunion(new TitreEvenement(titre2), new Proprietaire(participants),new DateEvenement(LocalDateTime.of(annee2, moisRdv2, jourRdv2, heure2, minute2)), new DureeEvenement(duree2), new Lieu(lieu), new Participants(Arrays.asList(participants.split(",")))));
                        System.out.println("Événement ajouté.");
    }

    // Ajouter un événement périodique
    private void ajouterEvenementPeriodique() {
        System.out.print("Titre de l'événement : ");
                        String titre3 = scanner.nextLine();
                        System.out.print("Année (AAAA) : ");
                        int annee3 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Mois (1-12) : ");
                        int moisRdv3 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Jour (1-31) : ");
                        int jourRdv3 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Heure début (0-23) : ");
                        int heure3 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Minute début (0-59) : ");
                        int minute3 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Frequence (en jours) : ");
                        int frequence = Integer.parseInt(scanner.nextLine());

                        calendar.ajouterEvent(new EvenementPeriodique(new TitreEvenement(titre3), new Proprietaire(userManager.getCurrentUser().getUsername()), new DateEvenement(LocalDateTime.of(annee3, moisRdv3, jourRdv3, heure3, minute3)), new DureeEvenement(0), new FrequenceJours(frequence)));
                        System.out.println("Événement ajouté.");
    }

    // Déconnexion
    private void seDeconnecter() {
        System.out.println("Déconnexion...");
        userManager.setCurrentUser(null);
    }
}
