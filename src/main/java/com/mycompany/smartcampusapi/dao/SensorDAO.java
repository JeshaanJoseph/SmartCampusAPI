package com.mycompany.smartcampusapi.dao;

import com.mycompany.smartcampusapi.model.Sensor;
import java.util.*;

public class SensorDAO {

    private static Map<String, Sensor> sensors = new HashMap<>();

    public static Collection<Sensor> getAll() {
        return sensors.values();
    }

    public static Sensor get(String id) {
        return sensors.get(id);
    }

    public static void add(Sensor s) {
        sensors.put(s.getId(), s);
    }

    public static Collection<Sensor> getByType(String type) {
        List<Sensor> list = new ArrayList<>();
        for (Sensor s : sensors.values()) {
            if (type.equalsIgnoreCase(s.getType())) {
                list.add(s);
            }
        }
        return list;
    }
}