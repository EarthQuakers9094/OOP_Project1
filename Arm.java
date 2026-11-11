// GUIDED IN CLASS: Use this starter to demonstrate extends, super, and @Override.
public class Arm extends Mechanism implements Loggable, Resettable{
    public Arm() {
        super("Arm");
    }

    @Override 
    public void setPosition(double position){
        if(position >= 0 && position <= 180){
            updatePosition(position);
        }
        else{
            System.out.println("Error: Enter an angle between 0 and 180");
        }
    }

    @Override 
    public void printStatus(){
        System.out.println("Arm Position: " + super.getPosition());
    }

    @Override
    public void reset() {
        updatePosition(0);
    }


    // TODO (together): Override setPosition(double position).
    // Arm angles must stay between 0 and 180 degrees.
    // For valid positions, call super.setPosition(position).
    // For invalid positions, print an error and leave position unchanged.
}
