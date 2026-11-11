public class Motor {
    private String name;
    private double speed;
    private static int motorCount = 0;

    public Motor(String name) {
        this.name = name;
        this.speed = 0;
        motorCount++;
    }

    public String getName() { return name; }
    public double getSpeed() { return speed; }

    public void setSpeed(double speed) {
        if (speed > 100) this.speed = 100;
        else if (speed < -100) this.speed = -100;
        else this.speed = speed;
    }

    public void stop() { setSpeed(0); }

    public void printStatus() {
        System.out.println(name + " motor: " + speed + "%");
    }

    public static int getMotorCount() { return motorCount; }
}
