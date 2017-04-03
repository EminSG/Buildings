package io.ezorrio.buildings.utils;

import io.ezorrio.buildings.model.Office;
import io.ezorrio.buildings.model.Room;
import io.ezorrio.buildings.model.Special;
import io.ezorrio.buildings.model.Talk;

/**
 * Created by golde on 03.04.2017.
 */
public class Utils {

    public static String getRoomInfo(Room room, boolean showExtended){
        if (showExtended) {
            if (room instanceof Office) {
                return ((Office) room).getExtendedInfo();
            }
            if (room instanceof Special) {
                return ((Special) room).getExtendedInfo();
            }
            if (room instanceof Talk) {
                return ((Talk) room).getExtendedInfo();
            }

        } else {
            return room.getInfo();
        }
        return null;
    }
}
