package src.main.calendar.event.reunion;

public class Lieu {
    private final String value;

    public Lieu(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}