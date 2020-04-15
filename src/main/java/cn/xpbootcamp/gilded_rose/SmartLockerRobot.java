package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exceptions.InvalidTicketException;

import java.util.Comparator;
import java.util.List;

public class SmartLockerRobot extends LockerRobot {

    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket store(Bag bag) {
        return lockers.stream().max(Comparator.comparingInt(Locker::getFreeCapacity)).get().store(bag);
    }

}
