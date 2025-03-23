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
        Map<String, Runnable> menuActions = new HashMap<>();
        menuActions.put("1", () -> seConnecter());
        menuActions.put("2", () -> creerCompte());

        System.out.print("Votre choix : ");
        String choix = scanner.nextLine();
        menuActions.getOrDefault(choix, () -> System.out.println("Choix invalide")).run();
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
