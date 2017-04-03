package io.ezorrio.buildings.model;

/**
 * Created by golde on 30.03.2017.
 */
public class Room {
    private String id;
    private double capacity;
    private int roomLevel;
    private boolean canHaveFire;
    private int fireCount;

    public Room(double capacity, boolean canHaveFire, int fireCount) {
        this.capacity = capacity;
        this.canHaveFire = canHaveFire;
        this.fireCount = fireCount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getCapacity() {
        return capacity;
    }

    public int getRoomLevel() {
        return roomLevel;
    }

    public void setRoomLevel(int level){
        this.roomLevel = level;
    }

    public int getFireCount() {
        return fireCount;
    }

    public boolean canHaveFire() {
        return canHaveFire;
    }

    public void setFireCount(int fireCount) {
        this.fireCount = fireCount;
    }

    public void setCanHaveFire(boolean canHaveFire) {
        this.canHaveFire = canHaveFire;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + id + "(" + capacity + ")";
    }

    public String getInfo() {
        return toString();
    }
}
