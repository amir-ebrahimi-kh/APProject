# Java 2D Game Project

This repository contains a standalone Java 2D game project, built entirely from scratch using fundamental Object-Oriented Programming (OOP) mechanics and custom data management.

## Object-Oriented Architecture

The project is structured to demonstrate foundational OOP design principles:

* **Inheritance and Polymorphism:** The game heavily utilizes inheritance to manage game entities. A base abstract class or interface defines the common traits of game objects, which are then extended by diverse enemy types, projectiles, and power-ups. This allows the core `Handler` class to iterate through a unified list of objects, executing polymorphic `tick()` and `render()` methods without needing to know their specific implementations.
* **Encapsulation:** Game states, player statistics, and rendering mechanisms are tightly encapsulated within their respective classes. Direct access to fields is restricted, utilizing methods to modify game parameters like health, score, or weapon heat, preventing unintended side effects.
* **Main Game Loop:** The core engine is built around a custom game loop (managed in `Game.java`), which actively regulates the frame rate and logical tick rate, completely independent of heavy external game engines.

## Data Persistence

Player data and high scores are managed using a local **SQLite** database via standard JDBC (`SavingSystem.java`). This ensures structured, persistent data storage using safe SQL queries (`PreparedStatement`) without relying on cumbersome file I/O text parsing.

## Compilation and Execution

Since the code depends on standard external libraries (like `slick.jar`, `lwjgl.jar`, and `sqlite-jdbc.jar`), you will need to provide these dependencies to compile and run the project manually using basic `javac` and `java` commands.

Assuming you place the necessary JAR files inside a `lib/` folder:

1. **Compile the code:**
```bash
javac -cp "lib/*" -d bin src/**/*.java
```

2. **Run the application:**
```bash
java -cp "bin:lib/*" gameCore.Game
```
