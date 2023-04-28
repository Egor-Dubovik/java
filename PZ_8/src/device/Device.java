package device;

import java.util.List;


public class Device {
    private List<DeviceComponent> components;
    private boolean critical;

    public List<DeviceComponent> getComponents() {
        return components;
    }

    public void setComponents(List<DeviceComponent> components) {
        this.components = components;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }
}