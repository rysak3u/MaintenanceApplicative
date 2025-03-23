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
        ConnectionMenuManager menuConnection = new ConnectionMenuManager(scanner, userManager);
        menuConnection.afficherMenuGestionConnection();
    }

    public void afficherMenuEvenements() {
        ActionMenuManager actionMenuManager = new ActionMenuManager(scanner, userManager, calendar);
        actionMenuManager.afficherActionMenu();
    } 
}