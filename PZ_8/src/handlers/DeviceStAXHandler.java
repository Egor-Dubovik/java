package handlers;

import device.DeviceComponent;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeviceStAXHandler {
    private List<DeviceComponent> components = new ArrayList<>();
    private DeviceComponent currentComponent;
    private String currentValue;

    public List<DeviceComponent> getComponents(String xml) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        XMLStreamReader reader = (XMLStreamReader) factory.createXMLEventReader(inputStream);

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamConstants.START_ELEMENT:
                    if ("Component".equals(reader.getLocalName())) {
                        currentComponent = new DeviceComponent();
                        currentComponent.setName(reader.getAttributeValue(null, "Name"));
                        currentComponent.setOrigin(reader.getAttributeValue(null, "Origin"));
                        currentComponent.setPrice(Integer.parseInt(reader.getAttributeValue(null, "Price")));
                        currentComponent.setType(reader.getAttributeValue(null, "Type"));
                        currentComponent.setEnergyConsumption(Integer.parseInt(reader.getAttributeValue(null, "EnergyConsumption")));
                        currentComponent.setCooler(reader.getAttributeValue(null, "Cooler"));
                        currentComponent.setGroup(reader.getAttributeValue(null, "Group"));
                        String[] ports = reader.getAttributeValue(null, "Ports").split(",");
                        List<String> portList = new ArrayList<>();
                        Collections.addAll(portList, ports);
                        currentComponent.setPorts(portList);
                        components.add(currentComponent);
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    currentValue = reader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if ("Name".equals(reader.getLocalName())) {
                        currentComponent.setName(currentValue);
                    } else if ("Origin".equals(reader.getLocalName())) {
                        currentComponent.setOrigin(currentValue);
                    } else if ("Price".equals(reader.getLocalName())) {
                        currentComponent.setPrice(Integer.parseInt(currentValue));
                    } else if ("Type".equals(reader.getLocalName())) {
                        currentComponent.setType(currentValue);
                    } else if ("EnergyConsumption".equals(reader.getLocalName())) {
                        currentComponent.setEnergyConsumption(Integer.parseInt(currentValue));
                    } else if ("Cooler".equals(reader.getLocalName())) {
                        currentComponent.setCooler(currentValue);
                    } else if ("Group".equals(reader.getLocalName())) {
                        currentComponent.setGroup(currentValue);
                    } else if ("Ports".equals(reader.getLocalName())) {
                        String[] ports = currentValue.split(",");
                        List<String> portList = new ArrayList<>();
                        Collections.addAll(portList, ports);
                        currentComponent.setPorts(portList);
                    }
                    break;
            }
        }

        return components;
    }
}
