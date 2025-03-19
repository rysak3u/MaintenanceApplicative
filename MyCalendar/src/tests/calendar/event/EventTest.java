package src.tests.calendar.event;


import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.calendar.event.*;

public class EventTest {
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
    }

    @Test
    void testConflit() {
        Event rdv = new RendezVous(new TitreEvenement("Dentiste"), new Proprietaire("Alice"),
                new DateEvenement(now.plusHours(1)), new DureeEvenement(60));

        Event autreRdv = new RendezVous(new TitreEvenement("Médecin"), new Proprietaire("Bob"),
                new DateEvenement(now.plusHours(1).plusMinutes(30)), new DureeEvenement(60));

        Event periodicEvent = new EvenementPeriodique(new TitreEvenement("Sport"), new Proprietaire("Alice"),
                new DateEvenement(now.minusDays(3)), new DureeEvenement(30), new FrequenceJours(2));

        assertTrue(rdv.conflit(autreRdv));
        assertFalse(rdv.conflit(periodicEvent));
    }
}

