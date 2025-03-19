// package src.main.calendar.event;
// import java.time.LocalDateTime;

// public class Event {
//     public String type; // "RDV_PERSONNEL", "REUNION", "PERIODIQUE"
//     public String title;
//     public String proprietaire;
//     public LocalDateTime dateDebut;
//     public int dureeMinutes;
//     public String lieu; // utilisé seulement pour REUNION
//     public String participants; // séparés par virgules (pour REUNION uniquement)
//     public int frequenceJours; // uniquement pour PERIODIQUE

//     public Event(String type, String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes,
//                  String lieu, String participants, int frequenceJours) {
//         this.type = type;
//         this.title = title;
//         this.proprietaire = proprietaire;
//         this.dateDebut = dateDebut;
//         this.dureeMinutes = dureeMinutes;
//         this.lieu = lieu;
//         this.participants = participants;
//         this.frequenceJours = frequenceJours;
//    }

//      public String description() {
//          String desc = "";
//          if (type.equals("RDV_PERSONNEL")) {
//              desc = "RDV : " + title + " à " + dateDebut.toString();
//          } else if (type.equals("REUNION")) {
//              desc = "Réunion : " + title + " à " + lieu + " avec " + participants;
//          } else if (type.equals("PERIODIQUE")) {
//              desc = "Événement périodique : " + title + " tous les " + frequenceJours + " jours";
//          }
//          return desc;
//      }
// }
package src.main.calendar.event;

public abstract class Event {
    protected final TitreEvenement titre;
    protected final Proprietaire proprietaire;
    protected final DateEvenement dateDebut;
    protected final DureeEvenement duree;

    public Event(TitreEvenement titre, Proprietaire proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        this.titre = titre;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }   

    public TitreEvenement getTitre() {
        return titre;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public DateEvenement getDateDebut() {
        return dateDebut;
    }

    public DureeEvenement getDuree() {
        return duree;
    }
    

    public abstract String description();

}
