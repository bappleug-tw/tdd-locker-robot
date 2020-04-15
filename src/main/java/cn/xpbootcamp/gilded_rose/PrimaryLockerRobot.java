package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exceptions.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exceptions.LockerFullException;

import java.util.List;

public class PrimaryLockerRobot extends LockerRobot {

    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket store(Bag bag) {
        for (Locker locker : lockers) {
            try {
                return locker.store(bag);
            } catch (LockerFullException ignored) {
            }
        }
        throw new LockerFullException();
    }
}
