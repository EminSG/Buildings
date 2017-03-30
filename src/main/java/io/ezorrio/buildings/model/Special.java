package io.ezorrio.buildings.model;

/**
 * Created by golde on 30.03.2017.
 */
public class Special extends Room {
    private boolean canHaveFire;
    private int fireCount;
    private int type;

    public static final int SERVER = 0;
    public static final int SECURITY = 1;
    public static final int CONTAINER = 2;

    /**
     * @param type one of constant value(SERVER/SECURITY/CONTAINER)
     */

    public Special(double capacity, boolean canHaveFire, int fireCount, int type){
        super(capacity);
        this.canHaveFire = canHaveFire;
        this.fireCount = fireCount;
        this.type = type;
    }

    public int getFireCount() {
        return fireCount;
    }

    public int getType() {
        return type;
    }

    public boolean canHaveFire(){
        return canHaveFire;
    }

    public void setFireCount(int fireCount) {
        this.fireCount = fireCount;
    }

    public void setCanHaveFire(boolean canHaveFire) {
        this.canHaveFire = canHaveFire;
    }

    public void setType(int type) {
        this.type = type;
    }
}
