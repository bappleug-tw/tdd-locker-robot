package cn.xpbootcamp.gilded_rose;

public class Ticket {
    private String id;
    private int blockNumber;

    public Ticket(int blockNumber) {
        this.id = HashUtils.generate();
        this.blockNumber = blockNumber;
    }

    public String getId() {
        return id;
    }

    public int getBlockNumber() {
        return blockNumber;
    }
}
