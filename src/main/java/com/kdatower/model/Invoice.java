package com.kdatower.model;

public class Invoice {
    private String id;
    private String apartmentId;
    private String customer;
    private String cashier;
    private String date;
    private double total;
    private String status; // "Đã thanh toán" hoặc "Chưa thanh toán"

    public Invoice() {}
    public Invoice(String id, String apartmentId, String customer,
                   String cashier, String date, double total, String status) {
        this.id = id; this.apartmentId = apartmentId; this.customer = customer;
        this.cashier = cashier; this.date = date; this.total = total; this.status = status;
    }
    // getters & setters...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getApartmentId() { return apartmentId; }
    public void setApartmentId(String apartmentId) { this.apartmentId = apartmentId; }
    public String getCustomer() { return customer; }
    public void setCustomer(String customer) { this.customer = customer; }
    public String getCashier() { return cashier; }
    public void setCashier(String cashier) { this.cashier = cashier; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
