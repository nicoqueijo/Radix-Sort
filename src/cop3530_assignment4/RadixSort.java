package cop3530_assignment4;

import java.util.ArrayList;
import java.util.List;

/**
 * *******************************************************************
 * Purpose/Description: See javadocs. 
 * Author’s Panther ID: 5152398
 * Certification: I hereby certify that this work is my own and none of it is
 * the work of any other person.
 * ******************************************************************
 */

/**
 * Provides a radix sort algorithm and a main method to test it.
 *
 * @author nicoqueijo
 */
public class RadixSort {

    /**
     * Creates an array with some integer elements and calls the radixSort
     * method to sort them.
     *
     * @param args The command-line arguments. (Unused in this application)
     */
    public static void main(String[] args) {

        // contains only even digits in each key
        int[] arr1 = {88, 82, 2086, 60, 48, 42, 28, 268, 24, 22, 860, 6, 4, 2, 0, 286, 60, 44};
        System.out.println("Unsorted list:");

        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        System.out.println();
        System.out.println("Calling radix sort...");
        System.out.println();

        radixSort(arr1);

        System.out.println();
        System.out.println("----------------------------------------------------------");
        System.out.println();

        // some keys contain odd digits
        int[] arr2 = {620, 40, 88, 60, 1024, 860, 44, 72, 6, 200, 40, 20, 100, 22, 62, 240, 68};
        System.out.println("Unsorted list:");

        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();

        System.out.println();
        System.out.println("Calling radix sort...");
        System.out.println();

        radixSort(arr2);

    }

    /**
     * Finds the number of digits in a given integer.
     *
     * @param maxKey The key with the max value.
     * @return The number of digits in the max key.
     */
    public static int findDigitsOfKey(int maxKey) {

        int digitsOfKey;
        digitsOfKey = (int) (Math.log10(maxKey) + 1);
        return digitsOfKey;

    }

    /**
     * Finds the maximum key in an array of integers.
     *
     * @param arr The array to be searched.
     * @return The maximum key in the array.
     */
    public static int findMaxKey(int[] arr) {

        int maxKey = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxKey) {
                maxKey = arr[i];
            }
        }
        return maxKey;
    }

    /**
     * Sorts an array of integers in the following manner.
     *
     * First we find the maximum value in the array and determine how many
     * digits it contains. We will use this number as the amount of passes
     * (sorting iterations). In each pass we examine the current digit and place
     * that digit in its appropriate bucket. We will do this from least
     * significant digit to most significant digit, one digit per pass. After
     * each pass we take all the elements in each bucket in order and put it
     * back into the array to sort it again. At the end of the algorithm we will
     * have accomplished the sorting of the list without doing any comparisons!
     *
     * Note: specifications required program to detect the case of odd digits in
     * the keys, and, in this case, abort. For this reason the radix is 5.
     *
     * @param arr The array to be sorted.
     */
    public static void radixSort(int arr[]) {

        final int OFFSET = 2;
        final int BASE_OF_INTEGER = 10;
        final int RADIX = 5;
        final int MAX_KEY = findMaxKey(arr);
        final int DIGITS_OF_MAX_KEY = findDigitsOfKey(MAX_KEY);
        final int NUMBER_OF_PASSES = DIGITS_OF_MAX_KEY;

        List<Integer>[] buckets = new ArrayList[RADIX];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        int divisor;
        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            divisor = (int) Math.pow(BASE_OF_INTEGER, i);
            int currentDigit;

            for (int j = 0; j < arr.length; j++) {
                currentDigit = arr[j] / divisor;
                currentDigit = currentDigit % BASE_OF_INTEGER;
                // Aborts if detects odd digit.
                if (currentDigit % 2 != 0) {
                    System.out.println("ERROR! Program aborted. List must only contain even digits.");
                    return;
                }
                buckets[currentDigit / OFFSET].add(arr[j]);
            }

            int currentArrayIndex = 0;
            for (int j = 0; j < RADIX; j++) {
                for (int k = 0; k < buckets[j].size(); k++) {
                    arr[currentArrayIndex] = buckets[j].get(k);
                    currentArrayIndex++;
                }
                buckets[j].clear();
            }

        }

        System.out.println("Sorted list:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

    }

    /**
     * Running time complexity of radixSort: O(max digit * (radix + size of array))
     *
     * This is due to the outer loop of the algorithm running as many times as
     * the digit size of the maximum key (this is the amount of passes). Then
     * there are two separate inner loops. One traverses the length of the input
     * array and the other runs from 0 to the size of the radix - 1. Since this
     * algorithm deals with integer keys and does not allow odd digits the radix
     * will always be a constant of 5. If we take this into account we can say
     * that the time complexity will be O(max digit * size of array).
     */
}
