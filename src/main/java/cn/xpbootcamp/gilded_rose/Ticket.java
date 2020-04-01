package cn.xpbootcamp.gilded_rose;

public class Ticket {
    private String id;

    public Ticket() {
        this.id = HashUtils.generate();
    }

    public String getId() {
        return id;
    }
}
