package exercise.session6_modifiers.auction;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Auction implements Serializable {

    private final List<Bid> bids = new ArrayList<> ();
    private transient volatile boolean isAuctionStarted;

    public synchronized void addBid (Bid bid) {
        this.bids.add (bid);
    }

    public synchronized List<Bid> getAllBids () {
        return Collections.unmodifiableList (bids);
    }

    public synchronized Optional<Bid> getHighestBid () {
        return bids.stream ().max (Comparator.comparing (Bid::getPrice));
    }

    public void StartAuction () {
        this.isAuctionStarted = true;
    }

    public void stopAuction () {
        this.isAuctionStarted = false;
    }

    public boolean isAuctionRunning () {
        return isAuctionStarted;
    }
    //Bid Builder class
    public static class Builder {
        private int price;
        private String bidderName;

        public Builder withPrice (int price) {
            this.price = price;
            return this;
        }

        public Builder withBidderName (String bidderName) {
            this.bidderName = bidderName;
            return this;
        }

        public Bid build () {
            return new Builder.BidImpl ();
        }


        private class BidImpl extends Bid {
            private BidImpl () {
                this.price = Auction.Builder.this.price;
                this.bidderName = Auction.Builder.this.bidderName;
            }
        }
    }
    @Override
    public String toString () {
        return bids.stream ().map (bid -> bid.toString () + "\n").collect (Collectors.joining ());
    }
}
