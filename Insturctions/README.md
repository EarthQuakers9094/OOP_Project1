# FRC Java OOP III — Robot Mechanisms

In this project, we'll continue building on our `Motor`, `Drivetrain`, and `Intake` classes while learning how to organize more complicated robot code using **inheritance, abstract classes, and interfaces**.

This is a **plain Java simulation**, not deployable FRC robot code. The starter project compiles and runs, but some files are intentionally incomplete for you to work on during class.

## The Four Pillars of Object-Oriented Programming (OOP)

Object-oriented programming (OOP) is a way of organizing code around classes and objects. There are four main principles, often called the **four pillars of OOP**.

### 1. Encapsulation — Protecting Data

Encapsulation means keeping fields and methods together in a class while controlling how other classes access or modify that data.

**Example:** Our `Motor` class has a private `speed` field. Other classes must use a method like `setSpeed()` to change its value rather than modifying it directly.

### 2. Abstraction — Hiding Details

Abstraction means hiding unnecessary implementation details and focusing on what an object can do rather than exactly how it works.

**Example:** Our `Mechanism` class will define a `setPosition()` method. Other classes can call this method without needing to know exactly how the mechanism moves.

### 3. Inheritance — Reusing Code

Inheritance allows one class to extend another class, inheriting its fields and methods. This helps us avoid writing the same code multiple times.

**Example:** `Arm` and `Elevator` both extend `Mechanism`. They inherit shared functionality like `getName()`, but each can also have its own unique behavior.

### 4. Polymorphism — Same Type, Different Behavior

Polymorphism allows objects of different classes to be treated as the same general type, even when they behave differently.

**Example:** Both `Arm` and `Elevator` are types of `Mechanism`. We can call `setPosition()` on either object, but each class implements the method differently.

### Summary

| Encapsulation | Protect the data |
| Abstraction | Hide the details |
| Inheritance | Reuse and extend code |
| Polymorphism | Same general type, different behavior |

We'll explore these ideas by creating different types of robot mechanisms that share some functionality but have their own unique behaviors.

---

## Getting Started

1. Clone the class GitHub repository using the link provided by Scho, then open the project folder in VS Code.

2. Open a terminal in the project folder and run:

   ```bash
   javac *.java
   java RobotSim
   ```

3. At the `robot>` prompt, try entering the following commands:

   ```text
   drive 40 40
   drive -40 40
   arm 90
   status
   quit
   ```

4. Open `CLASSWORK.md` and complete the activities in order. We'll work through the new concepts together.

5. After class, use `HOMEWORK.md` to practice applying these concepts independently.

**Note:** The `elevator` command will become available after you write `Elevator.java` and add it to `RobotSim.java`.

---

## Project Files

| File | Description |
|---|---|
| `Motor.java` | Existing class with private fields, methods, and a static counter |
| `Drivetrain.java` | Contains four `Motor` objects |
| `Intake.java` | Existing intake class |
| `RobotSim.java` | Interactive simulation with a prewritten `Scanner` loop |
| `Mechanism.java` | Parent class that we'll turn into an abstract class |
| `Arm.java` | Existing subclass that we'll modify together |
| `Elevator.java` | New subclass you'll write |
| `BatteryMonitor.java` | Existing class used to practice interfaces |
| `Loggable.java` | Interface we'll write together |
| `Turret.java` | New class you'll create for homework |
| `Resettable.java` | New interface you'll create for homework |

---

## What We'll Be Working On

We'll start by reviewing how classes can contain objects of other classes. Then we'll work through three new ideas:

**Part 1: Inheritance and Method Overriding**
- Use `extends` to create subclasses.
- Explore the difference between *is-a* and *has-a* relationships.
- Use `super()` to call a parent class constructor.
- Override methods so different mechanisms can behave differently.

**Part 2: Abstract Classes**
- Turn `Mechanism` into an abstract class.
- Learn why some classes should not be instantiated directly.
- Use abstract methods to require subclasses to provide their own implementations.
- Explore `private`, `protected`, and `public` access.

**Part 3: Interfaces**
- Create a `Loggable` interface.
- Use `implements` to add common functionality to different classes.
- Compare interfaces with inheritance and abstract classes.

If we don't reach interfaces during class, we'll continue that section next session.

---

## Simulator Commands

The program includes a simple command-line simulator so you can test your code.

| Command | Action |
|---|---|
| `drive 50 50` | Drive both sides forward at 50% |
| `drive -50 50` | Turn in place |
| `arm 90` | Move the simulated arm to 90 degrees |
| `elevator 1.5` | Move the elevator after implementing its class |
| `intake` | Run the intake |
| `outtake` | Reverse the intake |
| `stop` | Stop drivetrain motors |
| `status` | Print the current robot state |
| `help` | Display available commands |
| `quit` | Exit the simulator |

The `Scanner` input loop is **already written for you**. You do not need to build a command parser for this assignment.

As you add new mechanisms, you'll modify parts of `RobotSim.java` to connect your classes to the simulator.

---

## A Few Reminders

- **Compile and run your code regularly.** Don't wait until you've finished an entire section.
- If your code doesn't compile, start by reading the **first error message** and checking the file and line number.
- Remember that `private` fields cannot be directly accessed by subclasses.
- An abstract class can contain both regular methods and abstract methods.
- A class that extends an abstract class must implement its abstract methods unless the subclass is also abstract.

Most importantly, pay attention to **why we're organizing our code this way**. As our robot gets more complicated, using inheritance, abstraction, and interfaces can help us write code that's easier to reuse, maintain, and expand.