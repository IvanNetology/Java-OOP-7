package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {
    private TicketRepository repo = new TicketRepository();
    private Ticket ticket1 = new Ticket(1, 3000, "DME", "NYS", 100);
    private Ticket ticket2 = new Ticket(2, 8000, "VKO", "LAX", 250);

    @Test
    public void saveOneTicket() {
        repo.save(ticket1);

        Ticket[] expected = new Ticket[]{ticket1};
        Ticket[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void saveTwoTicket() {
        repo.save(ticket1);
        repo.save(ticket2);

        Ticket[] expected = new Ticket[]{ticket1, ticket2};
        Ticket[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findZero() {
        repo.findAll();

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findOneTicket() {
        repo.save(ticket1);

        repo.findAll();

        Ticket[] expected = new Ticket[]{ticket1};
        Ticket[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeBookByIdWhereOneTicket() {
        repo.save(ticket1);

        repo.removeById(1);

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeBookByIdWhereTwoTicket() {
        repo.save(ticket1);
        repo.save(ticket2);

        repo.removeById(1);

        Ticket[] expected = new Ticket[]{ticket2};
        Ticket[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }
}