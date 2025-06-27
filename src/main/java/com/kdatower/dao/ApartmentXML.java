package com.kdatower.dao;

import com.kdatower.model.Apartment;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;

public class ApartmentXML {
    private static final String XML_FILE = "apartment_data.xml";

    public static List<Apartment> readApartments() {
        List<Apartment> list = new ArrayList<>();
        try {
            File file = new File(XML_FILE);
            if (!file.exists()) return list;

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            NodeList nList = doc.getElementsByTagName("Apartment");
            for (int i = 0; i < nList.getLength(); i++) {
                Element e = (Element) nList.item(i);
                Apartment a = new Apartment();
                a.setId(e.getElementsByTagName("id").item(0).getTextContent());
                a.setOwner(e.getElementsByTagName("owner").item(0).getTextContent());
                a.setArea(Double.parseDouble(e.getElementsByTagName("area").item(0).getTextContent()));
                a.setBuilding(e.getElementsByTagName("building").item(0).getTextContent());
                a.setFloor(Integer.parseInt(e.getElementsByTagName("floor").item(0).getTextContent()));
                a.setStatus(e.getElementsByTagName("status").item(0).getTextContent());
                a.setNumPeople(Integer.parseInt(e.getElementsByTagName("numPeople").item(0).getTextContent()));
                a.setDateIn(e.getElementsByTagName("dateIn").item(0).getTextContent());
                a.setAccount(e.getElementsByTagName("account").item(0).getTextContent());
                list.add(a);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return list;
    }

    public static void writeApartments(List<Apartment> apartments) {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element root = doc.createElement("Apartments");
            doc.appendChild(root);
            for (Apartment a : apartments) {
                Element apartmentElem = doc.createElement("Apartment");

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(a.getId()));
                apartmentElem.appendChild(id);

                Element owner = doc.createElement("owner");
                owner.appendChild(doc.createTextNode(a.getOwner()));
                apartmentElem.appendChild(owner);

                Element area = doc.createElement("area");
                area.appendChild(doc.createTextNode(String.valueOf(a.getArea())));
                apartmentElem.appendChild(area);

                Element building = doc.createElement("building");
                building.appendChild(doc.createTextNode(a.getBuilding()));
                apartmentElem.appendChild(building);

                Element floor = doc.createElement("floor");
                floor.appendChild(doc.createTextNode(String.valueOf(a.getFloor())));
                apartmentElem.appendChild(floor);

                Element status = doc.createElement("status");
                status.appendChild(doc.createTextNode(a.getStatus()));
                apartmentElem.appendChild(status);

                Element numPeople = doc.createElement("numPeople");
                numPeople.appendChild(doc.createTextNode(String.valueOf(a.getNumPeople())));
                apartmentElem.appendChild(numPeople);

                Element dateIn = doc.createElement("dateIn");
                dateIn.appendChild(doc.createTextNode(a.getDateIn()));
                apartmentElem.appendChild(dateIn);

                Element account = doc.createElement("account");
                account.appendChild(doc.createTextNode(a.getAccount()));
                apartmentElem.appendChild(account);

                root.appendChild(apartmentElem);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File(XML_FILE)));
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}
