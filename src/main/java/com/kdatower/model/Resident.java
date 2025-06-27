package com.kdatower.model;

public class Resident {
    private String name;
    private String gender;    // "Nam" hoặc "Nữ"
    private String dateOfBirth;
    private String cccd;
    private String phone;
    private String apartmentId;
    private String hometown;
    private String householdBook;

    public Resident() {}

    public Resident(String name, String gender, String dateOfBirth,
                    String cccd, String phone,
                    String apartmentId, String hometown, String householdBook) {
        this.name = name; this.gender = gender; this.dateOfBirth = dateOfBirth;
        this.cccd = cccd; this.phone = phone;
        this.apartmentId = apartmentId; this.hometown = hometown;
        this.householdBook = householdBook;
    }
    // getters & setters...
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getCccd() { return cccd; }
    public void setCccd(String cccd) { this.cccd = cccd; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getApartmentId() { return apartmentId; }
    public void setApartmentId(String apartmentId) { this.apartmentId = apartmentId; }
    public String getHometown() { return hometown; }
    public void setHometown(String hometown) { this.hometown = hometown; }
    public String getHouseholdBook() { return householdBook; }
    public void setHouseholdBook(String householdBook) { this.householdBook = householdBook; }
}
