package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket implements Comparable<Ticket>{
    private int id;
    private int price;
    private String airportFrom;
    private String airportTo;
    private int flightTime;

    @Override
    public int compareTo(Ticket o) {
        return this.price - o.price;
    }
}
