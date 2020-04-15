package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exceptions.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exceptions.LockerFullException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SuperLockerRobotTest {
    private SuperLockerRobot superLockerRobot;

    @Nested
    class when_store_bags_with_super_robot {
        @Test
        void should_success_and_return_ticket_when_store_bag_with_robot_given_the_locker_is_empty() {
            List<Locker> lockers = new ArrayList<>();
            lockers.add(new Locker(2));
            superLockerRobot = new SuperLockerRobot(lockers);

            final Ticket ticket = superLockerRobot.store(new Bag());
            assertThat(ticket).isNotNull();
        }

        @Test
        void should_successfully_store_the_bag_in_the_1st_locker_when_store_bag_given_two_lockers_and_1st_has_two_thirds_empty_rate_while_2nd_has_one_third() {
            List<Locker> lockers = new ArrayList<>();
            final Locker firstLocker = getLocker(3, 2);
            lockers.add(firstLocker);
            lockers.add(getLocker(3, 1));
            superLockerRobot = new SuperLockerRobot(lockers);

            final Bag bagIn = new Bag();
            final Ticket ticket = superLockerRobot.store(bagIn);
            final Bag bagOut = firstLocker.takeOut(ticket);
            assertThat(bagOut).isEqualTo(bagIn);
        }

        @Test
        void should_successfully_store_the_bag_in_the_2st_locker_when_store_bag_given_two_lockers_and_1st_has_one_fourth_empty_rate_while_2nd_has_two_thirds() {
            List<Locker> lockers = new ArrayList<>();
            lockers.add(getLocker(4, 1));
            final Locker secondLocker = getLocker(3, 1);
            lockers.add(secondLocker);
            superLockerRobot = new SuperLockerRobot(lockers);

            final Bag bagIn = new Bag();
            final Ticket ticket = superLockerRobot.store(bagIn);
            final Bag bagOut = secondLocker.takeOut(ticket);
            assertThat(bagOut).isEqualTo(bagIn);
        }

        @Test
        void should_successfully_store_the_bag_in_the_1st_locker_when_store_bag_given_two_lockers_both_have_one_third_empty_rate() {
            List<Locker> lockers = new ArrayList<>();
            final Locker firstLocker = getLocker(6, 2);
            lockers.add(firstLocker);
            lockers.add(getLocker(3, 1));
            superLockerRobot = new SuperLockerRobot(lockers);

            final Bag bagIn = new Bag();
            final Ticket ticket = superLockerRobot.store(bagIn);
            final Bag bagOut = firstLocker.takeOut(ticket);
            assertThat(bagOut).isEqualTo(bagIn);
        }

        @Test
        void should_store_bag_fail_given_all_the_lockers_are_full() {
            List<Locker> lockers = new ArrayList<>();
            lockers.add(getFullLocker());
            lockers.add(getFullLocker());
            superLockerRobot = new SuperLockerRobot(lockers);

            assertThatThrownBy(() -> superLockerRobot.store(new Bag()))
                    .isInstanceOf(LockerFullException.class);
        }
    }

    private Locker getLocker(int capacity, int emptySpace) {
        Locker locker = new Locker(capacity);
        for (int i = 0; i < capacity - emptySpace; i++) {
            locker.store(new Bag());
        }
        return locker;
    }

    @Nested
    class when_take_out_bag_from_robot {

        @Test
        void should_take_out_the_bag_from_the_first_locker_given_a_valid_ticket_from_the_first_locker() {
            Bag bagIn = new Bag();
            final Locker locker = new Locker(2);
            final Ticket ticket = locker.store(bagIn);
            List<Locker> lockers = new ArrayList<>();
            lockers.add(locker);
            lockers.add(new Locker(1));
            superLockerRobot = new SuperLockerRobot(lockers);

            Bag bagOut = superLockerRobot.takeOut(ticket);
            assertThat(bagOut).isEqualTo(bagIn);
        }

        @Test
        void should_take_out_the_bag_from_the_second_locker_given_a_valid_ticket_from_the_second_locker() {
            Bag bagIn = new Bag();
            final Locker locker = new Locker(2);
            final Ticket ticket = locker.store(bagIn);
            List<Locker> lockers = new ArrayList<>();
            lockers.add(new Locker(1));
            lockers.add(locker);
            superLockerRobot = new SuperLockerRobot(lockers);

            Bag bagOut = superLockerRobot.takeOut(ticket);
            assertThat(bagOut).isEqualTo(bagIn);
        }

        @Test
        void should_throw_error_given_an_invalid_ticket() {
            List<Locker> lockers = new ArrayList<>();
            lockers.add(new Locker(1));
            lockers.add(new Locker(2));
            superLockerRobot = new SuperLockerRobot(lockers);

            final Ticket fakeTicket = new Ticket();
            assertThatThrownBy(() -> superLockerRobot.takeOut(fakeTicket))
                    .isInstanceOf(InvalidTicketException.class);
        }
    }

    private Locker getFullLocker() {
        Locker locker = new Locker(1);
        locker.store(new Bag());
        return locker;
    }

    private Locker getLockerWithEmptySpace(int emptySpace) {
        Locker locker = new Locker(emptySpace + 1);
        locker.store(new Bag());
        return locker;
    }
}
