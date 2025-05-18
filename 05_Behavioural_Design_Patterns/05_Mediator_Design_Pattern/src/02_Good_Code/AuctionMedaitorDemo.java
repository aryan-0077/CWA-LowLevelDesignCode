package Good_Code;

import Good_Code.Core_Classes.*;

public class AuctionMedaitorDemo {
    public static void main(String[] args) {
        AuctionMediator auctionHouse = new AuctionHouse();
        Bidder bidder1 = new Bidder("Alice", auctionHouse);
        Bidder bidder2 = new Bidder("Bob", auctionHouse);
        Bidder bidder3 = new Bidder("Charlie", auctionHouse);
        auctionHouse.registerBidder(bidder1);
        auctionHouse.registerBidder(bidder2);
        auctionHouse.registerBidder(bidder3);
        bidder1.placeBid(100);
        bidder2.placeBid(150);
        bidder3.placeBid(200);
    }
}

/*

Output :
Alice placed a bid of 100
Bob is notified: Alice placed a bid of 100
Charlie is notified: Alice placed a bid of 100
Bob placed a bid of 150
Alice is notified: Bob placed a bid of 150
Charlie is notified: Bob placed a bid of 150
Charlie placed a bid of 200
Alice is notified: Charlie placed a bid of 200
Bob is notified: Charlie placed a bid of 200


*/