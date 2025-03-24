package src.main.calendar;

import src.main.calendar.menu.CalendarManager;
import src.main.calendar.menu.menumanager.MenuManager;
import src.main.calendar.menu.user.UserManager;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main().lancerApplication();
    }
    
    public void lancerApplication() {
        CalendarManager calendar = new CalendarManager();
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        userManager.ajouterUtilisateur("Roger", "Chat");
        userManager.ajouterUtilisateur("Pierre", "KiRouhl");
        MenuManager menuManager = new MenuManager(scanner, userManager, calendar);
    
        while (true) {
            if (userManager.getCurrentUser() == null) {
                menuManager.afficherMenuPrincipal();
            } else {
                menuManager.afficherMenuEvenements();
            }
        }
    }
    
}
