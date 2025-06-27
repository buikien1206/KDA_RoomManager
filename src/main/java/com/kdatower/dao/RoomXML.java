package com.kdatower.dao;

import com.kdatower.model.Room;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;

public class RoomXML {
    private static final String XML_FILE = "room_data.xml";

    public static List<Room> readRooms() {
        List<Room> list = new ArrayList<>();
        try {
            File file = new File(XML_FILE);
            if (!file.exists()) return list;

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            NodeList nList = doc.getElementsByTagName("Room");
            for (int i = 0; i < nList.getLength(); i++) {
                Element e = (Element) nList.item(i);
                Room r = new Room();
                r.setId(e.getElementsByTagName("id").item(0).getTextContent());
                r.setName(e.getElementsByTagName("name").item(0).getTextContent());
                r.setBlockId(e.getElementsByTagName("blockId").item(0).getTextContent());
                list.add(r);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static void writeRooms(List<Room> rooms) {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element root = doc.createElement("Rooms");
            doc.appendChild(root);
            for (Room r : rooms) {
                Element roomElem = doc.createElement("Room");

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(r.getId()));
                roomElem.appendChild(id);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(r.getName()));
                roomElem.appendChild(name);

                Element blockId = doc.createElement("blockId");
                blockId.appendChild(doc.createTextNode(r.getBlockId()));
                roomElem.appendChild(blockId);

                root.appendChild(roomElem);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File(XML_FILE)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
