package cn.xpbootcamp.gilded_rose;

import java.util.Comparator;
import java.util.List;

public class SuperLockerRobot extends LockerRobot{

    public SuperLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket store(Bag bag) {
        return lockers.stream().max(Comparator.comparingDouble(Locker::getEmptyRate)).get().store(bag);
    }
}
