package io.ezorrio.buildings.model;

/**
 * Created by golde on 30.03.2017.
 */
public class Special extends Room {
    public static final int SERVER = 0;
    public static final int SECURITY = 1;
    public static final int CONTAINER = 2;
    private int type;

    /**
     * @param type one of constant value(SERVER/SECURITY/CONTAINER)
     */

    public Special(double capacity, boolean canHaveFire, int fireCount, int type) {
        super(capacity, canHaveFire, fireCount);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String typeToString() {
        switch (type) {
            case 0:
                return "server";
            case 1:
                return "security";
            case 2:
                return "container";
            default:
                return "unknown";
        }
    }

    public String getExtendedInfo() {
        return getClass().getSimpleName() +
                ": " + getId() +
                "(capacity: " + getCapacity() +
                ", fires count: " + getFireCount() +
                ", type: " + typeToString();
    }
}
