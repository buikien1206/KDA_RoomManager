package com.kdatower.dao;

import com.kdatower.model.Tenant;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;

public class TenantXML {
    private static final String XML_FILE = "tenant_data.xml";

    public static List<Tenant> readTenants() {
        List<Tenant> list = new ArrayList<>();
        try {
            File file = new File(XML_FILE);
            if (!file.exists()) return list;

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            NodeList nList = doc.getElementsByTagName("Tenant");
            for (int i = 0; i < nList.getLength(); i++) {
                Element e = (Element) nList.item(i);
                Tenant t = new Tenant();
                t.setId(e.getElementsByTagName("id").item(0).getTextContent());
                t.setName(e.getElementsByTagName("name").item(0).getTextContent());
                t.setGender(e.getElementsByTagName("gender").item(0).getTextContent());
                t.setRoomId(e.getElementsByTagName("roomId").item(0).getTextContent());
                list.add(t);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static void writeTenants(List<Tenant> tenants) {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element root = doc.createElement("Tenants");
            doc.appendChild(root);
            for (Tenant t : tenants) {
                Element tenantElem = doc.createElement("Tenant");

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(t.getId()));
                tenantElem.appendChild(id);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(t.getName()));
                tenantElem.appendChild(name);

                Element gender = doc.createElement("gender");
                gender.appendChild(doc.createTextNode(t.getGender()));
                tenantElem.appendChild(gender);

                Element roomId = doc.createElement("roomId");
                roomId.appendChild(doc.createTextNode(t.getRoomId()));
                tenantElem.appendChild(roomId);

                root.appendChild(tenantElem);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File(XML_FILE)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
