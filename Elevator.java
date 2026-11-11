// INDEPENDENT IN CLASS: Write the Elevator subclass here.
// 1. Extend Mechanism.
// 2. Add a constructor calling super("Elevator").
// 3. Override setPosition(double position).
//    Valid heights are 0.0 to 2.0 meters (inclusive).
// 4. Reject invalid heights without changing the current position.
//
// Start by replacing this comment with your class declaration.

public class Elevator extends Mechanism implements Loggable, Resettable{

    public Elevator(){
        super("Elevator");
    }

    @Override
    public void setPosition(double position){
        if(position >= 0 & position <= 2.0){
            updatePosition(position);
        }
        else{
            System.out.println("Error: Please enter a height between 0.0 and 2.0 meters");
        }
    }

    @Override 
    public void printStatus(){
        System.out.println("Elevator Position: " + super.getPosition());
    }

    @Override
    public void reset() {
        updatePosition(0);
    }



}