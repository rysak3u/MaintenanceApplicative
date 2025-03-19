package src.main.calendar.event;

import java.util.Objects;

public class TitreEvenement {
    private final String titre;

    public TitreEvenement(String titre) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide.");
        }
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitreEvenement that = (TitreEvenement) o;
        return titre.equals(that.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre);
    }

    @Override
    public String toString() {
        return titre;
    }
}
