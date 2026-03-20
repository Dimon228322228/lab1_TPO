# Trigonometric Functions Library

This project contains a Java implementation of the secant function using power series expansion.

## Project Structure

```
lab1/
├── build.bat                # Windows build script
├── build.gradle             # Gradle build configuration
├── run.bat                  # Windows run script
├── settings.gradle          # Gradle settings
├── src/
│   ├── main/
│   │   └── java/
│   │       └── lab1/
│   │           └── task1/
│   │               ├── App.java             # Command-line application
│   │               └── utils/
│   │                   └── Trigonometric.java   # Secant function implementation
│   └── test/
│       └── java/
│           └── lab1/
│               └── task1/
│                   └── utils/
│                       └── TrigonometricTest.java # Test suite
└── src/test/resources/
    └── table_values.csv   # Test data
```

## Features

- Implements `sec(x)` function using power series
- Handles edge cases like x = 0 and x near points of discontinuity
- Provides both single-parameter and multi-parameter versions
- Includes convergence checking for accurate results
- Command-line interface for easy testing
- Comprehensive unit tests with JUnit 5

## Usage

```java
import lab1.task1.utils.Trigonometric;

double result = Trigonometric.sec(0.5);
double resultWithPrecision = Trigonometric.sec(0.5, 100);
```

## Command Line Usage

```
java lab1.task1.App <x> [<n>]
```

Where:
- `<x>` is the input value for sec(x) (must be in valid domain)
- `[<n>]` is optional number of terms for precision (default: all terms)

Example:
```
java lab1.task1.App 0.5
java lab1.task1.App 0.5 100
```

## Building

This project uses batch files for building and dependency management on Windows.

To build the project:
```
build.bat
```

To run tests:
```
run.bat
```

To run the application:
```
run.bat
```

Note: Batch files are used for building and running on Windows systems.