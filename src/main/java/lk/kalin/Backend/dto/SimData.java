package lk.kalin.Backend.dto;

import lk.kalin.Backend.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimData {
    private int sold;
    private int Due;
    private int available;
    private Ticket[] tickets;

    public int getAvailable() {
        return available;
    }

    public int getDue() {
        return Due;
    }

    public int getSold() {
        return sold;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setDue(int due) {
        Due = due;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }
}
