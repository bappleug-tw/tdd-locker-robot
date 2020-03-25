package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LockerTest {

    @Nested
    class when_store_in {

        @Test
        public void given_locker_is_empty_then_success_and_return_ticket() {
            //given
            Locker locker = new Locker();
            //when
            Ticket ticket = locker.storeIn();
            //then
            assertThat(ticket.id).isNotNull();
            assertThat(ticket.lockerNumber).isEqualTo(0);
        }

        @Test
        public void given_the_first_five_blocks_are_occupied_then_success_and_return_ticket_with_locker_no_6() {
            //given
            Locker locker = new Locker();
            for (int i = 0; i < 5; i++) {
                locker.storeIn();
            }
            //when
            Ticket ticket = locker.storeIn();
            //then
            assertThat(ticket.id).isNotNull();
            assertThat(ticket.lockerNumber).isEqualTo(5);
        }

        @Test
        public void given_the_last_five_blocks_are_occupied_then_success_and_return_ticket_with_locker_no_1() {
            //given
            Locker locker = new Locker();
            for (int i = 14; i <= 18; i++) {
                locker.storeInIndex(i);
            }
            //when
            Ticket ticket = locker.storeIn();
            //then
            assertThat(ticket.id).isNotNull();
            assertThat(ticket.lockerNumber).isEqualTo(0);
        }

        @Test
        public void given_the_locker_is_full_then_throw_exception() {
            Locker locker = new Locker();
            for(int i = 0; i < 19; i++) {
                locker.storeIn();
            }
            assertThatThrownBy(locker::storeIn)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("locker is full");
        }
    }
}
