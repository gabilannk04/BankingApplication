package coe528.project;

import java.io.IOException;

public abstract class CustomerState {
    protected Customer temp;
    protected String defineLevel;
    protected double defineFee;

    public CustomerState(Customer c) {
        this.temp = c;
    }

    protected String getLevel() {
        return this.defineLevel;
    }

    protected double getFee() {
        return this.defineFee;
    }

    protected abstract void currToSilver();
    protected abstract void currToGold();
    protected abstract void currToPlat();
}
