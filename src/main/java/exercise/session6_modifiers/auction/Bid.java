package exercise.session6_modifiers.auction;

import javax.xml.catalog.CatalogFeatures;
import java.io.Serializable;
import java.lang.module.ModuleDescriptor;

public abstract class Bid implements Serializable {

    protected int price;
    protected String bidderName;

    public String getBidderName () {
        return bidderName;
    }

    public int getPrice () {
        return price;
    }

    //Builder pattern
    public static Builder builder () {
        return new Builder ();
    }

    @Override
    public String toString () {
        return "Bid{" +
                "price=" + price +
                ", bidderName='" + bidderName + '\'' +
                '}';
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
                this.price = Builder.this.price;
                this.bidderName = Builder.this.bidderName;
            }
        }
    }
}
