package io.ezorrio.buildings.exception;

/**
 * Created by golde on 30.03.2017.
 */
public class NoSpaceOnLevelException extends IllegalArgumentException {
    public NoSpaceOnLevelException(Double left) {
        super("You can build room with capacity not more than " + left);
    }
}
