import java.util.Scanner;

// A simple terminal simulator. The command loop is already written for you.
// Focus on the OOP exercises in CLASSWORK.md and HOMEWORK.md.
public class RobotSim {
    public static void main(String[] args) {
        Drivetrain drivetrain = new Drivetrain();
        Intake intake = new Intake();
        Arm arm = new Arm();

        // IN CLASS: After writing Elevator.java, replace null with new Elevator().
        //Scho Notes: rewrite to Elevator eleavtor 
        Elevator elevator = new Elevator();

        BatteryMonitor battery = new BatteryMonitor(12.6);

        Loggable[] devices = {arm, elevator, battery};

        // AFTER WRITING ELEVATOR: Try creating a generic Mechanism here. Later, when
        
        //Mechanism generic = new Mechanism("Generic Mechanism");

        Scanner scanner = new Scanner(System.in);
        printHelp();

        boolean running = true;
        while (running) {
            System.out.print("\nrobot> ");
            if (!scanner.hasNextLine()) break;
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) continue;
            String[] parts = input.split("\\s+");

            try {
                switch (parts[0]) {
                    case "drive":
                        // Example: drive 40 40, or drive -40 40 to turn.
                        if (parts.length != 3) {
                            System.out.println("Usage: drive <leftSpeed> <rightSpeed>");
                            break;
                        }
                        drivetrain.drive(Double.parseDouble(parts[1]),
                                         Double.parseDouble(parts[2]));
                        drivetrain.printStatus();
                        break;
                    case "stop":
                        drivetrain.stop();
                        System.out.println("Drivetrain stopped.");
                        break;
                    case "arm":
                        if (parts.length != 2) {
                            System.out.println("Usage: arm <angleInDegrees>");
                            break;
                        }
                        arm.setPosition(Double.parseDouble(parts[1]));
                        System.out.println("Arm position: " + arm.getPosition() + " degrees");
                        break;
                    case "elevator":
                        if (parts.length != 2) {
                            System.out.println("Usage: elevator <heightInMeters>");
                            break;
                        }
                        if (elevator == null) {
                            System.out.println("Elevator not created yet. Complete Elevator.java,");
                            System.out.println("then update the elevator variable in RobotSim.java.");
                            break;
                        }
                        elevator.setPosition(Double.parseDouble(parts[1]));
                        System.out.println("Elevator position: " + elevator.getPosition() + " meters");
                        break;
                    case "intake":
                        intake.intake();
                        intake.printStatus();
                        break;
                    case "outtake":
                        intake.outtake();
                        intake.printStatus();
                        break;
                    case "status":
                        for(Loggable device : devices){
                            device.printStatus(); 
                        }

                        // IN CLASS: After adding Loggable, try replacing this
                        // section with a Loggable[] loop over the components.
                        break;
                    case "help":
                        printHelp();
                        break;
                    case "quit":
                        drivetrain.stop();
                        intake.stop();
                        running = false;
                        break;
                    default:
                        System.out.println("Unknown command. Type 'help' for options.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number (example: arm 90).");
            }
            // HOMEWORK: Add commands such as 'turret 90' or 'reset'.
        }
        scanner.close();
        System.out.println("Simulator closed.");
    }

    private static void printHelp() {
        System.out.println("=== ROBOT SIMULATOR ===");
        System.out.println("drive <left> <right>  Set tank-drive motor speeds (-100 to 100)");
        System.out.println("arm <degrees>         Set arm angle (0 to 180)");
        System.out.println("elevator <meters>     Set elevator height (after you write Elevator)");
        System.out.println("intake / outtake      Run intake motor");
        System.out.println("stop                  Stop drivetrain");
        System.out.println("status                Print component status");
        System.out.println("help                  Show these commands");
        System.out.println("quit                  Exit the simulator");
    }
}
