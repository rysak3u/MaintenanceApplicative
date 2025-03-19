package src.main.calendar.event;

import java.util.Objects;

public class Proprietaire {
    private final String nom;

    public Proprietaire(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du propriétaire ne peut pas être vide.");
        }
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proprietaire that = (Proprietaire) o;
        return nom.equals(that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return nom;
    }
}
