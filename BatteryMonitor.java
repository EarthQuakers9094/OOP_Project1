// This is NOT a Mechanism: it tracks electrical information, not position.
public class BatteryMonitor implements Loggable, Resettable{
    private double voltage;
    private double lowestVoltage;

    public BatteryMonitor(double startingVoltage){
        voltage = startingVoltage;
        lowestVoltage = startingVoltage;
    }

    public void updateVoltage(double newVoltage) {
        if (newVoltage < 0) return;
        voltage = newVoltage;
        if (newVoltage < lowestVoltage) lowestVoltage = newVoltage;
    }

    public double getVoltage() { return voltage; }
    public double getLowestVoltage() { return lowestVoltage; }

    @Override 
    public void printStatus() {
        System.out.println("Battery: " + voltage + " V (lowest: " + lowestVoltage + " V)");
    }

    @Override
    public void reset() {
        lowestVoltage = voltage;
    }


    // HOMEWORK: Implement Resettable and write reset() so that
    // lowestVoltage becomes the current voltage; do not change voltage.
}
