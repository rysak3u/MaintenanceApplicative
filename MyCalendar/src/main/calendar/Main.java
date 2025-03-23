package src.main.calendar;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;

import src.main.calendar.event.DateEvenement;
import src.main.calendar.event.DureeEvenement;
import src.main.calendar.event.EvenementPeriodique;
import src.main.calendar.event.Event;
import src.main.calendar.event.FrequenceJours;
import src.main.calendar.event.Lieu;
import src.main.calendar.event.Participants;
import src.main.calendar.event.Proprietaire;
import src.main.calendar.event.RendezVous;
import src.main.calendar.event.Reunion;
import src.main.calendar.event.TitreEvenement;
import src.main.calendar.menu.UserManager;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        CalendarManager calendar = new CalendarManager();
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        // Ajouter des utilisateurs au démarrage
        userManager.ajouterUtilisateur("Roger", "Chat");
        userManager.ajouterUtilisateur("Pierre", "KiRouhl");

        while (true) {
            if (userManager.getCurrentUser() == null) {
                afficherBienvenue();

                // Création d'une map d'actions pour le menu principal
                Map<String, Runnable> menuActions = new HashMap<>();
                menuActions.put("1", () -> seConnecter(scanner, userManager));
                menuActions.put("2", () -> creerCompte(scanner, userManager));
                String choix = scanner.nextLine();

                // Exécuter l'action correspondante
                menuActions.getOrDefault(choix, () -> System.out.println("Choix invalide")).run();
            }

            while (userManager.getCurrentUser() != null) {
                System.out.println("\nBonjour, " + userManager.getCurrentUser());
                afficherMenuEvenements();

                // Menu pour les événements
                Map<String, Runnable> gestionEvenements = new HashMap<>();
                gestionEvenements.put("1", () -> afficherEvenements(calendar,scanner));
                gestionEvenements.put("2", () -> ajouterRendezVous(scanner, calendar, userManager.getCurrentUser()));
                gestionEvenements.put("3", () -> ajouterReunion(scanner, calendar, userManager.getCurrentUser()));
                gestionEvenements.put("4", () -> ajouterEvenementPeriodique(scanner, calendar, userManager.getCurrentUser()));
                gestionEvenements.put("5", () -> seDeconnecter());

                System.out.print("Votre choix : ");
                String choix = scanner.nextLine();

                // Exécuter l'action correspondante
                gestionEvenements.getOrDefault(choix, () -> System.out.println("Choix invalide")).run();
            }
        }
    }

    // Fonction pour se connecter
    private static void seConnecter(Scanner scanner, UserManager userManager) {
        System.out.print("Nom d'utilisateur: ");
        String utilisateur = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String motDePasse = scanner.nextLine();

        if (!userManager.verifierConnexion(utilisateur, motDePasse)) {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
            utilisateur = null;
        }else{
            userManager.setCurrentUser(utilisateur);
        }
    }

    // Fonction pour créer un compte
    private static void creerCompte(Scanner scanner, UserManager userManager) {
        System.out.print("Nom d'utilisateur: ");
        String utilisateur = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String newMotDePasse = scanner.nextLine();
        System.out.print("Répéter mot de passe: ");
        String repeatMotDePasse = scanner.nextLine();

        if (newMotDePasse.equals(repeatMotDePasse)) {
            if (userManager.ajouterUtilisateur(utilisateur, newMotDePasse)) {
                System.out.println("Utilisateur créé avec succès.");
            } else {
                System.out.println("L'utilisateur existe déjà.");
            }
        } else {
            System.out.println("Les mots de passes ne correspondent pas...");
        }
    }

    // Afficher les événements
    private static void afficherEvenements(CalendarManager calendar, Scanner scanner) {
        System.out.println("\n=== Menu de visualisation d'Événements ===");
        System.out.println("1 - Afficher TOUS les événements");
        System.out.println("2 - Afficher les événements d'un MOIS précis");
        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
        System.out.println("4 - Afficher les événements d'un JOUR précis");
        System.out.println("5 - Retour");
        System.out.print("Votre choix : ");
    
        String choix = scanner.nextLine();
    
        // Création de la Map d'actions
        Map<String, Runnable> actionsMenu = new HashMap<>();
        actionsMenu.put("1", calendar::afficherEvenements);
    
        actionsMenu.put("2", () -> {
            System.out.print("Entrez l'année (AAAA) : ");
            int anneeMois = Integer.parseInt(scanner.nextLine());
            System.out.print("Entrez le mois (1-12) : ");
            int mois = Integer.parseInt(scanner.nextLine());
    
            LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
            LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);
    
            afficherListe(calendar.eventsDansPeriode(debutMois, finMois));
        });
    
        actionsMenu.put("3", () -> {
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
        });
    
        actionsMenu.put("4", () -> {
            System.out.print("Entrez l'année (AAAA) : ");
            int anneeJour = Integer.parseInt(scanner.nextLine());
            System.out.print("Entrez le mois (1-12) : ");
            int moisJour = Integer.parseInt(scanner.nextLine());
            System.out.print("Entrez le jour (1-31) : ");
            int jour = Integer.parseInt(scanner.nextLine());
    
            LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
            LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);
    
            afficherListe(calendar.eventsDansPeriode(debutJour, finJour));
        });
    
        // Action pour "Retour" ou message en cas de choix invalide
        actionsMenu.getOrDefault(choix, () -> System.out.println("Choix invalide")).run();
    }
    
    // Ajouter un rendez-vous personnel
    private static void ajouterRendezVous(Scanner scanner, CalendarManager calendar, String utilisateur) {
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
        calendar.ajouterEvent(new RendezVous(new TitreEvenement(titre), new Proprietaire(utilisateur), new DateEvenement(LocalDateTime.of(annee, mois, jour, heure, minute)), new DureeEvenement(duree)));
        System.out.println("Événement ajouté.");
    }

    // Ajouter une réunion
    private static void ajouterReunion(Scanner scanner, CalendarManager calendar, String utilisateur) {
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
                        
                        String participants = utilisateur;
                        
                        boolean encore = true;
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
    private static void ajouterEvenementPeriodique(Scanner scanner, CalendarManager calendar, String utilisateur) {
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

                        calendar.ajouterEvent(new EvenementPeriodique(new TitreEvenement(titre3), new Proprietaire(utilisateur), new DateEvenement(LocalDateTime.of(annee3, moisRdv3, jourRdv3, heure3, minute3)), new DureeEvenement(0), new FrequenceJours(frequence)));
                        System.out.println("Événement ajouté.");
    }

    // Déconnexion
    private static void seDeconnecter() {
        System.out.println("Déconnexion...");
    }

    // Afficher le menu de gestion des événements
    private static void afficherMenuEvenements() {
        System.out.println("\n=== Menu Gestionnaire d'Événements ===");
        System.out.println("1 - Voir les événements");
        System.out.println("2 - Ajouter un rendez-vous perso");
        System.out.println("3 - Ajouter une réunion");
        System.out.println("4 - Ajouter un évènement périodique");
        System.out.println("5 - Se déconnecter");
    }

    // Afficher le message de bienvenue
    private static void afficherBienvenue() {
        System.out.println("  _____         _                   _                __  __");
        System.out.println(" / ____|       | |                 | |              |  \\/  |");
        System.out.println("| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
        System.out.println("| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
        System.out.println("| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
        System.out.println(" \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
        System.out.println("                                                                                   __/ |");
        System.out.println("                                                                                  |___/");
        System.out.println("1 - Se connecter");
        System.out.println("2 - Créer un compte");
        System.out.println("Choix : ");
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
