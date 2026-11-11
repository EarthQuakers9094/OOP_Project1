// IN CLASS: Start with this ordinary parent class.
// Later, we will discuss whether Mechanism should be abstract.
public abstract class Mechanism {
    private String name;
    private double position;

    public Mechanism(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() { return name; }
    public double getPosition() { return position; }

    //student code must override this and implement correct checks
    //to prevent mechanisms from breaking 
    //GENERALLY ON ROBOT: CODE WRITTEN BY YOU WITH @OVERRIDE
    public abstract void setPosition(double position); 

    //update position has no checks
    //GENERALLY ON ROBOT: CODE WRITTEN BY OTHERS
    protected void updatePosition(double position) {
        this.position = position;
    }
}
