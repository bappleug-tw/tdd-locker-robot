package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PrimaryLockerRobotTest {
    @Nested
    class when_store_bags {
        @Test
        void should_store_bag_in_the_first_locker_success_and_return_ticket_given_first_locker_has_room(){

            PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot();
            Bag bag = new Bag();
            List<Locker> lockers = new ArrayList<>();
            lockers.add(new Locker(2));
            lockers.add(new Locker(2));
            Ticket ticket = primaryLockerRobot.storeBag(lockers, bag);
            assertThat(ticket).isNotNull();
            assertThat(lockers.get(0).takeOut(ticket)).isEqualTo(bag);

        }
    }
}
