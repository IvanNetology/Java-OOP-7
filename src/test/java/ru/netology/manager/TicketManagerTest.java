package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketByTimeAscComparator;
import ru.netology.repository.TicketRepository;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TicketManagerTest {
    private TicketRepository repo = new TicketRepository();
    private TicketManager manager = new TicketManager(repo);
    private Comparator<Ticket> timeComparator = new TicketByTimeAscComparator();

    private Ticket ticket1 = new Ticket(1, 8300, "DME", "NOZ", 240);
    private Ticket ticket2 = new Ticket(2, 3500, "VKO", "SIP", 120);
    private Ticket ticket3 = new Ticket(3, 7500, "DME", "NOZ", 250);
    private Ticket ticket4 = new Ticket(4, 27500, "SVO", "NYS", 420);
    private Ticket ticket5 = new Ticket(5, 8000, "DME", "NOZ", 230);

    @Test
    public void shouldWhereNoTicket() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("DME", "NOZ");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldWhereOneTicketNoMatch() {
        repo.save(ticket2);

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("DME", "NOZ");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldWhereOneTicketOneMatch() {
        repo.save(ticket1);

        Ticket[] expected = new Ticket[]{ticket1};
        Ticket[] actual = manager.searchBy("DME", "NOZ");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldWhereManyTicketNoMatch() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("VKO", "NOZ");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldWhereManyTicketOneMatch() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);

        Ticket[] expected = new Ticket[]{ticket2};
        Ticket[] actual = manager.searchBy("VKO", "SIP");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldWhereManyTicketManyMatch() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);

        Ticket[] expected = new Ticket[]{ticket3, ticket5, ticket1};
        Ticket[] actual = manager.searchBy("DME", "NOZ");

        assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldByTimeWhereNoTicket() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("DME", "NOZ", timeComparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldByTimeWhereOneTicketNoMatch() {
        repo.save(ticket2);

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("DME", "NOZ", timeComparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldByTimeWhereOneTicketOneMatch() {
        repo.save(ticket1);

        Ticket[] expected = new Ticket[]{ticket1};
        Ticket[] actual = manager.searchBy("DME", "NOZ", timeComparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldByTimeWhereManyTicketNoMatch() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("VKO", "NOZ", timeComparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldByTimeWhereManyTicketOneMatch() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);

        Ticket[] expected = new Ticket[]{ticket2};
        Ticket[] actual = manager.searchBy("VKO", "SIP", timeComparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldByTimeWhereManyTicketManyMatch() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);

        Ticket[] expected = new Ticket[]{ticket5, ticket1, ticket3};
        Ticket[] actual = manager.searchBy("DME", "NOZ", timeComparator);

        assertArrayEquals(expected, actual);
    }
}