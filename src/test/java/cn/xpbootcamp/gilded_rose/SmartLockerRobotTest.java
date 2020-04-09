package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exceptions.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exceptions.LockerFullException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SmartLockerRobotTest {

    private SmartLockerRobot smartLockerRobot;

    @Nested
    class when_store_bags_with_robot {
        @Test
        void should_success_and_return_ticket_when_store_bag_with_robot_given_the_locker_is_empty() {
            List<Locker> lockers = new ArrayList<>();
            lockers.add(new Locker(2));
            smartLockerRobot = new SmartLockerRobot(lockers);

            final Ticket ticket = smartLockerRobot.store(new Bag());
            assertThat(ticket).isNotNull();
        }

        @Test
        void should_successfully_store_the_bag_in_the_1st_locker_when_store_bag_with_robot_given_the_two_lockers_and_1st_one_has_5_empty_spaces_while_2nd_one_has_4() {
            List<Locker> lockers = new ArrayList<>();
            final Locker firstLocker = getLockerWithEmptySpace(5);
            lockers.add(firstLocker);
            lockers.add(getLockerWithEmptySpace(4));
            smartLockerRobot = new SmartLockerRobot(lockers);

            final Bag bagIn = new Bag();
            final Ticket ticket = smartLockerRobot.store(bagIn);
            final Bag bagOut = firstLocker.takeOut(ticket);
            assertThat(bagOut).isEqualTo(bagIn);
        }

        @Test
        void should_successfully_store_the_bag_in_the_2nd_locker_when_store_bag_with_robot_given_the_two_lockers_and_1st_one_has_4_empty_spaces_while_2nd_one_has_5() {
            List<Locker> lockers = new ArrayList<>();
            final Locker secondLocker = getLockerWithEmptySpace(5);
            lockers.add(getLockerWithEmptySpace(4));
            lockers.add(secondLocker);
            smartLockerRobot = new SmartLockerRobot(lockers);

            final Bag bagIn = new Bag();
            final Ticket ticket = smartLockerRobot.store(bagIn);
            final Bag bagOut = secondLocker.takeOut(ticket);
            assertThat(bagOut).isEqualTo(bagIn);
        }

        @Test
        void should_successfully_store_the_bag_when_store_bag_with_robot_given_the_two_lockers_both_have_5_empty_spaces() {
            List<Locker> lockers = new ArrayList<>();
            lockers.add(getLockerWithEmptySpace(5));
            lockers.add(getLockerWithEmptySpace(5));
            smartLockerRobot = new SmartLockerRobot(lockers);

            final Bag bagIn = new Bag();
            final Ticket ticket = smartLockerRobot.store(bagIn);
            Bag bagOut = smartLockerRobot.takeOut(ticket);
            assertThat(bagOut).isEqualTo(bagIn);
        }

        @Test
        void should_store_bag_fail_given_all_the_lockers_are_full() {
            List<Locker> lockers = new ArrayList<>();
            lockers.add(getFullLocker());
            lockers.add(getFullLocker());
            smartLockerRobot = new SmartLockerRobot(lockers);

            assertThatThrownBy(() -> smartLockerRobot.store(new Bag()))
                    .isInstanceOf(LockerFullException.class);
        }
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
            smartLockerRobot = new SmartLockerRobot(lockers);

            Bag bagOut = smartLockerRobot.takeOut(ticket);
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
            smartLockerRobot = new SmartLockerRobot(lockers);

            Bag bagOut = smartLockerRobot.takeOut(ticket);
            assertThat(bagOut).isEqualTo(bagIn);
        }

        @Test
        void should_throw_error_given_an_invalid_ticket() {
            List<Locker> lockers = new ArrayList<>();
            lockers.add(new Locker(1));
            lockers.add(new Locker(2));
            smartLockerRobot = new SmartLockerRobot(lockers);

            final Ticket fakeTicket = new Ticket();
            assertThatThrownBy(() -> smartLockerRobot.takeOut(fakeTicket))
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
