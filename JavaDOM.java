/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadom;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author szilvacsku
 */
public class JavaDOM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TransformerException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        /*
        <beosztottak>
            <programozok>
                <beosztott azonosito="asd">Nagy Lajos</beosztott>
                <beosztott azonosito="asd2">Nagy Lajos2</beosztott>
            </programozok>
        </beosztottak>
        
         */
        builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element rootElement = doc.createElement("beosztottak");
        doc.appendChild(rootElement);

        Element programozok = doc.createElement("programozok");
        rootElement.appendChild(programozok);

        Element beosztott = doc.createElement("beosztott");
        beosztott.appendChild(doc.createTextNode("Nagy Lajos"));
        beosztott.setAttribute("azonosito", "SDAW");
        programozok.appendChild(beosztott);

        Element beosztott2 = doc.createElement("beosztott");
        beosztott2.appendChild(doc.createTextNode("Nagy Sandor"));
        beosztott2.setAttribute("azonosito", "WASD");

        programozok.appendChild(beosztott2);

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        StreamResult file = new StreamResult(new File("/home/szilvacsku/emps.xml"));

        transformer.transform(source, file);

    }

}
