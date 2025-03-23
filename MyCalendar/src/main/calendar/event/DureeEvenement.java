package src.main.calendar.event;


import java.util.Objects;

public class DureeEvenement {
    private final int dureeMinutes;

    public DureeEvenement(int dureeMinutes) {
        if (dureeMinutes < 0) {
            throw new IllegalArgumentException("La durée de l'événement doit être positive.");
        }
        this.dureeMinutes = dureeMinutes;
    }

    public int getDureeMinutes() {
        return dureeMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DureeEvenement that = (DureeEvenement) o;
        return dureeMinutes == that.dureeMinutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dureeMinutes);
    }

    @Override
    public String toString() {
        return dureeMinutes + " min";
    }
}
