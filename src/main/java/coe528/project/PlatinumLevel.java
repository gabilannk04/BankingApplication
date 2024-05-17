package coe528.project;

public class PlatinumLevel extends CustomerState{
    public PlatinumLevel (Customer c) {
        super(c);
        this.defineLevel = "Platinum";
        this.defineFee = 0.00;
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
