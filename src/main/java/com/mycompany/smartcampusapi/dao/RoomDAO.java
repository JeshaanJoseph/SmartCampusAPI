package com.mycompany.smartcampusapi.dao;

import com.mycompany.smartcampusapi.model.Room;
import java.util.*;

public class RoomDAO {

    private static Map<String, Room> rooms = new HashMap<>();

    public static Collection<Room> getAll() {
        return rooms.values();
    }

    public static Room get(String id) {
        return rooms.get(id);
    }

    public static void add(Room r) {
        rooms.put(r.getId(), r);
    }

    public static void delete(String id) {
        rooms.remove(id);
    }
}