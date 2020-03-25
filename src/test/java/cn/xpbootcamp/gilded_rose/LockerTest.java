package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LockerTest {

    @Nested
    class when_initialize_locker {

        @Test
        public void should_be_able_to_store_at_most_5_items_at_the_same_time_given_locker_capacity_is_5() {
            Locker locker = new Locker(5);
            for (int i = 0; i < 5; i++) {
                locker.storeIn();
            }
            assertThatThrownBy(locker::storeIn)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("locker is full");
        }

        @Test
        public void should_throw_error_given_initial_capacity_equals_to_minus_1() {
            assertThatThrownBy(() -> new Locker(-1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("capacity must be a positive value");
        }
    }

    @Nested
    class when_store_in {

        @Test
        public void should_success_and_return_ticket_given_locker_is_empty_then_() {
            //given
            Locker locker = new Locker(19);
            //when
            Ticket ticket = locker.storeIn();
            //then
            assertThat(ticket.getId()).isNotNull();
            assertThat(ticket.getBlockNumber()).isEqualTo(0);
        }

        @Test
        public void should_success_and_return_ticket_with_locker_no_6_given_the_first_five_blocks_are_occupied() {
            //given
            Locker locker = new Locker(19);
            for (int i = 0; i < 5; i++) {
                locker.storeIn();
            }
            //when
            Ticket ticket = locker.storeIn();
            //then
            assertThat(ticket.getId()).isNotNull();
            assertThat(ticket.getBlockNumber()).isEqualTo(5);
        }

        @Test
        public void should_success_and_return_ticket_with_locker_no_1_given_the_last_five_blocks_are_occupied() {
            //given
            Locker locker = new Locker(19);
            for (int i = 14; i <= 18; i++) {
                locker.storeInIndex(i);
            }
            //when
            Ticket ticket = locker.storeIn();
            //then
            assertThat(ticket.getId()).isNotNull();
            assertThat(ticket.getBlockNumber()).isEqualTo(0);
        }

        @Test
        public void should_throw_exception_given_the_locker_is_full() {
            Locker locker = new Locker(19);
            for (int i = 0; i < 19; i++) {
                locker.storeIn();
            }
            assertThatThrownBy(locker::storeIn)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("locker is full");
        }
    }

    @Nested
    class when_take_out {

        @Test
        public void should_success_given_store_one_and_take_out_with_valid_ticket() {
            //given
            Locker locker = new Locker(19);
            Ticket ticket = locker.storeIn();
            //when
            int blockNumber = locker.takeOut(ticket.getId());
            //then
            assertThat(blockNumber).isEqualTo(ticket.getBlockNumber());
        }

        @Test
        public void should_success_given_store_two_and_take_out_in_different_order_with_valid_ticket() {
            //given
            Locker locker = new Locker(19);
            Ticket ticket1 = locker.storeIn();
            Ticket ticket2 = locker.storeIn();
            //when
            int blockNumber2 = locker.takeOut(ticket2.getId());
            int blockNumber1 = locker.takeOut(ticket1.getId());
            //then
            assertThat(blockNumber1).isEqualTo(ticket1.getBlockNumber());
            assertThat(blockNumber2).isEqualTo(ticket2.getBlockNumber());
        }

        @Test
        public void should_fail_given_invalid_ticket() {
            //given
            Locker locker = new Locker(19);

            //when
            int blockNumber = locker.takeOut("invalid ticket id");
            //then
            assertThat(blockNumber).isEqualTo(-1);
        }

        @Test
        public void should_fail_given_used_ticket() {
            //given
            Locker locker = new Locker(19);
            Ticket ticket = locker.storeIn();
            locker.takeOut(ticket.getId());
            //when
            int blockNumber = locker.takeOut(ticket.getId());
            //then
            assertThat(blockNumber).isEqualTo(-1);
        }
    }
}
