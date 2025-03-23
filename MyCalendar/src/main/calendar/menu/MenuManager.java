package src.main.calendar.menu;


import java.util.Scanner;

import src.main.calendar.CalendarManager;

public class MenuManager {
    private final Scanner scanner;
    private final UserManager userManager;
    private final CalendarManager calendar;

    public MenuManager(Scanner scanner, UserManager userManager, CalendarManager calendar) {
        this.scanner = scanner;
        this.userManager = userManager;
        this.calendar = calendar;
    }
    public void afficherMenuPrincipal() {
        afficherBienvenue();
        ConnectionMenuManager menuConnection = new ConnectionMenuManager(scanner, userManager);
        menuConnection.afficherMenuGestionConnection();
    }
    private  void afficherBienvenue() {
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
    public void afficherMenuEvenements() {
        System.out.println("\nBonjour, " + userManager.getCurrentUser());
        afficherOptionsEvenements();
        ActionMenuManager actionMenuManager = new ActionMenuManager(scanner, userManager, calendar);
        actionMenuManager.afficherActionMenu();
    } 
        // Afficher le menu de gestion des événements
        private void afficherOptionsEvenements() {
            System.out.println("\n=== Menu Gestionnaire d'Événements ===");
            System.out.println("1 - Voir les événements");
            System.out.println("2 - Ajouter un rendez-vous perso");
            System.out.println("3 - Ajouter une réunion");
            System.out.println("4 - Ajouter un évènement périodique");
            System.out.println("5 - Se déconnecter");
        }
}