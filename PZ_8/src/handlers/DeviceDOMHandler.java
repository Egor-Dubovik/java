//package handlers;
//
//import device.DeviceComponent;
//import org.w3c.dom.*;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.*;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class DeviceDOMHandler {
//    private List<DeviceComponent> components = new ArrayList<>();
//    public List<DeviceComponent> getComponents() {
//        return components;
//    }
//
//    public void parseXML(String xmlFilePath) {
//        try {
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(xmlFilePath);
//            doc.getDocumentElement().normalize();
//
//            NodeList nodeList = doc.getElementsByTagName("Component");
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                Node node = nodeList.item(i);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element element = (Element) node;
//                    DeviceComponent component = new DeviceComponent();
//                    component.setName(getValue("Name", element));
//                    component.setOrigin(getValue("Origin", element));
//                    component.setPrice(Integer.parseInt(getValue("Price", element)));
//                    component.setType(getValue("Type", element));
//                    component.setEnergyConsumption(Integer.parseInt(getValue("EnergyConsumption", element)));
//                    component.setCooler(getValue("Cooler", element));
//                    component.setGroup(getValue("Group", element));
//                    String[] ports = getValue("Ports", element).split(",");
//                    List<String> portList = new ArrayList<>();
//                    Collections.addAll(portList, ports);
//                    component.setPorts(portList);
//                    components.add(component);
//                }
//            }
//        } catch (ParserConfigurationException | SAXException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String getValue(String tag, Element element) {
//        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
//        Node node = nodeList.item(0);
//        return node.getNodeValue();
//    }
//
//}
