package src.main.calendar.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ConnectionMenuManager {
    private final Scanner scanner;
    private final UserManager userManager;

    public ConnectionMenuManager(Scanner scanner, UserManager userManager) {
        this.scanner = scanner;
        this.userManager = userManager;
    }
    public void afficherMenuGestionConnection(){
        afficherBienvenue();
        System.out.print("Votre choix : ");
        String choix = scanner.nextLine();
        traiterChoixMenu(choix);
    }
    public void traiterChoixMenu(String choix){
        Map<String, Runnable> menuActions = new HashMap<>();
        menuActions.put("1", this::seConnecter);
        menuActions.put("2", this::creerCompte);
        menuActions.getOrDefault(choix, () -> System.out.println("Choix invalide")).run();
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
    private  void seConnecter() {
        System.out.print("Nom d'utilisateur: ");
        String utilisateur = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String motDePasse = scanner.nextLine();
    
        if (userManager.seConnecter(utilisateur, motDePasse)) {
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
        }
    }

    // Fonction pour créer un compte
    private void creerCompte() {
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
}
