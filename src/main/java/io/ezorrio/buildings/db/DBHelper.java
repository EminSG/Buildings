package io.ezorrio.buildings.db;

import io.ezorrio.buildings.exception.NoSpaceOnLevelException;
import io.ezorrio.buildings.model.*;

import java.sql.*;

/**
 * Created by golde on 30.03.2017.
 */
public class DBHelper {

    public static final String DEFAULT_DB_PATH = "C:/Buildings/Building.db";
    public static final String OFFICE_TABLE = "office";
    public static final String LEVEL_TABLE = "level";
    public static final String SPECIAL_TABLE = "special";
    public static final String TALK_TABLE = "talk";
    private Connection mConnection;


    public DBHelper() {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + DEFAULT_DB_PATH);
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DBHelper(String path) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + path);
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return mConnection;
    }

    public void createLevelTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + LEVEL_TABLE + " (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "	capacity REAL,\n"
                + " room_count INTEGER,\n"
                + " inner_offices INTEGER,\n"
                + " offices_count INTEGER,\n"
                + " special_count INTEGER,\n"
                + " talk_count INTEGER,\n"
                + "UNIQUE (id) ON CONFLICT REPLACE\n"
                + ");";

        mConnection.createStatement().execute(sql);
    }

    public void createOfficesTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + OFFICE_TABLE + " (\n"
                + "	id TEXT NOT NULL PRIMARY KEY,\n"
                + "	name TEXT NOT NULL,\n"
                + "	capacity REAL,\n"
                + " can_have_fire BOOLEAN,\n"
                + " fire_count INTEGER,\n"
                + " is_owner BOOLEAN,\n"
                + " inner_offices INTEGER,\n"
                + " level INTEGER,\n"
                + "UNIQUE (id) ON CONFLICT REPLACE\n"
                + ");";

        mConnection.createStatement().execute(sql);
    }

    public void createTalkTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + TALK_TABLE + " (\n"
                + "	id TEXT NOT NULL PRIMARY KEY,\n"
                + "	capacity REAL,\n"
                + " can_have_fire BOOLEAN,\n"
                + " fire_count INTEGER,\n"
                + " can_presentate BOOLEAN,\n"
                + " is_used BOOLEAN,\n"
                + " level INTEGER,\n"
                + "UNIQUE (id) ON CONFLICT REPLACE\n"
                + ");";

        mConnection.createStatement().execute(sql);
    }

    public void createSpecialTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + SPECIAL_TABLE + " (\n"
                + "	id TEXT NOT NULL PRIMARY KEY,\n"
                + "	capacity REAL,\n"
                + " can_have_fire BOOLEAN,\n"
                + " fire_count INTEGER,\n"
                + " type INTEGER,\n"
                + " level INTEGER,\n"
                + "UNIQUE (id) ON CONFLICT REPLACE\n"
                + ");";

        mConnection.createStatement().execute(sql);
    }


    public void createTables() {
        try {
            createLevelTable();
            createOfficesTable();
            createTalkTable();
            createSpecialTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEntry(String sql, int id) {
        try {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    2.1.1.Площадь этажа – редактируемый параметр.
    2.1.2.Номер этажа (сочетание цифр и букв) – редактируемый параметр.
    2.1.3.Общее кол-во помещений на этаже – вычисляемый параметр.
    2.1.4.Количество помещений каждого типа на этаже – набор вычисляемых параметров.
     */

    public void addLevel(Level level) {
        String sql = "INSERT INTO " + LEVEL_TABLE + "(id,capacity,room_count,offices_count,talk_count,special_count)"
                + " VALUES(?,?,?,?,?,? )";

        try {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);
            pstmt.setInt(1, level.getId());
            pstmt.setDouble(2, level.getCapacity());
            pstmt.setInt(3, 0);
            pstmt.setInt(4, 0);
            pstmt.setInt(5, 0);
            pstmt.setInt(6, 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLevel(int id) {
        String sql = "DELETE FROM " + LEVEL_TABLE + " WHERE id = ?";
        deleteEntry(sql, id);
    }

    /*
    2.2.1.1. Площадь помещения.
    2.2.1.2. Номер комнаты (сочетание цифр и букв).
    2.2.1.3. Наличие пожарных датчиков.
    2.2.1.4. Кол-во пожарных датчиков (доступен для редактирования при условии наличий этих самых датчиков).
    2.2.1.5. Название компании.
    2.2.1.6. Аренда / собственность.
    2.2.1.7. Количество офисов в нутрии данного офиса – вычисляемый параметр.
     */

    public void addOfficeRoom(Office room) {
        String sql = "INSERT INTO " + OFFICE_TABLE + "(id,name,capacity,can_have_fire,fire_count,is_owner,inner_offices,level)"
                + " VALUES(?,?,?,?,?,?,?,? )";

        try {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);
            pstmt.setString(1, room.getId());
            pstmt.setString(2, room.getName());
            pstmt.setDouble(3, room.getCapacity());
            pstmt.setBoolean(4, room.canHaveFire());
            pstmt.setInt(5, room.getFireCount());
            pstmt.setBoolean(6, room.isOwner());
            pstmt.setInt(7, room.getInnerOffices());
            pstmt.setInt(8, room.getRoomLevel());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteOfficeRoom(int id) {
        String sql = "DELETE FROM " + OFFICE_TABLE + " WHERE id = ?";
        deleteEntry(sql, id);
    }

    /*
    2.2.2.1. Площадь помещения.
    2.2.2.2. Номер комнаты (сочетание цифр и букв).
    2.2.2.3. Наличие пожарных датчиков.
    2.2.2.4. Кол-во пожарных датчиков (доступен для редактирования при условии наличий этих самых датчиков).
    2.2.2.5. Оборудовано аппаратурой для презентаций.
    2.2.2.6. Зарезервирована.
     */

    public void addTalkRoom(Talk room) {
        String sql = "INSERT INTO " + TALK_TABLE + "(id,capacity,can_have_fire,fire_count,can_presentate,is_used,level)"
                + " VALUES(?,?,?,?,?,?,? )";

        try {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);
            pstmt.setString(1, room.getId());
            pstmt.setDouble(2, room.getCapacity());
            pstmt.setBoolean(3, room.canHaveFire());
            pstmt.setInt(4, room.getFireCount());
            pstmt.setBoolean(5, room.canPresentate());
            pstmt.setBoolean(6, room.isUsed());
            pstmt.setInt(7, room.getRoomLevel());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteTalkRoom(int id) {
        String sql = "DELETE FROM " + TALK_TABLE + " WHERE id = ?";
        deleteEntry(sql, id);
    }

    /*
    2.2.3.1. Площадь помещения.
    2.2.3.2. Номер комнаты (сочетание цифр и букв).
    2.2.3.3. Наличие пожранных датчиков.
    2.2.3.4. Кол-во пожарных датчиков (доступен для редактирования при условии наличий этих самых датчиков).
    2.2.3.5. Тип (Серверная, склад, помещение охраны).
     */

    public void addSpecialRoom(Special room) {
        String sql = "INSERT INTO " + SPECIAL_TABLE + "(id,capacity,can_have_fire,fire_count,type,level)"
                + " VALUES(?,?,?,?,?,? )";

        try {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);
            pstmt.setString(1, room.getId());
            pstmt.setDouble(2, room.getCapacity());
            pstmt.setBoolean(3, room.canHaveFire());
            pstmt.setInt(4, room.getFireCount());
            pstmt.setInt(5, room.getType());
            pstmt.setInt(6, room.getRoomLevel());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSpecialRoom(int id) {
        String sql = "DELETE FROM " + SPECIAL_TABLE + " WHERE id = ?";
        deleteEntry(sql, id);
    }

    private void dropAllTables() {
    }

    public Building importFromDB() throws SQLException, NoSpaceOnLevelException {
        Building result = new Building();
        ResultSet rsLevels = mConnection.createStatement().executeQuery("SELECT * FROM " + LEVEL_TABLE);
        ResultSet rsOffices = mConnection.createStatement().executeQuery("SELECT * FROM " + OFFICE_TABLE);
        ResultSet rsSpecials = mConnection.createStatement().executeQuery("SELECT * FROM " + SPECIAL_TABLE);
        ResultSet rsTalk = mConnection.createStatement().executeQuery("SELECT * FROM " + TALK_TABLE);

        while (rsLevels.next()) {
            //"(id,capacity,room_count,offices_count,talk_count,special_count)"
            Level level = new Level(rsLevels.getInt("id"),
                    rsLevels.getDouble("capacity"));
            result.addLevel(level);
        }
        while (rsSpecials.next()) {
            //id,capacity,can_have_fire,fire_count,type,level
            Special special = new Special(rsSpecials.getDouble(2),
                    rsSpecials.getBoolean(3),
                    rsSpecials.getInt(4),
                    rsSpecials.getInt(5));
            int roomLevel = rsSpecials.getInt(6);
            special.setId(rsSpecials.getString(1));
            special.setRoomLevel(roomLevel);
            result.getLevel(roomLevel).addRoom(special);
        }

        while (rsOffices.next()) {
            //id,name,capacity,can_have_fire,fire_count,is_owner,inner_offices,level
            Office office = new Office(rsOffices.getDouble(2),
                    rsOffices.getBoolean(3),
                    rsOffices.getInt(4),
                    rsOffices.getString(5),
                    rsOffices.getBoolean(6),
                    rsOffices.getInt(7));
            int roomLevel = rsOffices.getInt(8);
            office.setId(rsOffices.getString(1));
            office.setRoomLevel(roomLevel);
            result.getLevel(roomLevel).addRoom(office);
        }

        while (rsTalk.next()) {
            //id,capacity,can_have_fire,fire_count,can_presentate,is_used,level
            Talk talk = new Talk(rsTalk.getDouble(2),
                    rsTalk.getBoolean(3),
                    rsTalk.getInt(4),
                    rsTalk.getBoolean(5),
                    rsTalk.getBoolean(6));
            int roomLevel = rsTalk.getInt(7);
            talk.setId(rsTalk.getString(1));
            talk.setRoomLevel(roomLevel);
            result.getLevel(roomLevel).addRoom(talk);
        }
        return result;
    }
}