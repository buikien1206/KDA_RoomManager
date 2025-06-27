package com.kdatower.model;

public class Tenant {
    private String id;
    private String name;
    private String gender;
    private String roomId;

    public Tenant() {}

    public Tenant(String id, String name, String gender, String roomId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.roomId = roomId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
}
