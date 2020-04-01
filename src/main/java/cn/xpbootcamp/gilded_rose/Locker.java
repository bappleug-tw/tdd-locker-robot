package cn.xpbootcamp.gilded_rose;

import com.google.common.annotations.VisibleForTesting;

import java.util.HashMap;
import java.util.Map;

public class Locker {

    private int capacity;
    private Map<Ticket, Bag> storedBags = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket store(Bag bag) {
        if (storedBags.size() >= capacity) {
            throw new LockerFullException();
        }
        final Ticket ticket = new Ticket();
        storedBags.put(ticket, bag);
        return ticket;
    }

    public Bag takeOut(Ticket ticket) {
        final Bag bag = storedBags.get(ticket);
        if (bag == null) {
            throw new InvalidTicketException();
        }
        storedBags.remove(ticket);
        return bag;
    }
}
