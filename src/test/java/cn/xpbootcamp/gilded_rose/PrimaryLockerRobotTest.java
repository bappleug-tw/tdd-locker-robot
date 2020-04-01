package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PrimaryLockerRobotTest {

    private PrimaryLockerRobot primaryLockerRobot;

    @BeforeEach
    void initRobot() {
        primaryLockerRobot = new PrimaryLockerRobot();
    }

    @Nested
    class when_store_bags {
        @Test
        void should_store_bag_in_the_first_locker_success_and_return_ticket_given_first_locker_has_room() {
            List<Locker> lockers = new ArrayList<>();
            lockers.add(new Locker(2));
            lockers.add(new Locker(2));
            Bag bag = new Bag();
            Ticket ticket = primaryLockerRobot.storeBag(lockers, bag);

            assertThat(ticket).isNotNull();
            assertThat(lockers.get(0).takeOut(ticket)).isEqualTo(bag);
        }

        @Test
        void should_store_bag_in_the_second_locker_success_and_return_ticket_given_only_the_first_locker_is_full() {
            List<Locker> lockers = new ArrayList<>();
            lockers.add(getFullLocker());
            lockers.add(new Locker(2));
            Bag bag = new Bag();
            Ticket ticket = primaryLockerRobot.storeBag(lockers, bag);

            assertThat(ticket).isNotNull();
            assertThat(lockers.get(1).takeOut(ticket)).isEqualTo(bag);
        }

        @Test
        void should_store_bag_fail_given_all_the_lockers_are_full() {
            List<Locker> lockers = new ArrayList<>();
            lockers.add(getFullLocker());
            lockers.add(getFullLocker());

            assertThatThrownBy(() -> primaryLockerRobot.storeBag(lockers, new Bag()))
                    .isInstanceOf(LockerFullException.class);
        }
    }

    @Nested
    class when_take_out_bag {

        @Test
        void should_take_out_the_bag_from_the_first_locker_given_a_valid_ticket_from_the_first_locker() {
            Bag bagIn = new Bag();
            final Locker locker = new Locker(2);
            final Ticket ticket = locker.store(bagIn);

            List<Locker> lockers = new ArrayList<>();
            lockers.add(locker);
            lockers.add(new Locker(1));

            Bag bagOut = primaryLockerRobot.takeOut(lockers, ticket);
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

            Bag bagOut = primaryLockerRobot.takeOut(lockers, ticket);
            assertThat(bagOut).isEqualTo(bagIn);
        }

        @Test
        void should_throw_error_given_an_invalid_ticket() {
            List<Locker> lockers = new ArrayList<>();
            lockers.add(new Locker(1));
            lockers.add(new Locker(2));

            final Ticket fakeTicket = new Ticket();
            assertThatThrownBy(() -> primaryLockerRobot.takeOut(lockers, fakeTicket))
                    .isInstanceOf(InvalidTicketException.class);
        }
    }

    private Locker getFullLocker() {
        Locker locker = new Locker(1);
        locker.store(new Bag());
        return locker;
    }
}
