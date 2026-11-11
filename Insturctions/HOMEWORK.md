
# Homework — OOP Robot Design Challenge

**Goal:** Apply inheritance, abstract classes, composition, interfaces, and the Java skills you've practiced to expand our robot simulator.

In class, we worked through several examples of how classes can share functionality and interact with one another. For this assignment, you'll apply those concepts more independently. You'll have some specific requirements, but you'll also need to make decisions about how to structure your classes and implement their behavior.

Use the existing robot simulator project as your starting point. You may modify existing classes, add fields and methods, and create new files.

**Test your changes through `RobotSim.java`.** The terminal input loop is already written; you do not need to rewrite the `Scanner` code.

## Part 1 — Create a Climber Subsystem

Our robot needs a climber that can extend and retract a hook to reach a climbing bar.

In class, you created `Arm` and `Elevator` classes that extend `Mechanism`. Now you'll create a `Climber` class independently, using those classes as examples.

### Requirements

1. Create a new class called `Climber` that **extends `Mechanism`**.
2. Write a constructor that calls the parent constructor using `super()`.
3. Override `setPosition(double position)` to represent the climber's simulated extension.
4. Choose a reasonable minimum and maximum extension. Reject invalid positions **without changing the current position**.
5. Add two methods:
   - `extend()` — move the simulated climber to its maximum extension.
   - `retract()` — return the simulated climber to its minimum extension.
6. Implement `Loggable` so your climber can report its current extension.

### Testing and Terminal Commands

Update `RobotSim.java` so you can control your climber through the terminal.

1. Create a `Climber` object alongside the existing robot components.
2. Find the existing `switch` statement that processes terminal commands. Add cases to:
   - Set the climber to a specific position (for example, `climber 1.5`).
   - Fully extend the climber (`extend`).
   - Fully retract the climber (`retract`).
   - Print the climber's current status, either with a dedicated command or as part of `status`.
3. **Print a short message in `RobotSim` for each command** describing what the robot is doing.
4. Update the `printHelp()` method so users can discover your new commands.
5. Test valid and invalid positions. An invalid command should not change the climber's stored position.

**Think about:**
- Which fields and methods does `Climber` inherit from `Mechanism`?
- Why does `Climber` need to override `setPosition()`?
- Should `extend()` and `retract()` change the position directly or call `setPosition()`? Why?

## Part 2 — Refactor the Intake

The intake from our earlier project was fairly simple. A real FRC intake often has **two motors with different jobs**:

- **Spinner motor:** Spins the rollers to collect or eject game pieces.
- **Deploy motor:** Raises and lowers the intake between **0° (retracted)** and **90° (deployed)**.

Refactor `Intake.java` so it represents both functions. This is a design task: you decide which fields and methods you need and how to organize them.

### Requirements

Your updated intake should:

1. Contain **two separate `Motor` objects**, one for spinning and one for deployment.
2. Support deploying, retracting, collecting game pieces, ejecting game pieces, and stopping. Decide what each action does and which motor(s) it controls.
3. Track the intake's simulated angle from **0° to 90°** and reject positions outside that range.
4. Track **both motor speeds independently**, using the existing `Motor` functionality rather than duplicating speed fields unnecessarily.
5. Add a `boolean hasGamePiece` field to represent whether a game piece is currently held. Decide when and how it changes.
6. Implement `Loggable`. Its `printStatus()` method should report:
   - Intake angle in degrees
   - Deploy motor speed (%)
   - Spinner motor speed (%)
   - Whether the intake has a game piece

You may modify existing methods or create new ones as needed.

### Design Questions

Before writing code, consider:

- Should `Intake` extend `Mechanism`, contain `Motor` objects, or both? Why?
- Which methods should control deployment versus spinning?
- Should `stop()` stop one motor or both?
- Does spinning the rollers automatically mean a piece was collected? Would you rather use a separate simulated sensor or detection command?
- Our current `Mechanism` position changes directly, without motor movement. How will your intake represent its angle for now?

There is no single required design, but your class must satisfy the behaviors above, and you should be able to explain your choices.

### Testing and Terminal Commands

Update `RobotSim.java` to test your refactored intake.

1. Locate the intake commands already in the `switch` statement. Modify them as needed, and add commands for deploying and retracting.
2. **Each terminal command should print a short message in `RobotSim`** describing the action, such as `"Deploying intake..."` or `"Running intake rollers..."`.
3. Update `printHelp()` to list any added or changed commands.
4. Test collecting, ejecting, deploying, retracting, and stopping. Use `printStatus()` to check **both speeds**, angle, and `hasGamePiece`.
5. Test an invalid angle such as 120° and verify that it is rejected.

You do **not** need to make the deploy motor's speed automatically change the intake's angle yet. That is the design challenge at the end of this assignment.

## Part 3 — Create a `Resettable` Interface

During robot testing, we often need to return different robot components to a known starting state. But resetting does not necessarily mean the same thing for every class.

**How can we give unrelated components a common way to reset themselves?**

### Requirements

1. Create an interface named `Resettable` that requires a `void reset()` method.
2. Have **at least three different robot component classes** implement `Resettable`.
3. One of these classes must be `BatteryMonitor`. Modify `BatteryMonitor.java` so that its `reset()` method resets `lowestVoltage` to the current `voltage`, without changing the current voltage.
4. Choose at least **two additional classes** to implement `Resettable`. Decide what resetting means for each component and implement that behavior.

