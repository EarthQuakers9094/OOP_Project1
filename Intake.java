public class Intake {
    private Motor motor;

    public Intake() { motor = new Motor("Intake"); }

    public void intake() { motor.setSpeed(50); }
    public void outtake() { motor.setSpeed(-50); }
    public void stop() { motor.stop(); }
    public void printStatus() { motor.printStatus(); }
}
