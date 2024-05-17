package coe528.project;

public class GoldLevel extends CustomerState{
    public GoldLevel (Customer c) {
        super(c);
        this.defineLevel = "Gold";
        this.defineFee = 10.00;
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
