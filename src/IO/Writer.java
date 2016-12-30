package IO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;

/**
 * Created by paul on 29.12.16.
 */
public class Writer {
    public void writeXml(ArrayList<Datatypes.Item> items, String path) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root element
        Document doc = docBuilder.newDocument();
        Element rootElement =doc.createElement("Items");
        doc.appendChild(rootElement);

        for (Datatypes.Item item: items)
        {
            Element weapon = doc.createElement("weapon");
            for (Datatypes.Attribute attribute: item.getAttributes())
            {
                System.out.println(attribute.getName());
                Element weaponAttribute = doc.createElement(attribute.getName());
                weaponAttribute.appendChild(doc.createTextNode(attribute.getValue()));
                weapon.appendChild(weaponAttribute);
            }
            rootElement.appendChild(weapon);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(path);

        transformer.transform(source, result);
        System.out.println("File saved !");
    }
}
