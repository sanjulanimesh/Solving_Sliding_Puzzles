// UOW ID: w1953533
// IIT NO: 20220730
// Name: Sanjula Nimesh Hettiarachchi

package org.example;
import java.util.*;
import java.io.File;
import java.io.IOException;
// Packages are used to organize classes into namespaces.

public class PathFinder {
    // Declaring private instance variables to store information about the map
    private int startRow;
    private int startCol;
    private int finishRow;
    private int finishCol;
    private int RowCount;
    private int ColumnCount;
    private char[][] mapGrid;
    // Directions array represents the possible movements: up, down, left, right
    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // Constructor for the PathFinder class
    public PathFinder(String fileName) {

        try {
            // Check if the file name is not empty
            if (!fileName.equals("")) {
                // Load the map from the file
                loadFromFile(fileName);
                // Add start and finish positions to the map
                addStartAndFinishPositions();
                // Print the loaded map
                PrintData();
                List<int[]> shortestPath = findShortestPath();
                // If a path is found, print it; otherwise, print a message indicating no path is found
                if (shortestPath != null && !shortestPath.isEmpty()) {
                    PrintShortPath(shortestPath);
                    System.out.println();
                } else {
                    System.out.println("\nNo path found...");
                }
            }
        } catch (IOException e) {
            System.out.println("\nThe file doesn't exist. Please check the file name and \n " +
                    "try again.............");
        }
    }

    // Method to find the shortest path from start to finish using BFS algorithm
    public List<int[]> findShortestPath() {
        boolean[][] visited = new boolean[RowCount][ColumnCount];
        int[][] parent = new int[RowCount][ColumnCount];
        Queue<int[]> queue = new LinkedList<>();
        // Add the start position to the queue and mark it as visited
        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;
        // Perform BFS traversal
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            // If the finish point is reached, construct and return the path
            if (row == finishRow && col == finishCol) {
                return constructPath(parent);
            }
            for (int[] direction : directions) {
                int newRow = row;
                int newCol = col;
                // Move in the current direction until a wall ('0') is encountered
                while (true) {
                    newRow += direction[0];
                    newCol += direction[1];
                    if (!ValidationCell(newRow, newCol) || mapGrid[newRow][newCol] == '0') {
                        break;
                    }
                    if (mapGrid[newRow][newCol] == 'F') {
                        parent[newRow][newCol] = row * ColumnCount + col;
                        return constructPath(parent);
                    }
                }
                newRow -= direction[0];
                newCol -= direction[1];
                if (!visited[newRow][newCol]) {
                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                    parent[newRow][newCol] = row * ColumnCount + col;
                }
            }
        }
        return new ArrayList<>();
    }
    public void loadFromFile(String fileName) throws IOException {
        // List to store each line of the map
        List<String> linesOfMap = new ArrayList<>();
        // Create a File object using the provided file name
        File file = new File(fileName);
        // Create a Scanner object to read from the file
        Scanner scanner = new Scanner(file);
        // Read each line of the file and add it to the list
        while (scanner.hasNext()) {
            linesOfMap.add(scanner.nextLine());
        }
        // Set the number of rows and columns based on the loaded map
        this.RowCount = linesOfMap.size();
        this.ColumnCount = linesOfMap.get(0).length();
        // Initialize the mapGrid array with the correct dimensions
        this.mapGrid = new char[RowCount][ColumnCount];
        // Populate the mapGrid array with characters from the loaded map
        for (int i = 0; i < RowCount; i++) {
            for (int j = 0; j < ColumnCount; j++) {
                mapGrid[i][j] = linesOfMap.get(i).charAt(j);
            }
        }
    }

    // Method to find and store the positions of the start and finish points on the map
    private void addStartAndFinishPositions() {
        for (int i = 0; i < RowCount; i++) {
            for (int j = 0; j < ColumnCount; j++) {
                if (mapGrid[i][j] == 'S') {
                    this.startRow = i;
                    this.startCol = j;
                } else if (mapGrid[i][j] == 'F') {
                    this.finishRow = i;
                    this.finishCol = j;
                }
            }
        }
        System.out.println("\nSuccessfully loaded the file into the program\n");
    }

    // Method to construct the path from start to finish based on the parent array
    private List<int[]> constructPath(int[][] parent) {
        List<int[]> path = new ArrayList<>();
        int currentRow = finishRow;
        int currentCol = finishCol;
        while (currentRow != startRow || currentCol != startCol) {
            path.add(0, new int[]{currentRow, currentCol});
            int index = parent[currentRow][currentCol];
            currentRow = index / ColumnCount;
            currentCol = index % ColumnCount;
        }
        path.add(0, new int[]{startRow, startCol});
        return path;
    }

    // Method to print the loaded map
    public void PrintData() {
        for (char[] row : mapGrid) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
    public void PrintShortPath(List<int[]> path) {
        System.out.println("\nShortest Path given below:\n");
        System.out.println("1. Starting at (" + (startCol + 1) + "," + (startRow + 1) + ")");
        int j = 0;
        for (int i = 1; i < path.size(); i++) {
            int[] currentCell = path.get(i);
            int[] previousCell = path.get(i - 1);
            int changeOfRow = currentCell[0] - previousCell[0];
            int changeOfColumn = currentCell[1] - previousCell[1];
            String change = "";
            if (changeOfColumn == 0 && changeOfRow < 0) {
                change = "Move up to ";
            } else if (changeOfRow > 0 && changeOfColumn == 0) {
                change = "Move down to ";
            } else if (changeOfRow == 0 && changeOfColumn > 0) {
                change = "Move right to ";
            } else if (changeOfRow == 0 && changeOfColumn < 0) {
                change = "Move left to ";
            }
            System.out.println((i + 1) + ". " + change + "(" + (currentCell[1] + 1) + "," + (currentCell[0] + 1) + ")");
            j = i;
        }
        System.out.println((j + 2) + ". Done!");
        System.out.println("\nPath found "+(j+2)+" Steps.");
    }

    private boolean ValidationCell(int row, int col) {
        return row >= 0 && row < RowCount && col >= 0 && col < ColumnCount;
    }
}
