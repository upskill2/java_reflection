package exercise.session6_modifiers.auction;

import java.io.Serializable;

import static exercise.session6_modifiers.auction.Utils.*;

public class Main {
    public static void main (String[] args) throws ClassNotFoundException {
/*        runAuction ();
        printClassModifiers (Serializable.class);
        System.out.println ("---------------------");
        printClassModifiers (Class.forName ("exercise.session6_modifiers.auction.Auction$Builder$BidImpl"));
        System.out.println ("---------------------");
        printMethodModifiers (Class.forName ("exercise.session6_modifiers.auction.Auction"));*/

        printFieldsAccessModifiers (Auction.class);

    }


    public static void runAuction(){
        Auction auction = new Auction ();
        auction.stopAuction ();

        Bid bid1 = Bid.builder ()
                .withBidderName ("Bidder1")
                .withPrice (100)
                .build ();
        auction.addBid (bid1);

        Bid bid2 = Bid.builder ()
                .withBidderName ("Bidder2")
                .withPrice (200)
                .build ();
        auction.addBid (bid2);

        auction.stopAuction ();

        System.out.println (auction.getAllBids ());
        System.out.println (String.format ("Highest bid: %s", auction.getHighestBid ().get ()));
    }

}
