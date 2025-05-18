package Ugly_Code;

public class AuctionUglyDemo {
    public static void main(String[] args) {
        // Creating bidders without a central mediator
        Bidder bidder1 = new Bidder("Alice");
        Bidder bidder2 = new Bidder("Bob");
        Bidder bidder3 = new Bidder("Charlie");
        Bidder[] bidders = {bidder1, bidder2, bidder3};
        // Each bidder is responsible for handling the bid and all associated extras
        bidder1.placeBidWithExtras(100, bidders);
        bidder2.placeBidWithExtras(150, bidders);
        bidder3.placeBidWithExtras(200, bidders);
    }
}

/*

Output :
[Alice] placed a bid: 100
LOG: Alice bid 100 at 1747549229962
[Bob] is notified: Alice placed a bid of 100
EMAIL: Sending email to Bob about the bid from Alice
[Charlie] is notified: Alice placed a bid of 100
EMAIL: Sending email to Charlie about the bid from Alice
[Bob] placed a bid: 150
LOG: Bob bid 150 at 1747549229989
[Alice] is notified: Bob placed a bid of 150
EMAIL: Sending email to Alice about the bid from Bob
[Charlie] is notified: Bob placed a bid of 150
EMAIL: Sending email to Charlie about the bid from Bob
[Charlie] placed a bid: 200
LOG: Charlie bid 200 at 1747549229989
[Alice] is notified: Charlie placed a bid of 200
EMAIL: Sending email to Alice about the bid from Charlie
[Bob] is notified: Charlie placed a bid of 200
EMAIL: Sending email to Bob about the bid from Charlie

*/