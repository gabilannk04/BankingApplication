package coe528.project;

import java.io.IOException;

public class SilverLevel extends CustomerState {
    public SilverLevel (Customer c) {
        super(c);
        this.defineLevel = "Silver";
        this.defineFee = 20.00;
    }

    @Override
    protected void currToSilver() {
        temp.setStateLevel(new SilverLevel(temp));
    }
    @Override
    protected void currToGold() {
        temp.setStateLevel(new GoldLevel(temp));
    }

    @Override
    protected void currToPlat() {
        temp.setStateLevel(new PlatinumLevel(temp));

    }

}
