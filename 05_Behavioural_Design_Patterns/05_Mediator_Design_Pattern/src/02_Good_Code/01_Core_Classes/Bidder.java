package Good_Code.Core_Classes;

import Good_Code.AuctionMediator;

public class Bidder {
    private String name;
    private AuctionMediator mediator;
    public Bidder(String name, AuctionMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
    public String getName() { return name; }
    public void placeBid(int amount) { mediator.placeBid(this, amount); }
    public void receiveBid(Bidder bidder, int amount) {
        System.out.println(name + " is notified: " + bidder.getName() +
                " placed a bid of " + amount);
    }
}