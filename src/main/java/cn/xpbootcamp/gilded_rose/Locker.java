package cn.xpbootcamp.gilded_rose;

import com.google.common.annotations.VisibleForTesting;

public class Locker {

    private String[] ticketIds;

    public Locker(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("capacity must be a positive value");
        ticketIds = new String[capacity];
    }

    public Ticket storeIn() {
        int index = findFirstAvailableIndex(ticketIds);
        if (index == -1) {
            throw new IllegalStateException("locker is full");
        }
        final Ticket ticket = new Ticket();
        ticket.id = "ticketId" + index;
        ticket.blockNumber = index;
        ticketIds[index] = ticket.id;
        return ticket;
    }

    @VisibleForTesting
    void storeInIndex(int index) {
        ticketIds[index] = "ticketId";
    }

    private int findFirstAvailableIndex(String[] ticketIds) {
        for (int i = 0; i < ticketIds.length; i++) {
            if (ticketIds[i] == null) return i;
        }
        return -1;
    }

    public int takeOut(String ticketId) {
        for (int i = 0; i < 19; i++) {
            if (ticketId.equals(ticketIds[i])) {
                ticketIds[i] = null;
                return i;
            }
        }
        return -1;
    }
}
