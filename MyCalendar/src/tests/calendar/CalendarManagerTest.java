package src.tests.calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.main.calendar.event.*;
import src.main.calendar.event.periodique.EvenementPeriodique;
import src.main.calendar.event.periodique.FrequenceJours;
import src.main.calendar.event.rendezvous.RendezVous;
import src.main.calendar.event.reunion.Lieu;
import src.main.calendar.event.reunion.Participants;
import src.main.calendar.event.reunion.Proprietaire;
import src.main.calendar.event.reunion.Reunion;
import src.main.calendar.menu.CalendarManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalendarManagerTest {
    private CalendarManager manager;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        manager = new CalendarManager();
        now = LocalDateTime.now();
    }

    @Test
    void testAjouterEventEtAffichage() {
        Event rdv = new RendezVous(new TitreEvenement("Dentiste"), new Proprietaire("Alice"),
                new DateEvenement(now.plusDays(1)), new DureeEvenement(60));

        manager.ajouterEvent(rdv);
        assertEquals(1, manager.getEvents().size());
        assertEquals("("+rdv.getId()+")" +"RDV : Dentiste à " + now.plusDays(1), manager.getEvents().get(0).description());
    }

    @Test
    void testEventsDansPeriode() {
        Event rdv = new RendezVous(new TitreEvenement("Dentiste"), new Proprietaire("Alice"),
                new DateEvenement(now.plusDays(1)), new DureeEvenement(60));

        Event reunion = new Reunion(new TitreEvenement("Meeting"), new Proprietaire("Bob"),
                new DateEvenement(now.plusDays(2)), new DureeEvenement(90),
                new Lieu("Bureau 101"), new Participants(List.of("Charlie")));

        Event eventPeriodique = new EvenementPeriodique(new TitreEvenement("Gym"), new Proprietaire("Alice"),
                new DateEvenement(now.minusDays(5)), new DureeEvenement(30), new FrequenceJours(2));

        Event eventPasDansPeriode = new RendezVous(new TitreEvenement("Dentiste"), new Proprietaire("Alice"),
        new DateEvenement(now.plusDays(6)), new DureeEvenement(60));
        manager.ajouterEvent(rdv);
        manager.ajouterEvent(reunion);
        manager.ajouterEvent(eventPeriodique);
        manager.ajouterEvent(eventPasDansPeriode);

        List<Event> result = manager.eventsDansPeriode(now, now.plusDays(3));
        assertTrue(result.contains(rdv));
        assertTrue(result.contains(reunion));
        assertTrue(result.contains(eventPeriodique));
        assertFalse(result.contains(eventPasDansPeriode));
    }
}