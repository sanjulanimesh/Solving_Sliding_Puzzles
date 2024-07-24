// UOW ID: w1953533
// IIT NO: 20220730
// Name: Sanjula Nimesh Hettiarachchi


package org.example;
import java.util.Scanner;

// Define the Main class
public class Main {
    // Main method, the entry point of the program
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);
        // Print a blank line followed by the available files
        System.out.println("\nAvailable files in the project directory");
        // List each file on a new line
        System.out.println("\nbenchmark_series/maze10_1.txt");
        System.out.println("benchmark_series/puzzle_10.txt");
        System.out.println("benchmark_series/puzzle_20.txt");
        System.out.println("benchmark_series/puzzle_40.txt");
        System.out.println("benchmark_series/puzzle_80.txt");
        System.out.println("benchmark_series/puzzle_160.txt");
        System.out.println("benchmark_series/puzzle_320.txt");
        System.out.println("benchmark_series/puzzle_640.txt");
        System.out.println("benchmark_series/puzzle_1280.txt");
        System.out.println("benchmark_series/puzzle_2560.txt");

        // Prompt the user to pick a file from the list
        System.out.print("\nPick One of above list(copy and paste): ");
        // Read the filename from user input
        String fileName = scanner.next();

        // Create an instance of PathFinder with the user's chosen file
        PathFinder pathFinder = new PathFinder(fileName);
    }
}
