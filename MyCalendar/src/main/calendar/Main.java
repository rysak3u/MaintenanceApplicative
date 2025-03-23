package src.main.calendar;

import src.main.calendar.menu.MenuManager;
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

        // Création du menu manager pour gérer l'affichage et les actions
        MenuManager menuManager = new MenuManager(scanner, userManager, calendar);

        while (true) {
            if (userManager.getCurrentUser() == null) {
                menuManager.afficherMenuPrincipal(); // Appel au menu principal
            } else {
                menuManager.afficherMenuEvenements(); // Appel au menu des événements
            }
        }
    }
}
