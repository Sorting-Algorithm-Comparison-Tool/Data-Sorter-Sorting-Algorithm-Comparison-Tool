import java.util.*;

public class SortingComparisonTool {

     // BUBBLE SORT 
        public static int bubbleSort(int[] arr) {
        int n  = arr.length; // Get how many numbers we have
        int steps = 0; // Count how many comparisons we make
        
 // Outer loop: we need to do n passes
        for (int i = 0; i < n; i++) {
            // Inner loop: compare adjacent numbers
            for (int j = 0; j < n - i - 1; j++) {
                steps++; // Count this comparison
                
// If left number is bigger than right number, swap them
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];        // Save left number
                    arr[j] = arr[j + 1];      // Put right number on left
                    arr[j + 1] = temp;        // Put saved number on right
                }
            }
        }
        return steps; // Return total comparisons made
    }


     // MERGE SORT - Divide and conquer approach
    // Like sorting two piles of cards separately, then merging them
    public static int mergeSort(int[] arr) {
        int steps = 0; // Count comparisons
        

        // Base case: array with 0 or 1 element is already sorted
        if (arr.length <= 1) {
            return steps;
        }
// Step 1: Split array into two halves
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);      // First half
        int[] right = Arrays.copyOfRange(arr, mid, arr.length); // Second half

// Step 2: Recursively sort both halves
        steps += mergeSort(left);
        steps += mergeSort(right);

        // Step 3: Merge the sorted halves back together
        steps += merge(arr, left, right);
        
        return steps;
    }

     // Helper method to merge two sorted arrays
    private static int merge(int[] arr, int[] left, int[] right) {
        int i = 0; // Index for left array
        int j = 0; // Index for right array
        int k = 0; // Index for main array
        int steps = 0; // Count comparisons
        // Compare elements from left and right, put smaller one in main array
        while (i < left.length && j < right.length) {
            steps++;
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        

        // Copy any remaining elements from left array
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }

        // Copy any remaining elements from right array
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
        
        return steps;
    }

// QUICK SORT  
    public static int quickSort(int[] arr) {
        return quickSortHelper(arr, 0, arr.length - 1);
    }

    private static int quickSortHelper(int[] arr, int low, int high) {
        int steps = 0;
        
// Only sort if there are at least 2 elements
        if (low < high) {
            // Partition and get pivot position
            int pivotIndex = partition(arr, low, high);
            
            // Recursively sort left side of pivot
            steps += quickSortHelper(arr, low, pivotIndex - 1);
            
            // Recursively sort right side of pivot
            steps += quickSortHelper(arr, pivotIndex + 1, high);
        }
        return steps;
    }
