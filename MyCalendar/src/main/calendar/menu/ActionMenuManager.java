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

    public void afficherActionMenu() {
        afficherOptionsEvenements();
        System.out.print("Votre choix : ");
        String choix = scanner.nextLine();
        traiterChoix(choix);
    }

    private void traiterChoix(String choix) {
        Map<String, Runnable> gestionEvenements = new HashMap<>();
        gestionEvenements.put("1", this::afficherEvenements);
        gestionEvenements.put("2", this::ajouterRendezVous);
        gestionEvenements.put("3", this::ajouterReunion);
        gestionEvenements.put("4", this::ajouterEvenementPeriodique);
        gestionEvenements.put("5", this::seDeconnecter);
        gestionEvenements.getOrDefault(choix, () -> System.out.println("Choix invalide")).run();
    }

    private void afficherOptionsEvenements() {
        System.out.println("\nBonjour, " + userManager.getCurrentUser());
        System.out.println("\n=== Menu Gestionnaire d'Événements ===");
        System.out.println("1 - Voir les événements");
        System.out.println("2 - Ajouter un rendez-vous perso");
        System.out.println("3 - Ajouter une réunion");
        System.out.println("4 - Ajouter un évènement périodique");
        System.out.println("5 - Se déconnecter");
    }

    private void afficherEvenements() {
        EventMenuManager eventMenuManager = new EventMenuManager(scanner, userManager, calendar);
        eventMenuManager.afficherEventMenu();
    }

    private void ajouterRendezVous() {
        System.out.println("Ajouter un rendez-vous");
        DateEvenement dateEvenement = demanderDateHeure();
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        calendar.ajouterEvent(new RendezVous(new TitreEvenement(titre), 
                new Proprietaire(userManager.getCurrentUser().getUsername()), dateEvenement, new DureeEvenement(0)));
        System.out.println("Événement ajouté.");
    }

    private void ajouterReunion() {
        System.out.println("Ajouter une réunion");
        DateEvenement dateEvenement = demanderDateHeure();
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        System.out.print("Lieu : ");
        String lieu = scanner.nextLine();
        String participants = userManager.getCurrentUser().getUsername();
        participants = ajouterParticipants(participants);
        calendar.ajouterEvent(new Reunion(new TitreEvenement(titre), new Proprietaire(participants), 
                dateEvenement, new DureeEvenement(0), new Lieu(lieu), new Participants(Arrays.asList(participants.split(",")))));
        System.out.println("Événement ajouté.");
    }

    private void ajouterEvenementPeriodique() {
        System.out.println("Ajouter un événement périodique");
        DateEvenement dateEvenement = demanderDateHeure();
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        System.out.print("Fréquence (en jours) : ");
        int frequence = Integer.parseInt(scanner.nextLine());
        calendar.ajouterEvent(new EvenementPeriodique(new TitreEvenement(titre),
                new Proprietaire(userManager.getCurrentUser().getUsername()), dateEvenement, new DureeEvenement(0), new FrequenceJours(frequence)));
        System.out.println("Événement ajouté.");
    }

    private DateEvenement demanderDateHeure() {
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
        return new DateEvenement(LocalDateTime.of(annee, mois, jour, heure, minute));
    }

    private String ajouterParticipants(String participants) {
        System.out.println("Ajouter un participant ? (oui / non)");
        while (scanner.nextLine().equals("oui")) {
            System.out.print("Participant : ");
            participants += ", " + scanner.nextLine();
            System.out.println("Ajouter un participant ? (oui / non)");
        }
        return participants;
    }

    private void seDeconnecter() {
        System.out.println("Déconnexion...");
        userManager.setCurrentUser(null);
    }
}

