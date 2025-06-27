package com.kdatower.model;

public class Room {
    private String id;
    private String name;
    private String blockId;

    public Room() {}

    public Room(String id, String name, String blockId) {
        this.id = id;
        this.name = name;
        this.blockId = blockId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBlockId() { return blockId; }
    public void setBlockId(String blockId) { this.blockId = blockId; }
}
