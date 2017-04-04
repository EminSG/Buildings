package io.ezorrio.buildings.model;

import io.ezorrio.buildings.exception.NoSpaceOnLevelException;

import java.util.ArrayList;

/**
 * Created by golde on 30.03.2017.
 */
/*
    2.1.1.Площадь этажа – редактируемый параметр.
    2.1.2.Номер этажа (сочетание цифр и букв) – редактируемый параметр.
    2.1.3.Общее кол-во помещений на этаже – вычисляемый параметр.
    2.1.4.Количество помещений каждого типа на этаже – набор вычисляемых параметров.
     */

public class Level {
    private int id;
    private double capacity;
    private ArrayList<Room> rooms;

    public Level(double capacity) {
        this.capacity = capacity;
        this.rooms = new ArrayList<Room>();
    }

    public Level(int id, double capacity) {
        this.id = id;
        this.capacity = capacity;
        this.rooms = new ArrayList<Room>();
    }

    public Room getRoom(int id) {
        return rooms.get(id);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room newRoom) {
        newRoom.setId(id + "-" + rooms.size());
        if (capacity < roomsTotalCapacity() + newRoom.getCapacity()) {
            throw new NoSpaceOnLevelException(roomsTotalCapacity() + newRoom.getCapacity() - capacity);
        } else {
            rooms.add(newRoom);
        }
    }

    public boolean isLevelFull() {
        return capacity == roomsTotalCapacity();
    }

    public double roomsTotalCapacity() {
        double roomsTotalCapacity = 0;
        for (Room room : rooms) {
            roomsTotalCapacity += room.getCapacity();
        }
        return roomsTotalCapacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Level " + getId() + " (" + capacity + ")";
    }
}

