package io.ezorrio.buildings.model;

/**
 * Created by golde on 30.03.2017.
 */
public class Room {
    private String id;
    private double capacity;
    private int roomLevel;

    public Room(double capacity) {
        this.capacity = capacity;
    }

    public Room(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
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
}
