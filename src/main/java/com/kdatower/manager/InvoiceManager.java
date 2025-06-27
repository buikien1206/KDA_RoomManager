package com.kdatower.manager;

import com.kdatower.model.Invoice;
import com.kdatower.dao.InvoiceXML;
import java.util.*;

public class InvoiceManager {
    private List<Invoice> invoices;

    public InvoiceManager() {
        invoices = InvoiceXML.readInvoices();
    }

    public List<Invoice> getAll() { return invoices; }
    public void add(Invoice i) {
        invoices.add(i);
        InvoiceXML.writeInvoices(invoices);
    }
    public void update(int idx, Invoice i) {
        invoices.set(idx, i);
        InvoiceXML.writeInvoices(invoices);
    }
    public void delete(int idx) {
        invoices.remove(idx);
        InvoiceXML.writeInvoices(invoices);
    }
    public List<Invoice> search(String key) {
        List<Invoice> out = new ArrayList<>();
        for (Invoice i: invoices){
            if (i.getId().contains(key) ||
                i.getApartmentId().contains(key) ||
                i.getCustomer().toLowerCase().contains(key.toLowerCase()))
                out.add(i);
        }
        return out;
    }
    public void sortByDate(){
        invoices.sort(Comparator.comparing(Invoice::getDate));
        InvoiceXML.writeInvoices(invoices);
    }
}