For example, resetting a drivetrain might stop all its motors, while resetting an arm might return its simulated position to zero. Make sensible choices for your components rather than giving every `reset()` method identical code.

**Think about:** Why does resetting a `BatteryMonitor` mean something different from resetting an arm or elevator? How can the same interface work for classes that aren't related through inheritance?

## Part 4 — Reset the Entire Robot

Now that multiple components implement `Resettable`, write one method that can reset all of them.

Inside `RobotSim.java`, write this **static** method:

```java
public static void resetAll(Resettable[] components) {
    // Your code here
}
```

Be able to explain why the above method must be static.
Your method should use a loop to call `reset()` on every object in the array.

### Testing

1. Create a `Resettable[]` containing at least three different robot components.
2. Put the components into different states before resetting them.
3. Add a `reset` case to the existing `switch` statement that calls `resetAll()`.
4. Print a short message when the reset command runs, and add `reset` to `printHelp()`.
5. Use `status` or other logging commands before and after resetting to verify that each component responds as intended.

**Think about:**
- Why can one `Resettable[]` hold objects of different classes?
- Why is this preferable to calling a different reset method for every component?
- **Why is `resetAll()` declared `static` here?** How does that relate to where you call it from in `RobotSim.java`? Could you make it non-static instead? If so, what would need to change?

## Part 5 — Testing and Reflection

Before submitting, make sure your updated simulator compiles and runs successfully.

Check that:
- Your climber accepts valid positions and rejects invalid ones.
- Your intake's two motors can be controlled appropriately.
- Your intake tracks its angle and game piece state.
- Your `Resettable` implementations work correctly.
- Your `resetAll()` method resets multiple components.
- Your original terminal commands still work.
- Your new commands appear in `printHelp()` and print useful feedback.

Answer the following questions:

1. Identify **one example of inheritance** and **one example of composition** in your updated program. Explain each relationship.
2. Which classes implement `Resettable`, and what does resetting mean for each one?
3. Describe **one design decision** you made independently. What alternatives did you consider, and why did you choose your approach?

---

## Design Challenge — Connecting Motors to Mechanisms

**Strongly recommended: Be creative and push yourself beyond the basic requirements!**

Take another look at our robot simulator. You may have noticed a pretty significant limitation:

**Our `Mechanism` class doesn't actually interact with `Motor` at all.**

Right now, calling `setPosition(90)` simply changes a stored position. No motor runs, and no movement is simulated. Similarly, setting a motor to 50% does not cause the arm, elevator, or climber to change position.

That was intentional when we first wrote these classes, but it isn't a realistic model of robot movement.

### The Design Problem

How could you refactor our simulator so that position-controlled mechanisms actually use motors to move?

Consider the following questions:

1. **Where should the motor live?** Should each `Arm`, `Elevator`, and `Climber` contain its own `Motor`, or should `Mechanism` contain one that all subclasses inherit?
2. **How should movement work?** If a motor runs at 50% speed, how could your program calculate a change in position?
3. **What should `setPosition()` do?** Should it immediately change the position, or should it set a target position that the motor moves toward?
4. **How should limits work?** What happens when a mechanism reaches its minimum or maximum position while its motor is still running?
5. **What about Intake?** It has two motors, but only one controls its position. How should your design account for that?

### One Possible Starting Point — `update()`

You could write an `update()` method that advances the simulation **one step at a time** based on the motor's current speed.

For example, suppose a climber at 100% motor output moves **0.10 meters per update**. At 50% output, each update would move it 0.05 meters:

```text
Motor speed: 50%

Starting position: 0.00 m
After update 1:   0.05 m
After update 2:   0.10 m
After update 3:   0.15 m
After update 4:   0.20 m
```

You **do not** need to make the simulation run continuously.

Instead, add an `update` command to the existing `switch` statement in `RobotSim.java` so that entering `update` advances the simulation **once**. Enter it multiple times to observe the motion. Update `printHelp()` as well.

Try implementing `update()` methods for several mechanisms, such as your arm, elevator, climber, and intake. Each mechanism might use a different movement rate and have different position limits.

Think about which logic belongs in `Mechanism` and which belongs in its subclasses.

**Hint:** If multiple classes have an `update()` method, how could you write one piece of code that updates them all together, like you did with `Loggable` and `Resettable`? Would a new interface help?

And if *every* subclass of `Mechanism` needs `update()`, could the abstract parent class play a role instead?

You can make reasonable assumptions about movement rates and distances. You do **not** need to model real motor physics, use background execution, or work with WPILib.

**Be creative!** The goal is to practice designing how objects interact, deciding where functionality belongs, and using inheritance and composition to avoid repeating code.

If you attempt this challenge, demonstrate your changes through `RobotSim.java` and include a short explanation of your design.

---

## Submission

Submit your updated Java project, including any new classes or interfaces you created, along with your reflection responses.

Your program should compile and run, and you should be prepared to demonstrate its functionality through the terminal.

**Remember:** The goal isn't simply to write code that works. It's to think carefully about **how you organize that code and why your classes are designed the way they are.**
