package io.ezorrio.buildings.exception;

/**
 * Created by golde on 06.04.2017.
 */
public class InvalidIdException extends Exception {
    public InvalidIdException(String id) {
        super("Id: " + id + "not found");
    }
}
