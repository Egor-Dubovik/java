package parsers;

import device.DeviceComponent;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class DeviceDOMParser {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File("PZ_8/src/device/device.xml");
            Document doc = builder.parse(file);

            List<DeviceComponent> components = new ArrayList<>();

            NodeList nodeList = doc.getElementsByTagName("Component");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    DeviceComponent component = new DeviceComponent();
                    component.setName(getValue("Name", element));
                    component.setOrigin(getValue("Origin", element));
                    component.setPrice(Integer.parseInt(getValue("Price", element)));
                    component.setType(getValue("Type", element));
                    component.setEnergyConsumption(Integer.parseInt(getValue("EnergyConsumption", element)));
                    component.setCooler(getValue("Cooler", element));
                    component.setGroup(getValue("Group", element));
                    component.setPorts(Arrays.asList(getValue("Ports", element).split(",")));
                    components.add(component);
                }
            }

            // Сортировка объектов по цене
            Collections.sort(components, new Comparator<DeviceComponent>() {
                @Override
                public int compare(DeviceComponent c1, DeviceComponent c2) {
                    return Integer.compare(c1.getPrice(), c2.getPrice());
                }
            });

            // Вывод отсортированной коллекции
            for (DeviceComponent component : components) {
                System.out.println(component.getName() + " " + component.getOrigin() + " " +
                        component.getPrice() + " " + component.getType() + " " + component.getEnergyConsumption() + " " +
                        component.getCooler() + " " + component.getGroup() + " " + component.getPorts());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
