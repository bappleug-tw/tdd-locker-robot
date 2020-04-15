package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exceptions.InvalidTicketException;

import java.util.List;

public abstract class LockerRobot {
    protected List<Locker> lockers;

    public LockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public abstract Ticket store(Bag bag);

    public Bag takeOut(Ticket ticket) {
        for (Locker locker : lockers) {
            if(locker.contains(ticket)) {
                return locker.takeOut(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
