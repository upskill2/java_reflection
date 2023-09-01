package exercise.session4_fields_modification.data;

import java.util.Arrays;
import java.util.Random;

public class UserInterfaceConfig {
    private String titleColor;
    private String titleTest;

    private int titleFontSize;
    private int footerFontSize;
    private double price;
    private short[] titleTextSizes;
    private String[] titleFonts;


    public short[] getTitleTextSizes () {
        return titleTextSizes;
    }

    public String[] getTitleFonts () {
        return titleFonts;
    }

    /*    public UserInterfaceConfig (int titleFontSize) {
            this.titleFontSize = new Random ().nextInt (2000);
        }*/
    public String getTitleColor () {
        return titleColor;
    }


    public String getTitleTest () {
        return titleTest;
    }

    public int getTitleFontSize () {
        return titleFontSize;
    }

    public int getFooterFontSize () {
        return footerFontSize;
    }


    public double getPrice () {
        return price;
    }

    @Override
    public String toString () {
        return "UserInterfaceConfig{" +
                "titleColor='" + titleColor + '\'' +
                ", titleTest='" + titleTest + '\'' +
                ", titleFontSize=" + titleFontSize +
                ", footerFontSize=" + footerFontSize +
                ", price=" + price +
                ", titleTextSizes=" + Arrays.toString (titleTextSizes) +
                ", titleFonts=" + Arrays.toString (titleFonts) +
                '}';
    }
}
