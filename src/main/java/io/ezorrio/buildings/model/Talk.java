package io.ezorrio.buildings.model;

/**
 * Created by golde on 30.03.2017.
 */
public class Talk extends Room{
    private boolean canHaveFire;
    private int fireCount;
    private boolean canPresentate;
    private boolean isUsed;

    public Talk(double capacity, boolean canHaveFire, int fireCount, boolean canPresentate, boolean isUsed){
        super(capacity);
        this.canHaveFire = canHaveFire;
        this.fireCount = fireCount;
        this.canPresentate = canPresentate;
        this.isUsed = isUsed;
    }

    public int getFireCount() {
        return fireCount;
    }

    public boolean isUsed(){
        return isUsed;
    }

    public boolean canHaveFire() {
        return canHaveFire;
    }

    public boolean canPresentate(){
        return canPresentate;
    }
}
