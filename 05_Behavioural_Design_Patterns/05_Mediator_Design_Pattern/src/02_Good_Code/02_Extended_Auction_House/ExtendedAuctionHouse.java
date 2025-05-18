package Good_Code.Extended_Auction_House;

import Good_Code.Core_Classes.*;

public class ExtendedAuctionHouse extends AuctionHouse {
    private
    long biddingEndTime;  // Timestamp when bidding ends
    public
    ExtendedAuctionHouse(long biddingEndTime) {
        this.biddingEndTime = biddingEndTime;
    }
    @Override public void placeBid(Bidder bidder, int amount) {
        // Check if bidding time is still open
        if (System.currentTimeMillis() > biddingEndTime) {
            System.out.println("Bidding time is over. No more bids accepted.");
            return;
        }
        // Log the bid
        System.out.println("LOG: " + bidder.getName() + " is bidding " + amount);
        // Delegate to the original mediator logic to notify other bidders
        super.placeBid(bidder, amount);
    }
}