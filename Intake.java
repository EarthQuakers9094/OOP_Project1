public class Intake extends Mechanism implements Loggable,Resettable{
    private Motor deployMotor;
    private Motor spinMotor; 

    public Intake() { 
        deployMotor = new Motor("Intake"); 
        spinMotor = new Motor("Spin Motor");
        super("Intake");
    }

    public void intake() { spinMotor.setSpeed(50); }
    public void outtake() { spinMotor.setSpeed(-50); }
    public void stop() { spinMotor.stop(); }
    

    @Override
    public void reset() {
     
    }

    @Override
    public void setPosition(double position) {
        if(position <= 90 && position >= 0){
            updatePosition(position);
        }
    }

    @Override
    public void printStatus() {
        System.out.println("Intake position: " + super.getPosition() + "Spinner Speed: " + spinMotor.getSpeed());
    }
}
