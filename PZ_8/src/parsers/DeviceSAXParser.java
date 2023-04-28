package parsers;

import device.DeviceComponent;
import handlers.DeviceSAXHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class DeviceSAXParser {

    public static void main(String[] args) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DeviceSAXHandler handler = new DeviceSAXHandler();
            saxParser.parse("PZ_8/src/device/device.xml", handler);

            List<DeviceComponent> components = handler.getComponents();

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
}