package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exceptions.InvalidTicketException;

import java.util.Comparator;
import java.util.List;

public class SmartLockerRobot {
    private List<Locker> lockers;

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) {
        return lockers.stream().max(Comparator.comparingInt(Locker::getFreeCapacity)).get().store(bag);
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
