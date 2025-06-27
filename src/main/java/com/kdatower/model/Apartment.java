package com.kdatower.model;

public class Apartment {
    private String id;
    private String owner;
    private double area;
    private String building;
    private int floor;
    private String status; // "Đã ở" hoặc "Bỏ trống"
    private int numPeople;
    private String dateIn;
    private String account;

    public Apartment() {}

    public Apartment(String id, String owner, double area, String building, int floor, String status, int numPeople, String dateIn, String account) {
        this.id = id;
        this.owner = owner;
        this.area = area;
        this.building = building;
        this.floor = floor;
        this.status = status;
        this.numPeople = numPeople;
        this.dateIn = dateIn;
        this.account = account;
    }
    // Getter, setter (có thể Generate trong NetBeans cho nhanh)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }
    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }
    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getNumPeople() { return numPeople; }
    public void setNumPeople(int numPeople) { this.numPeople = numPeople; }
    public String getDateIn() { return dateIn; }
    public void setDateIn(String dateIn) { this.dateIn = dateIn; }
    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
}
