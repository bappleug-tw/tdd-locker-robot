package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LockerTest {

    @Nested
    class when_store_bags {

        @Test
        public void should_success_and_return_ticket_given_locker_is_empty() {
            //given
            Locker locker = new Locker(19);
            //when
            Bag bag = new Bag();
            Ticket ticket = locker.store(bag);
            //then
            assertThat(ticket).isNotNull();
        }

        @Test
        public void should_throw_exception_given_the_locker_is_full() {
            Locker locker = new Locker(1);
            Bag bag1 = new Bag();
            locker.store(bag1);
            Bag bag2 = new Bag();
            assertThatThrownBy(() -> locker.store(bag2))
                    .isInstanceOf(LockerFullException.class);
        }
    }

    @Nested
    class when_take_out_bags {

        @Test
        public void should_success_given_store_one_and_take_out_with_valid_ticket() {
            //given
            Locker locker = new Locker(19);
            Bag bagIn = new Bag();
            Ticket ticket = locker.store(bagIn);
            //when
            Bag bagOut = locker.takeOut(ticket);
            //then
            assertThat(bagOut).isEqualTo(bagIn);
        }

        @Test
        public void should_success_given_store_two_and_take_out_in_different_order_with_valid_ticket() {
            //given
            Locker locker = new Locker(19);
            Bag bagIn1 = new Bag();
            Bag bagIn2 = new Bag();
            Ticket ticket1 = locker.store(bagIn1);
            Ticket ticket2 = locker.store(bagIn2);
            //when
            Bag bagOut2 = locker.takeOut(ticket2);
            Bag bagOut1 = locker.takeOut(ticket1);
            //then
            assertThat(bagOut1).isEqualTo(bagIn1);
            assertThat(bagOut2).isEqualTo(bagIn2);
        }

        @Test
        public void should_fail_given_invalid_ticket() {
            Locker locker = new Locker(19);

            assertThatThrownBy(() -> locker.takeOut(new Ticket()))
                    .isInstanceOf(InvalidTicketException.class);
        }

        @Test
        public void should_fail_given_used_ticket() {
            Locker locker = new Locker(19);
            Bag bag = new Bag();
            Ticket ticket = locker.store(bag);
            locker.takeOut(ticket);

            assertThatThrownBy(() -> locker.takeOut(ticket))
                    .isInstanceOf(InvalidTicketException.class);
        }
    }
}
