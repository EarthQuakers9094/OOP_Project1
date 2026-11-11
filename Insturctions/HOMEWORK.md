# Homework — Extend the Robot Simulator

**Target:** Approximately 1–2 hours of independent coding practice after the classwork on inheritance, abstract classes, and interfaces. If interfaces are carried into the following meeting, complete these tasks afterward.

## Part 1 — Create a Turret mechanism

Create `Turret.java` from scratch. A turret rotates a shooter toward a target.

1. Extend the abstract `Mechanism` class, and write a constructor using `super("Turret")`.
2. Override `setPosition(double position)`. Allow angles from **−170 to +170 degrees** (inclusive). Invalid values must not change the current position.
3. Implement `Loggable`, with `printStatus()` showing the current turret angle.
4. Extend the existing command loop in `RobotSim` with `turret <degrees>` (copy the structure of the `arm` command). Test **90°**, **200°**, **−170°**, and **−175°**. Check that invalid commands preserve the previous valid position.

**Reflection:** Which methods does `Turret` inherit? Which does it override or implement itself?

## Part 2 — Write a new interface: Resettable

Sometimes we want components to reset their tracked state. Resetting an arm is different from clearing a battery monitor's recorded low voltage, but both can support a `reset()` operation.

1. Write **`Resettable.java`** yourself: a public interface requiring `void reset()`.
2. Have `Arm`, `Elevator`, and `Turret` implement `Resettable`. Each `reset()` should set its *simulated position* to **0**, using the appropriate existing method.
3. Have `BatteryMonitor` implement `Resettable`. The current voltage is a measurement—it must **not** be changed by reset. Instead, `reset()` should set `lowestVoltage` to the **current voltage**, starting a fresh low-voltage history. Do not introduce a public setter for its private fields.
4. Update status output if necessary to confirm each change.

**Think:** Why do these four objects share a reset capability but not a common `Mechanism` parent class?

## Part 3 — A reusable reset operation

In `RobotSim`, write this **static** method:

```java
public static void resetAll(Resettable[] components) {
    // Use a loop to reset every component.
}
```

In `main()`:

1. Create an `Arm`, `Elevator`, `Turret`, and `BatteryMonitor`.
2. Move the mechanisms away from zero and change the battery voltage a few times so its recorded minimum differs from the current voltage.
3. Create a `Resettable[]` containing all four objects.
4. Add a `reset` command to the existing `RobotSim` command loop that calls `resetAll(...)`. Call it, then print all statuses using a `Loggable[]` array.
5. Confirm that all mechanism positions are zero, while **battery current voltage is unchanged** and its lowest-recorded voltage now equals the current voltage.

**Explain:** How can `resetAll()` work without knowing which kind of component it receives?

## Part 4 — Test and debug independently

Use the simulator commands to test your code rather than only assuming your methods work; add automated checks in `RobotSim` if useful.

| Test | Expected behavior |
|---|---|
| Arm at 90°, then 200° | 200° rejected; arm remains at 90° |
| Elevator at 1.5 m, then 3.0 m | 3.0 m rejected; height remains 1.5 m |
| Turret at −90°, then 200° | 200° rejected; turret remains at −90° |
| Reset mechanisms | All positions return to zero |
| Battery update 12.6 → 11.7 → 12.1 V | Current = 12.1 V; lowest = 11.7 V |
| Reset battery | Current remains 12.1 V; lowest becomes 12.1 V |
| Loop through `Loggable[]` | Every object reports its own status |

Answer briefly in comments or a separate text file:

- How are `extends` and `implements` different?
- Why is `Mechanism` abstract?
- Which classes in your project demonstrate polymorphism? Show an example.

## Optional stretch — Shooter

Create a `Shooter` class **without** extending `Mechanism`. It should contain a `Motor`, offer methods to set shooter speed and stop, and implement both `Loggable` and `Resettable`. Show that `resetAll()` works with this class too. Explain why a speed-controlled shooter need not be treated as a position-controlled `Mechanism`.

## Turn in

Submit your completed Java source files (and answers to the reflection questions). The entire project should compile with `javac *.java` and run with `java RobotSim`.
