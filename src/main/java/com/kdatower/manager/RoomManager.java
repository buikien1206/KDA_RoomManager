package com.kdatower.manager;

import com.kdatower.model.Room;
import com.kdatower.dao.RoomXML;
import java.util.*;

public class RoomManager {
    private List<Room> rooms;

    public RoomManager() {
        rooms = RoomXML.readRooms();
    }

    public List<Room> getAll() { return rooms; }

    public void addRoom(Room r) {
        rooms.add(r);
        RoomXML.writeRooms(rooms);
    }
}
