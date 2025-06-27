package com.kdatower.manager;

import com.kdatower.model.Apartment;
import com.kdatower.dao.ApartmentXML;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ApartmentManager {
    private List<Apartment> apartments;

    public ApartmentManager() {
        apartments = ApartmentXML.readApartments();
    }

    public List<Apartment> getAll() {
        return apartments;
    }

    public void add(Apartment a) {
        apartments.add(a);
        ApartmentXML.writeApartments(apartments);
    }

    public void update(int idx, Apartment a) {
        apartments.set(idx, a);
        ApartmentXML.writeApartments(apartments);
    }

    public void delete(int idx) {
        apartments.remove(idx);
        ApartmentXML.writeApartments(apartments);
    }

    public void sortByArea() {
        apartments.sort(Comparator.comparingDouble(Apartment::getArea));
        ApartmentXML.writeApartments(apartments);
    }

    public void sortById() {
        apartments.sort(Comparator.comparing(Apartment::getId));
        ApartmentXML.writeApartments(apartments);
    }

    public void sortByOwner() {
        apartments.sort(Comparator.comparing(Apartment::getOwner));
        ApartmentXML.writeApartments(apartments);
    }

    public void sortByBuilding() {
        apartments.sort(Comparator.comparing(Apartment::getBuilding));
        ApartmentXML.writeApartments(apartments);
    }

    public void sortByFloor() {
        apartments.sort(Comparator.comparingInt(Apartment::getFloor));
        ApartmentXML.writeApartments(apartments);
    }

    public void sortByNumPeople() {
        apartments.sort(Comparator.comparingInt(Apartment::getNumPeople));
        ApartmentXML.writeApartments(apartments);
    }

    public List<Apartment> search(String keyword) {
        List<Apartment> result = new ArrayList<>();
        for (Apartment a : apartments) {
            if (a.getId().contains(keyword) ||
                a.getOwner().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(a);
            }
        }
        return result;
    }
}
