package com.gathergrid.gathergridfeatures.domain;

import com.gathergrid.gathergridfeatures.domain.enums.TicketType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float price;
    private int quantityAvailable;
    @Enumerated(EnumType.STRING)
    private TicketType type;

    @ManyToOne
    private Event event;

    @OneToMany(mappedBy = "ticket")
    private List<Reservation> reservations = new ArrayList<>();

    public Ticket() {}

    public Ticket(float price, int quantityAvailable, TicketType type) {
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.type = type;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public long getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Float.compare(price, ticket.price) == 0 && quantityAvailable == ticket.quantityAvailable && type == ticket.type /* && ticket.event.getId() == event.getId() && Objects.equals(event, ticket.event) */;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, quantityAvailable, type, event);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", quantityAvailable=" + quantityAvailable +
                ", type=" + type +
                ", event=" + event +
                ", reservations=" + reservations +
                '}';
    }
}
