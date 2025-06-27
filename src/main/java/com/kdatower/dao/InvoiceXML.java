package com.kdatower.dao;

import com.kdatower.model.Invoice;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;

public class InvoiceXML {
    private static final String XML_FILE = "invoice_data.xml";

    public static List<Invoice> readInvoices() {
        List<Invoice> list = new ArrayList<>();
        try {
            File f = new File(XML_FILE);
            if (!f.exists()) return list;
            Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
            NodeList nodes = d.getElementsByTagName("Invoice");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element e = (Element) nodes.item(i);
                Invoice inv = new Invoice();
                inv.setId(e.getElementsByTagName("id").item(0).getTextContent());
                inv.setApartmentId(e.getElementsByTagName("apartmentId").item(0).getTextContent());
                inv.setCustomer(e.getElementsByTagName("customer").item(0).getTextContent());
                inv.setCashier(e.getElementsByTagName("cashier").item(0).getTextContent());
                inv.setDate(e.getElementsByTagName("date").item(0).getTextContent());
                inv.setTotal(Double.parseDouble(e.getElementsByTagName("total").item(0).getTextContent()));
                inv.setStatus(e.getElementsByTagName("status").item(0).getTextContent());
                list.add(inv);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return list;
    }

    public static void writeInvoices(List<Invoice> list) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = doc.createElement("Invoices");
            doc.appendChild(root);
            for (Invoice inv: list) {
                Element e = doc.createElement("Invoice");
                append(doc,e,"id", inv.getId());
                append(doc,e,"apartmentId", inv.getApartmentId());
                append(doc,e,"customer", inv.getCustomer());
                append(doc,e,"cashier", inv.getCashier());
                append(doc,e,"date", inv.getDate());
                append(doc,e,"total", String.valueOf(inv.getTotal()));
                append(doc,e,"status", inv.getStatus());
                root.appendChild(e);
            }
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT,"yes");
            t.transform(new DOMSource(doc), new StreamResult(new File(XML_FILE)));
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private static void append(Document doc, Element parent, String tag, String txt) {
        Element c = doc.createElement(tag);
        c.appendChild(doc.createTextNode(txt));
        parent.appendChild(c);
    }
}
