package io.ezorrio.buildings.model;

import com.sun.javaws.exceptions.InvalidArgumentException;
import io.ezorrio.buildings.db.DBHelper;
import io.ezorrio.buildings.exception.IncorrectBuildingException;
import io.ezorrio.buildings.exception.InvalidIdException;
import io.ezorrio.buildings.exception.NoSpaceOnLevelException;

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

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public ArrayList<Room> getRooms() {
        ArrayList<Room> result = new ArrayList<Room>();
        for (Level level : levels) {
            for (Room room : level.getRooms()) {
                result.add(room);
            }
        }
        return result;
    }

    public Room getRoomById(String id) {
        for (Level level : levels) {
            for (Room room : level.getRooms()) {
                if (room.getId().equals(id)) {
                    return room;
                }
            }
        }
        return null;
    }

    public int getBuildingSize() {
        return levels.size() - 1;
    }

    public void addLevel(Level level) {
        level.setId(levels.size() + 1);
        levels.add(level);
    }

    public void addRoom(Room room) throws NoSpaceOnLevelException {
        room.setRoomLevel(getBuildingSize());
        addRoom(room, getBuildingSize());
    }

    public void addRoom(Room room, int level) throws NoSpaceOnLevelException {
        room.setRoomLevel(level);
        getLevel(level).addRoom(room);
    }

    public void removeRoom(String id) throws InvalidIdException{
        for (Level level : levels) {
            for (Room room : level.getRooms()) {
                if (room.getId().equals(id)) {
                    level.removeRoom(room);
                }
            }
        }
        throw new InvalidIdException(id);
    }

    public void removeLevel(int id) throws InvalidIdException {
        if (id > getBuildingSize()){
            throw new InvalidIdException(String.valueOf(id));
        }
        getLevels().remove(id);
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


    public void save() throws IncorrectBuildingException {
        if (checkIfBuiltRight()) {
            saveBuilding();
        } else {
            throw new IncorrectBuildingException();
        }
    }
}
