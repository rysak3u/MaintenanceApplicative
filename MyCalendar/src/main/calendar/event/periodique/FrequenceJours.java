package src.main.calendar.event.periodique;

public class FrequenceJours{
    private final int frequenceJours;

    public FrequenceJours(int frequenceJours){
        this.frequenceJours = frequenceJours;
    }
    public int getFrequenceJours() {
        return frequenceJours;
    }
    @Override
    public String toString() {
        return frequenceJours+"";
    }
}