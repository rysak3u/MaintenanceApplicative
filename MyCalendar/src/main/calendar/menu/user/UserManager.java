package src.main.calendar.menu.user;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private List<User> utilisateurs;
    private User currentUser = null;
    public UserManager() {
        this.utilisateurs = new ArrayList<>();

    }

    public boolean verifierConnexion(String username, String password) {
        for (User user : utilisateurs) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean ajouterUtilisateur(String username, String password) {
        if (trouverUtilisateur(username) == null) {
            utilisateurs.add(new User(username, password));
            return true;
        }
        return false;  // L'utilisateur existe déjà
    }

    public User trouverUtilisateur(String username) {
        for (User user : utilisateurs) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;  // Aucun utilisateur trouvé
    }

    public List<User> getUtilisateurs() {
        return utilisateurs;
    }
    public User getCurrentUser(){
        return this.currentUser;
    }
    public void setCurrentUser(User newUser){
        this.currentUser = newUser;
    }
    public boolean seConnecter(String utilisateur, String motDePasse) {
        if (verifierConnexion(utilisateur, motDePasse)) {
            setCurrentUser(trouverUtilisateur(utilisateur));
            return true;
        }
        return false;
    }
}
