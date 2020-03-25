package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LockerTest {

    @Nested
    class when_store_in {

        @Test
        public void should_success_and_return_ticket_given_locker_is_empty_then_() {
            //given
            Locker locker = new Locker();
            //when
            Ticket ticket = locker.storeIn();
            //then
            assertThat(ticket.id).isNotNull();
            assertThat(ticket.blockNumber).isEqualTo(0);
        }

        @Test
        public void should_success_and_return_ticket_with_locker_no_6_given_the_first_five_blocks_are_occupied() {
            //given
            Locker locker = new Locker();
            for (int i = 0; i < 5; i++) {
                locker.storeIn();
            }
            //when
            Ticket ticket = locker.storeIn();
            //then
            assertThat(ticket.id).isNotNull();
            assertThat(ticket.blockNumber).isEqualTo(5);
        }

        @Test
        public void should_success_and_return_ticket_with_locker_no_1_given_the_last_five_blocks_are_occupied() {
            //given
            Locker locker = new Locker();
            for (int i = 14; i <= 18; i++) {
                locker.storeInIndex(i);
            }
            //when
            Ticket ticket = locker.storeIn();
            //then
            assertThat(ticket.id).isNotNull();
            assertThat(ticket.blockNumber).isEqualTo(0);
        }

        @Test
        public void should_throw_exception_given_the_locker_is_full() {
            Locker locker = new Locker();
            for(int i = 0; i < 19; i++) {
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
            Locker locker = new Locker();
            Ticket ticket = locker.storeIn();
            //when
            int blockNumber = locker.takeOut(ticket.id);
            //then
            assertThat(blockNumber).isEqualTo(ticket.blockNumber);
        }

        @Test
        public void should_success_given_store_two_and_take_out_in_different_order_with_valid_ticket() {
            //given
            Locker locker = new Locker();
            Ticket ticket1 = locker.storeIn();
            Ticket ticket2 = locker.storeIn();
            //when
            int blockNumber2 = locker.takeOut(ticket2.id);
            int blockNumber1 = locker.takeOut(ticket1.id);
            //then
            assertThat(blockNumber1).isEqualTo(ticket1.blockNumber);
            assertThat(blockNumber2).isEqualTo(ticket2.blockNumber);
        }

        @Test
        public void should_fail_given_invalid_ticket() {
            //given
            Locker locker = new Locker();

            //when
            int blockNumber = locker.takeOut("invalid ticket id");
            //then
            assertThat(blockNumber).isEqualTo(-1);
        }

        @Test
        public void should_fail_given_used_ticket() {
            //given
            Locker locker = new Locker();
            Ticket ticket = locker.storeIn();
            locker.takeOut(ticket.id);
            //when
            int blockNumber = locker.takeOut(ticket.id);
            //then
            assertThat(blockNumber).isEqualTo(-1);
        }
    }
}
