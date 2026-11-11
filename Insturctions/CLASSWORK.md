# Classwork — Inheritance, Abstract Classes, and Interfaces

**Goal:** Use our Java robot simulator to practice inheritance, overriding methods, abstract classes, and interfaces. We'll also learn the differences between *has-a* and *is-a* relationships.

Work through the sections in order. If we don't reach interfaces today, we'll pick up there next class.

## 0. Examine the project (quick review)

Open `RobotSim.java`, `Drivetrain.java`, and `Motor.java`. Run the starter program and try these commands:

```text
drive 40 40
arm 90
status
quit
```

The `Scanner` loop is already written for you.

1. Where is `main()`?
2. Which class contains four `Motor` objects?
3. Find the `drive()` method in `Drivetrain`. How does having this method save us work elsewhere in the program?

## 1. Guided example: Arm extends Mechanism

Open `Mechanism.java` and `Arm.java`. A `Mechanism` stores a name and a simulated position. An `Arm` **is a** `Mechanism`, so it can inherit methods that are useful for all mechanisms.

**With Scho:**

1. Look at `extends Mechanism` and `super("Arm")`. What does each do?
2. In `Arm.java`, override `setPosition(double position)`. Include `@Override` above the method.
3. The arm can move from **0 to 180 degrees**, inclusive. If a requested position is outside that range, print an error and leave the current position unchanged.
4. Test `arm 90` and `arm 200` in the simulator. Check the reported position after each command.

**Think about it:** Why can we call `arm.getPosition()` when there is no `getPosition()` method in `Arm.java`? When you write `super.setPosition(position)`, which version of the method runs?

## 2. Independent practice: Elevator

Create `Elevator.java`, using the TODO as a guide.

- Have `Elevator` extend `Mechanism`.
- In its constructor, call `super("Elevator")`.
- Override `setPosition(double position)`. Valid positions are **0.0 to 2.0 meters**, inclusive.
- If the requested position is invalid, print an error and keep the previous position.

In `RobotSim.java`, replace `Mechanism elevator = null;` with:

```java
Mechanism elevator = new Elevator();
```

Test `elevator 1.5` and `elevator 3.0`. Does the elevator keep its last valid position?

## 3. Design change: Make Mechanism abstract

Right now, the simulator can create a generic `Mechanism` object. Find the commented-out example in `RobotSim.java`, uncomment it, and try compiling. It works—but what would a generic mechanism actually represent on our robot?

We want to create specific mechanisms, such as an arm or elevator, without allowing a plain `Mechanism` object. We also want each type of mechanism to decide which positions it can accept.

**With Scho:**

1. **Make the class abstract.** Change the declaration in `Mechanism.java` to:

   ```java
   public abstract class Mechanism {
   ```

   An abstract class cannot be instantiated directly. Leave the generic `new Mechanism(...)` line in `RobotSim` for a moment. What error do you get?

2. **Make `setPosition()` abstract.** Replace its existing implementation in `Mechanism` with:

   ```java
   public abstract void setPosition(double position);
   ```

   An abstract method has **no method body**. This means that a concrete subclass, like `Arm` or `Elevator`, must provide the implementation. We still keep ordinary methods like `getName()` and `getPosition()` in `Mechanism` because those work the same way for every mechanism.

3. **Solve the private-field problem.** Your `Arm` and `Elevator` methods previously called `super.setPosition(position)`. That won't work now: the parent method no longer contains any code.

   Could you just write `this.position = position;` in `Arm`? Try it or predict what happens. The `position` field is **private** in `Mechanism`, so subclasses cannot access it directly.

   Add this method *inside `Mechanism`*:

   ```java
   protected void updatePosition(double position) {
       this.position = position;
   }
   ```

   `protected` is another access modifier:

   | Modifier | Who can use it? |
   | --- | --- |
   | `private` | Only code in that class |
   | `protected` | Code in that class, its subclasses, and other classes in the same package |
   | `public` | Code in any class |

   The new helper method can change `position` because it's written **inside `Mechanism`**. `Arm` and `Elevator` can call it because it's **protected**. We don't make it public because other code should normally request a new position through `setPosition()`, which checks the limits first.

4. **Update the subclasses.** In `Arm.java` and `Elevator.java`, replace:

   ```java
   super.setPosition(position);
   ```

   with:

   ```java
   updatePosition(position);
   ```

   Keep the range checks you wrote earlier. The two methods have different jobs:

   - `setPosition()` checks whether a requested position is allowed.
   - `updatePosition()` stores the position after the check.

5. **Test again.** Comment out the generic `new Mechanism(...)` line in `RobotSim`. Recompile. Make sure `arm 90`, `arm 200`, `elevator 1.5`, and `elevator 3.0` behave as before.

**Discuss:** What's the difference between an abstract **class** and an abstract **method**? Why can `Mechanism` still have regular methods like `getName()`? Why do we use a protected helper instead of letting every subclass change the private field?

## 4. Interface design problem: Loggable

Our arm, elevator, and `BatteryMonitor` should all be able to report their status. But a battery monitor **is not a** mechanism, so extending `Mechanism` wouldn't make sense.

How could we require these unrelated classes to have the same status-reporting method?

**With Scho:**

1. Open `Loggable.java` and declare a public interface with a `void printStatus();` method.
2. Have `Arm` extend `Mechanism` **and** implement `Loggable`:

   ```java
   public class Arm extends Mechanism implements Loggable {
   ```

   Add a `printStatus()` method, with `@Override`, that prints the arm's angle in degrees.
3. Have `BatteryMonitor` implement `Loggable`. It already has a `printStatus()` method. Check that the method matches the interface, and add `@Override`.

## 5. Independent practice: Loggable Elevator

1. Have `Elevator` implement `Loggable` and add `printStatus()` to print its current height in meters.
2. Find the `status` command in `RobotSim`. Create a `Loggable[]` containing your arm, elevator, and `BatteryMonitor` objects.
3. Use a loop to call `printStatus()` on each object. The command parser is already written; you only need to change the status-reporting code.

**Explain:** How can a `Loggable` variable call `printStatus()` even when the objects are different types? Why can't that same variable directly call `setPosition()`?

## Check your understanding

- **Composition:** A `Drivetrain` **has** `Motor` objects.
- **Inheritance:** An `Arm` **is a** `Mechanism`.
- **Abstract class:** We can share fields and methods without allowing a generic `Mechanism` object.
- **Abstract method:** Concrete subclasses must provide an implementation.
- **Interface:** Different classes can agree to provide a capability, such as `printStatus()`.
- A class can `extend` a parent class and `implement` an interface at the same time.

**After class:** Work on `HOMEWORK.md` after we've covered the relevant material.
