package src.main.calendar.event;

import java.time.LocalDateTime;
import java.util.Objects;

public class DateEvenement {
    private final LocalDateTime date;

    public DateEvenement(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("La date de l'événement ne peut pas être nulle.");
        }
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateEvenement that = (DateEvenement) o;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return date.toString();
    }
}
