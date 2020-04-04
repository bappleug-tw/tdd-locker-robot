package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exceptions.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exceptions.LockerFullException;

import java.util.ArrayList;
import java.util.List;

public class PrimaryLockerRobot {

    private List<Locker> lockers = new ArrayList<>();

    public PrimaryLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) {
        for (Locker locker : lockers) {
            try {
                return locker.store(bag);
            } catch (LockerFullException ignored) {
            }
        }
        throw new LockerFullException();
    }

    public Bag takeOut(Ticket ticket) {
        for (Locker locker : lockers) {
            try {
                return locker.takeOut(ticket);
            } catch (InvalidTicketException ignored) {
            }
        }
        throw new InvalidTicketException();
    }
}
