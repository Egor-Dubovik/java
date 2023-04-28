package handlers;

import device.DeviceComponent;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeviceSAXHandler extends DefaultHandler {
    private List<DeviceComponent> components = new ArrayList<>();
    private DeviceComponent currentComponent;

    public List<DeviceComponent> getComponents() {
        return components;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Component")) {
            currentComponent = new DeviceComponent();
            currentComponent.setName(attributes.getValue("Name"));
            currentComponent.setOrigin(attributes.getValue("Origin"));
            currentComponent.setPrice(Integer.parseInt(attributes.getValue("Price")));
            currentComponent.setType(attributes.getValue("Type"));
            currentComponent.setEnergyConsumption(Integer.parseInt(attributes.getValue("EnergyConsumption")));
            currentComponent.setCooler(attributes.getValue("Cooler"));
            currentComponent.setGroup(attributes.getValue("Group"));
            String[] ports = attributes.getValue("Ports").split(",");
            List<String> portList = new ArrayList<>();
            Collections.addAll(portList, ports);
            currentComponent.setPorts(portList);
            components.add(currentComponent);
        }
    }
}
