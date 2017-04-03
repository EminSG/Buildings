package io.ezorrio.buildings.model;

import io.ezorrio.buildings.db.DBHelper;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by golde on 30.03.2017.
 */
public class Building {
    private ArrayList<Level> levels;


    public Building() {
        this.levels = new ArrayList<Level>();
    }

    public boolean isLevelFull(int level) {
        return getLevel(level).isLevelFull();
    }

    public Level getLevel(int level) {
        return levels.get(level);
    }

    public int getBuildingSize() {
        return levels.size() - 1;
    }

    public void addLevel(Level level) {
        level.setId(levels.size() + 1);
        levels.add(level);
    }

    public void addRoom(Room room) {
        room.setRoomLevel(getBuildingSize());
        addRoom(room, getBuildingSize());
    }

    public void addRoom(Room room, int level) {
        room.setRoomLevel(level);
        getLevel(level).addRoom(room);
    }

    private boolean checkIfBuiltRight() {
        for (int i = 0; i < levels.size(); i++) {
            if (!isLevelFull(i)) {
                return false;
            }
        }
        return true;
    }

    private void saveBuilding() {
        DBHelper helper = new DBHelper();
        for (Level level : levels) {
            helper.addLevel(level);
            for (Room room : level.getRooms()) {

                if (room instanceof Office) {
                    System.out.println("Saving office" + room.getId());
                    helper.addOfficeRoom((Office) room);
                }

                if (room instanceof Special) {
                    System.out.println("Saving special" + room.getId());
                    helper.addSpecialRoom((Special) room);
                }

                if (room instanceof Talk) {
                    System.out.println("Saving talk" + room.getId());
                    helper.addTalkRoom((Talk) room);
                }
            }
        }
    }

    public void save() {
        if (checkIfBuiltRight()) {
            saveBuilding();
        } else {
            System.out.println("Incorrect building");
        }
    }
}
