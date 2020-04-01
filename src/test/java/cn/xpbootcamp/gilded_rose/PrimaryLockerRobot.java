package cn.xpbootcamp.gilded_rose;

import java.util.List;

public class PrimaryLockerRobot {

    public Ticket storeBag(List<Locker> lockers, Bag bag) {
        return lockers.get(0).store(bag);
    }
}
