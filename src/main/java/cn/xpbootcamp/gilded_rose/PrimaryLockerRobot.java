package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exceptions.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exceptions.LockerFullException;

import java.util.List;

public class PrimaryLockerRobot {

    public Ticket store(List<Locker> lockers, Bag bag) {
        for (Locker locker : lockers) {
            try {
                return locker.store(bag);
            } catch (LockerFullException ignored) {
            }
        }
        throw new LockerFullException();
    }

    public Bag takeOut(List<Locker> lockers, Ticket ticket) {
        for (Locker locker : lockers) {
            try {
                return locker.takeOut(ticket);
            } catch (InvalidTicketException ignored) {
            }
        }
        throw new InvalidTicketException();
    }
}
