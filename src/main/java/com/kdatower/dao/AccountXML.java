package com.kdatower.dao;

import com.kdatower.model.Account;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;

public class AccountXML {
    private static final String XML_FILE = "account_data.xml";

    public static List<Account> readAccounts() {
        List<Account> list = new ArrayList<>();
        try {
            File file = new File(XML_FILE);
            if (!file.exists()) return list;
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            NodeList nList = doc.getElementsByTagName("Account");
            for (int i = 0; i < nList.getLength(); i++) {
                Element e = (Element) nList.item(i);
                Account a = new Account();
                a.setUsername(e.getElementsByTagName("username").item(0).getTextContent());
                a.setPassword(e.getElementsByTagName("password").item(0).getTextContent());
                list.add(a);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return list;
    }

    public static void writeAccounts(List<Account> accounts) {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element root = doc.createElement("Accounts");
            doc.appendChild(root);
            for (Account a : accounts) {
                Element accElem = doc.createElement("Account");

                Element username = doc.createElement("username");
                username.appendChild(doc.createTextNode(a.getUsername()));
                accElem.appendChild(username);

                Element password = doc.createElement("password");
                password.appendChild(doc.createTextNode(a.getPassword()));
                accElem.appendChild(password);

                root.appendChild(accElem);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File(XML_FILE)));
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}
