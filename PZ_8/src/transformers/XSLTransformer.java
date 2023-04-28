package transformers;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class XSLTransformer {
    public static void main() {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();

            Source xslt = new StreamSource(new java.io.File("src/device.xsl"));
            Source xml = new StreamSource(new java.io.File("src/inputDevice.xml"));

            Transformer transformer = factory.newTransformer(xslt);
            transformer.setParameter("root", "Critical");

            // Execute the transformation and output the result
            transformer.transform(xml, new StreamResult(new java.io.File("src/outputDevice.xml")));

            System.out.println("Transformation complete successfully!");
        } catch (Exception e) {
            System.out.println("Transformation failed!");
            e.printStackTrace();
        }
    }
}