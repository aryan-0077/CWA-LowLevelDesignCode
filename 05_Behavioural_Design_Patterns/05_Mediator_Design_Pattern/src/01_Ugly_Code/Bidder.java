package Ugly_Code;

// Messy version: Each bidder handles bidding, logging, and notifications
// directly
public class Bidder {
    String name;
    int bid;
    public Bidder(String name) { this.name = name; }
    // A cluttered method that handles bidding and additional responsibilities
    public void placeBidWithExtras(int amount, Bidder[] bidders) {
        // Update bid and log the bid placement
        this.bid = amount;
        System.out.println("[" + name + "] placed a bid: " + amount);
        System.out.println("LOG: " + name + " bid " + amount + " at " +
                System.currentTimeMillis());


        // Directly notify every other bidder with additional responsibilities
        for (Bidder b : bidders) {
            if (b != this) {
                b.receiveBidWithExtras(this, amount);
            }
        }
    }

    // Cluttered notification method with extra tasks (like simulating an email
    // notification)
    public void receiveBidWithExtras(Bidder bidder, int amount) {
        System.out.println("[" + name + "] is notified: " + bidder.name +
                " placed a bid of " + amount);
        System.out.println("EMAIL: Sending email to " + name +
                " about the bid from " + bidder.name);
    }
}
