package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket[] searchBy(String airportFrom, String airportTo) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, airportFrom, airportTo)) {
                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;
                result = tmp;
                Arrays.sort(result);
            }
        }
        return result;
    }

    public boolean matches(Ticket ticket, String airportFrom, String airportTo) {
        if (ticket.getAirportFrom().contains(airportFrom)) {
            return ticket.getAirportTo().contains(airportTo);
        }
        return false;
    }
}




