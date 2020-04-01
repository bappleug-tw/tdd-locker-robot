package cn.xpbootcamp.gilded_rose;

import java.util.List;

public class PrimaryLockerRobot {

    public Ticket storeBag(List<Locker> lockers, Bag bag) {
        for (Locker locker : lockers) {
            try {
                return locker.store(bag);
            } catch (LockerFullException ignored) {
            }
        }
        throw new LockerFullException();
    }
}