// Partition method: puts smaller elements on left, larger on right
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choose last element as pivot
        int i = low - 1; // Position for smaller elements
        int steps = 0;
        
        // Go through all elements except pivot
        for (int j = low; j < high; j++) {
            steps++;
            // If current element is smaller than pivot
            if (arr[j] < pivot) {
                i++;
                // Swap to put smaller element on left side
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        // Put pivot in its correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1; // Return pivot's final position
    }

   // DATA GENERATION, PERFORMANCE COMPARISON, AND DISPLAY MODULES

    // Generate random numbers for testing
    public static int[] generateRandomData(int size) {
        Random rand = new Random();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = rand.nextInt(100); // Random number between 0-99
        }
        return data;
    }
   // Main program with menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] data = null; // Will store our numbers to sort

        // Keep showing menu until user chooses to exit
        while (true) {
            // Display menu
            System.out.println("\n--- Data Sorter: Sorting Algorithm Comparison Tool ---");
            System.out.println("1. Enter numbers manually");
            System.out.println("2. Generate random numbers");
            System.out.println("3. Perform Bubble Sort");
            System.out.println("4. Perform Merge Sort");
            System.out.println("5. Perform Quick Sort");
            System.out.println("6. Compare all algorithms (show performance table)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            // Input validation for menu choice
            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 1-7.");
                sc.nextLine(); // Clear invalid input
                continue;
            }

           // Option 1: User enters numbers
            if (choice == 1) {
                System.out.print("Enter the number of elements: ");
                int n = sc.nextInt();
                data = new int[n];
                System.out.println("Enter the numbers:");
                for (int i = 0; i < n; i++) {
                    data[i] = sc.nextInt();
                }
                            } 
            // Option 2: Generate random numbers
            else if (choice == 2) {
                System.out.print("Enter the number of random elements: ");
                int size = sc.nextInt();
                data = generateRandomData(size);
                System.out.println("Generated data: " + Arrays.toString(data));
            } 
            // Option 3: Run Bubble Sort
            else if (choice == 3) {
                int[] bubbleData = Arrays.copyOf(data, data.length); // Make a copy
                long startTime = System.nanoTime(); // Record start time
                int bubbleSteps = bubbleSort(bubbleData);
                long endTime = System.nanoTime(); // Record end time
                
                System.out.println("Bubble Sort Result: " + Arrays.toString(bubbleData));
                System.out.println("Bubble Sort Execution Time: " + (endTime - startTime) / 1000000.0 + " ms");
                System.out.println("Bubble Sort Steps: " + bubbleSteps);
            } 
            // Option 4: Run Merge Sort
            else if (choice == 4) {
                int[] mergeData = Arrays.copyOf(data, data.length);
                long startTime = System.nanoTime();
                int mergeSteps = mergeSort(mergeData);
                long endTime = System.nanoTime();
                
                System.out.println("Merge Sort Result: " + Arrays.toString(mergeData));
                System.out.println("Merge Sort Execution Time: " + (endTime - startTime) / 1000000.0 + " ms");
                System.out.println("Merge Sort Steps: " + mergeSteps);
            } 
            // Option 5: Run Quick Sort
            else if (choice == 5) {
                int[] quickData = Arrays.copyOf(data, data.length);
                long startTime = System.nanoTime();
                int quickSteps = quickSort(quickData);
                long endTime = System.nanoTime();
                
                System.out.println("Quick Sort Result: " + Arrays.toString(quickData));
                System.out.println("Quick Sort Execution Time: " + (endTime - startTime) / 1000000.0 + " ms");
                System.out.println("Quick Sort Steps: " + quickSteps);
            } 
            // Option 6: Compare all three algorithms
            else if (choice == 6) {
                // Make separate copies for each algorithm
                int[] bubbleData = Arrays.copyOf(data, data.length);
                int[] mergeData = Arrays.copyOf(data, data.length);
                int[] quickData = Arrays.copyOf(data, data.length);
                                // Test Bubble Sort
                long startTime = System.nanoTime();
                int bubbleSteps = bubbleSort(bubbleData);
                long bubbleTime = System.nanoTime() - startTime;

                // Test Merge Sort
                startTime = System.nanoTime();
                int mergeSteps = mergeSort(mergeData);
                long mergeTime = System.nanoTime() - startTime;

                // Test Quick Sort
                startTime = System.nanoTime();
                int quickSteps = quickSort(quickData);
                long quickTime = System.nanoTime() - startTime;

                // Display comparison results
                System.out.println("\n--- Algorithm Performance Comparison ---");
                System.out.printf("Bubble Sort Time: %.6f ms, Steps: %d\n", bubbleTime / 1000000.0, bubbleSteps);
                System.out.printf("Merge Sort Time: %.6f ms, Steps: %d\n", mergeTime / 1000000.0, mergeSteps);
                System.out.printf("Quick Sort Time: %.6f ms, Steps: %d\n", quickTime / 1000000.0, quickSteps);
            } 

            // Option 7: Exit program
            else if (choice == 7) {
                System.out.println("Exiting program.");
                break;
            } 
            // Invalid choice
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close(); // Close scanner
    }
}