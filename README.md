# Lab 1: Java Implementations

This project contains two Java implementations:
1. **Task 1**: Trigonometric functions library (secant via power series)
2. **Task 2**: Bucket sort algorithm with logging

## Project Structure

```
lab1/
├── build.bat                # Windows build script
├── build.gradle             # Gradle build configuration
├── run.bat                  # Windows run script
├── settings.gradle          # Gradle settings
├── task1/                   # Trigonometric functions
│   ├── src/main/java/lab1/task1/
│   │   ├── App.java             # Command-line application
│   │   └── utils/Trigonometric.java   # Secant function implementation
│   └── src/test/java/lab1/task1/utils/TrigonometricTest.java
├── task2/                   # Bucket sort
│   ├── src/main/java/lab1/task2/BucketSort.java
│   └── src/test/java/lab1/task2/BucketSortTest.java
└── src/test/resources/table_values.csv   # Test data for task1
```

## Task 1: Trigonometric Functions Library

### Features

- Implements `sec(x)` function using power series
- Handles edge cases like x = 0 and x near points of discontinuity
- Provides both single-parameter and multi-parameter versions
- Includes convergence checking for accurate results
- Command-line interface for easy testing
- Comprehensive unit tests with JUnit 5

### Usage

```java
import lab1.task1.utils.Trigonometric;

double result = Trigonometric.sec(0.5);
```

### Command Line Usage

```
java lab1.task1.App <x>
```

Where:
- `<x>` is the input value for sec(x) (must be in valid domain)

Example:
```
java lab1.task1.App 0.5
```

## Task 2: Bucket Sort

### Overview

Implementation of bucket sort for double arrays with logging of algorithm steps. The algorithm sorts numbers based on their absolute values and logs characteristic points (A, B, C, D, E, F) for debugging and analysis.

### Features

- Sorts arrays of arbitrary double values
- Handles edge cases: empty array, null, all equal elements
- Logs each step of the algorithm (creation of buckets, distribution, merging)
- Uses binary search for insertion within buckets to maintain sorted order
- Returns a new sorted array without modifying the original

### Usage

```java
import lab1.task2.BucketSort;

BucketSort sorter = new BucketSort();
double[] array = {3.5, -2.1, 0.0, 7.8, -4.3};
double[] sorted = sorter.sort(array);
List<String> log = sorter.getLog(); // retrieve step log
```

### Algorithm Steps

The algorithm logs the following points:

- **A**: Start of sorting
- **B**: Creation of buckets (number of buckets equals array length)
- **C**: Distribution of each element into appropriate bucket (based on absolute value)
- **D**: Merging buckets into result array
- **E**: Early termination or end of sorting (empty or null input)

### Example Output

For input `{3.5, -2.1, 0.0}`, the log may contain:

```
A
B: создано 3 корзин
C: элемент 3.50 -> корзина 2
C: элемент -2.10 -> корзина 1
C: элемент 0.00 -> корзина 0
D: добавлены элементы из корзины 0: [0.00]
D: добавлены элементы из корзины 1: [-2.10]
D: добавлены элементы из корзины 2: [3.50]
E
```

## Building

To build the project, run the following command:

```
gradle build
```

This will compile both tasks and run unit tests.

## Testing

Unit tests are written with JUnit 5 and can be executed via:

```
gradle test
```

Test coverage reports are generated using JaCoCo and can be found in `build/reports/jacoco/test/html/`.