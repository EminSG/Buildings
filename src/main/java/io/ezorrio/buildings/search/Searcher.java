package io.ezorrio.buildings.search;

import io.ezorrio.buildings.model.Building;
import io.ezorrio.buildings.model.Room;
import io.ezorrio.buildings.utils.Utils;

import java.util.ArrayList;

/**
 * Created by golde on 03.04.2017.
 */
public class Searcher {
    private Building building;

    public Searcher(Building building) {
        this.building = building;
    }

    public ArrayList<String> getAllRoomsInfo(boolean showExtended) {
        ArrayList<String> result = new ArrayList<String>();
        for (Room room : building.getRooms()) {
            result.add(Utils.getRoomInfo(room, showExtended));
        }
        return result;
    }

    public ArrayList<String> getRoomsInfoFromLevel(int level, boolean showExtended) {
        ArrayList<String> result = new ArrayList<String>();
        for (Room room : building.getLevel(level).getRooms()) {
            result.add(Utils.getRoomInfo(room, showExtended));
        }
        return result;
    }

    public interface BuildingShowCriteria {
        int BY_CAPACITY = 0;
        int BY_ROOM_NUMBER = 1;
        int BY_FIRE_SENSOR = 2;
    }

    public ArrayList<String> sortRooms(final int criteria, boolean showExtended) {
        building.getRooms().sort((o1, o2) -> {
            switch (criteria) {
                case BuildingShowCriteria.BY_CAPACITY:
                    return Double.compare(o1.getCapacity(), o2.getCapacity());
                case BuildingShowCriteria.BY_ROOM_NUMBER:
                    return o1.getId().compareTo(o2.getId());
                case BuildingShowCriteria.BY_FIRE_SENSOR:
                    return Integer.compare(o1.getFireCount(), o2.getFireCount());
            }
            return 0;
        });
        return getAllRoomsInfo(showExtended);
    }

    public interface FilterType {
        int MORE = 1;
        int LESS = -1;
        int EQUAL = 0;
    }

    public ArrayList<String> filterRooms(final int criteria, final int filterType, String filterValue, boolean showAsExtended) {
        ArrayList<String> result = new ArrayList<>();
        for (Room room : building.getRooms()) {
            switch (criteria) {
                case BuildingShowCriteria.BY_CAPACITY:
                    switch (filterType) {
                        case FilterType.MORE:
                            if (room.getCapacity() > Double.parseDouble(filterValue)) {
                                result.add(Utils.getRoomInfo(room, showAsExtended));
                            }
                            break;
                        case FilterType.LESS:
                            if (room.getCapacity() < Double.parseDouble(filterValue)) {
                                result.add(Utils.getRoomInfo(room, showAsExtended));
                            }
                            break;
                        case FilterType.EQUAL:
                            if (room.getCapacity() == Double.parseDouble(filterValue)) {
                                result.add(Utils.getRoomInfo(room, showAsExtended));
                            }
                            break;
                    }
                    break;
                case BuildingShowCriteria.BY_ROOM_NUMBER:
                    if (room.getId().contains(filterValue)) {
                        result.add(Utils.getRoomInfo(room, showAsExtended));
                    }
                    break;
                case BuildingShowCriteria.BY_FIRE_SENSOR:
                    switch (filterType) {
                        case FilterType.MORE:
                            if (room.getFireCount() > Integer.parseInt(filterValue)) {
                                result.add(Utils.getRoomInfo(room, showAsExtended));
                            }
                            break;
                        case FilterType.LESS:
                            if (room.getFireCount() < Integer.parseInt(filterValue)) {
                                result.add(Utils.getRoomInfo(room, showAsExtended));
                            }
                            break;
                        case FilterType.EQUAL:
                            if (room.getFireCount() == Integer.parseInt(filterValue)) {
                                result.add(Utils.getRoomInfo(room, showAsExtended));
                            }
                            break;
                    }
                    break;
            }
        }
        return result;
    }
}
