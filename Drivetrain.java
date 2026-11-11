public class Drivetrain {
    private Motor leftLeader;
    private Motor leftFollower;
    private Motor rightLeader;
    private Motor rightFollower;

    public Drivetrain() {
        leftLeader = new Motor("Left Leader");
        leftFollower = new Motor("Left Follower");
        rightLeader = new Motor("Right Leader");
        rightFollower = new Motor("Right Follower");
    }

    public void drive(double leftSpeed, double rightSpeed) {
        leftLeader.setSpeed(leftSpeed);
        leftFollower.setSpeed(leftLeader.getSpeed());
        rightLeader.setSpeed(rightSpeed);
        rightFollower.setSpeed(rightLeader.getSpeed());
    }

    public void stop() { drive(0, 0); }

    public void printStatus() {
        leftLeader.printStatus();
        leftFollower.printStatus();
        rightLeader.printStatus();
        rightFollower.printStatus();
    }
}
