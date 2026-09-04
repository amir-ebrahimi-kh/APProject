# Java Code Exercises

This repository contains standalone Java code exercises, demonstrating straightforward OOP mechanics without heavy enterprise architecture. The project includes a 2D game component built natively in Java.

## Compilation and Execution

Since the code depends on external libraries (like `slick.jar`, `lwjgl.jar`, and `gson.jar` which were previously used by the IDE configuration), you will need to provide these dependencies in order to compile and run the project manually using basic `javac` and `java` commands.

Assuming you place the necessary JAR files inside a `lib/` folder:

1. **Compile the code:**
```bash
javac -cp "lib/*" -d bin src/**/*.java
```

2. **Run the application:**
```bash
java -cp "bin:lib/*" gameCore.Game
```
