package com.kdatower.manager;

import com.kdatower.model.Resident;
import com.kdatower.dao.ResidentXML;
import java.util.*;

public class ResidentManager {
    private List<Resident> residents;

    public ResidentManager() {
        residents = ResidentXML.readResidents();
    }

    public List<Resident> getAll() { return residents; }
    public void add(Resident r) {
        residents.add(r);
        ResidentXML.writeResidents(residents);
    }
    public void update(int idx, Resident r) {
        residents.set(idx, r);
        ResidentXML.writeResidents(residents);
    }
    public void delete(int idx) {
        residents.remove(idx);
        ResidentXML.writeResidents(residents);
    }
    public List<Resident> search(String key) {
        List<Resident> res = new ArrayList<>();
        for (Resident r: residents) {
            if (r.getName().toLowerCase().contains(key.toLowerCase()) ||
                r.getApartmentId().contains(key))
                res.add(r);
        }
        return res;
    }
    public void sortByName() {
        residents.sort(Comparator.comparing(Resident::getName));
        ResidentXML.writeResidents(residents);
    }
}
