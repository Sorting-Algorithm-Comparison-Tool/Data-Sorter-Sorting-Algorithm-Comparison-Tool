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
        

        
