package io.ezorrio.buildings.model;

/**
 * Created by golde on 30.03.2017.
 */
public class Talk extends Room {
    private boolean canPresentate;
    private boolean isUsed;

    public Talk(double capacity, boolean canHaveFire, int fireCount, boolean canPresentate, boolean isUsed) {
        super(capacity, canHaveFire, fireCount);
        this.canPresentate = canPresentate;
        this.isUsed = isUsed;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean canPresentate() {
        return canPresentate;
    }

    public String getExtendedInfo() {
        return getClass().getSimpleName() +
                ": " + getId() +
                "(capacity: " + getCapacity() +
                ", fires count: " + getFireCount() +
                ", canPresentate: " + canPresentate() +
                ", isUsed: " + isUsed() + ")";
    }

    public void setCanPresentate(boolean canPresentate) {
        this.canPresentate = canPresentate;
    }
}
