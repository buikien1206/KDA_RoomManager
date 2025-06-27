package com.kdatower.manager;

import com.kdatower.model.Tenant;
import com.kdatower.dao.TenantXML;
import java.util.*;

public class TenantManager {
    private List<Tenant> tenants;

    public TenantManager() {
        tenants = TenantXML.readTenants();
    }

    public List<Tenant> getAll() { return tenants; }

    public void addTenant(Tenant t) {
        tenants.add(t);
        TenantXML.writeTenants(tenants);
    }
}
