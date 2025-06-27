package com.kdatower.dao;

import com.kdatower.model.Resident;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;

public class ResidentXML {
    private static final String XML_FILE = "resident_data.xml";

    public static List<Resident> readResidents() {
        List<Resident> list = new ArrayList<>();
        try {
            File f = new File(XML_FILE);
            if (!f.exists()) return list;
            Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
            NodeList nodes = d.getElementsByTagName("Resident");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element e = (Element) nodes.item(i);
                Resident r = new Resident();
                r.setName(e.getElementsByTagName("name").item(0).getTextContent());
                r.setGender(e.getElementsByTagName("gender").item(0).getTextContent());
                r.setDateOfBirth(e.getElementsByTagName("dateOfBirth").item(0).getTextContent());
                r.setCccd(e.getElementsByTagName("cccd").item(0).getTextContent());
                r.setPhone(e.getElementsByTagName("phone").item(0).getTextContent());
                r.setApartmentId(e.getElementsByTagName("apartmentId").item(0).getTextContent());
                r.setHometown(e.getElementsByTagName("hometown").item(0).getTextContent());
                r.setHouseholdBook(e.getElementsByTagName("householdBook").item(0).getTextContent());
                list.add(r);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static void writeResidents(List<Resident> list) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = doc.createElement("Residents");
            doc.appendChild(root);
            for (Resident r : list) {
                Element e = doc.createElement("Resident");
                append(doc, e, "name", r.getName());
                append(doc, e, "gender", r.getGender());
                append(doc, e, "dateOfBirth", r.getDateOfBirth());
                append(doc, e, "cccd", r.getCccd());
                append(doc, e, "phone", r.getPhone());
                append(doc, e, "apartmentId", r.getApartmentId());
                append(doc, e, "hometown", r.getHometown());
                append(doc, e, "householdBook", r.getHouseholdBook());
                root.appendChild(e);
            }
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new File(XML_FILE)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void append(Document doc, Element parent, String tag, String txt) {
        Element c = doc.createElement(tag);
        c.appendChild(doc.createTextNode(txt));
        parent.appendChild(c);
    }
}
