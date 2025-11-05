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
                
