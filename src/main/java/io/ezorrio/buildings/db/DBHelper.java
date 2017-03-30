package io.ezorrio.buildings.db;

import io.ezorrio.buildings.model.Level;
import io.ezorrio.buildings.model.Office;
import io.ezorrio.buildings.model.Special;
import io.ezorrio.buildings.model.Talk;

import java.sql.*;

/**
 * Created by golde on 30.03.2017.
 */
public class DBHelper {

    private Connection mConnection;
    private static final String DB_NAME = "jdbc:sqlite:C:/Buildings/Building.db";
    private static final String OFFICE_TABLE = "office";
    private static final String LEVEL_TABLE = "level";
    private static final String SPECIAL_TABLE = "special";
    private static final String TALK_TABLE = "talk";


    public DBHelper() {
        try {
            mConnection = DriverManager.getConnection(DB_NAME);
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
        String sql = "INSERT INTO " + OFFICE_TABLE + "(id,name,capacity,can_have_fire,fire_count,is_owner,inner_offices)"
                + " VALUES(?,?,?,?,?,?,? )";

        try {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);
            pstmt.setString(1, room.getId());
            pstmt.setString(2, room.getName());
            pstmt.setDouble(3, room.getCapacity());
            pstmt.setBoolean(4, room.canHaveFire());
            pstmt.setInt(5, room.getFireCount());
            pstmt.setBoolean(6, room.isOwner());
            pstmt.setInt(7, room.getInnerOffices());
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
        String sql = "INSERT INTO " + TALK_TABLE + "(id,capacity,can_have_fire,fire_count,can_presentate,is_used)"
                + " VALUES(?,?,?,?,?,? )";

        try {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);
            pstmt.setString(1, room.getId());
            pstmt.setDouble(2, room.getCapacity());
            pstmt.setBoolean(3, room.canHaveFire());
            pstmt.setInt(4, room.getFireCount());
            pstmt.setBoolean(5, room.canPresentate());
            pstmt.setBoolean(6, room.isUsed());
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
        String sql = "INSERT INTO " + SPECIAL_TABLE + "(id,capacity,can_have_fire,fire_count,type)"
                + " VALUES(?,?,?,?,? )";

        try {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);
            pstmt.setString(1, room.getId());
            pstmt.setDouble(2, room.getCapacity());
            pstmt.setBoolean(3, room.canHaveFire());
            pstmt.setInt(4, room.getFireCount());
            pstmt.setInt(5, room.getType());
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


}
