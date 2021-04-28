package hardcore.model;

public class Engine {

    private String numberOfInstances;
    private String baseType;
    private String location;
    private String committedUsage;

    public Engine(String numberOfInstances, String baseType, String location, String committedUsage) {
        this.numberOfInstances = numberOfInstances;
        this.baseType = baseType;
        this.location = location;
        this.committedUsage = committedUsage;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCommittedUsage() {
        return committedUsage;
    }

    public void setCommittedUsage(String committedUsage) {
        this.committedUsage = committedUsage;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "numberOfInstances='" + numberOfInstances + '\'' +
                ", baseType='" + baseType + '\'' +
                ", location='" + location + '\'' +
                ", committedUsage='" + committedUsage + '\'' +
                '}';
    }
}
