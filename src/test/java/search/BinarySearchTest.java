package search;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.LongPredicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    @Test
    void checkpointLowerUpperCount() {
        int[] arr = {1, 3, 3, 5, 7};
        assertEquals(1, BinarySearch.lowerBound(arr, 3));
        assertEquals(3, BinarySearch.upperBound(arr, 3));
        assertEquals(2, BinarySearch.countOccurrences(arr, 3));
    }

    @Test
    void checkpointBisectAnswerTaskAssignment() {
        long[] tasks = {3, 5, 2, 8, 4};
        int k = 3;

        LongPredicate canDistribute = maxLoad -> {
            int workers = 1;
            long current = 0;
            for (long task : tasks) {
                if (task > maxLoad) {
                    return false;
                }
                if (current + task > maxLoad) {
                    workers++;
                    current = task;
                } else {
                    current += task;
                }
            }
            return workers <= k;
        };

        long lo = Arrays.stream(tasks).max().orElseThrow();
        long hi = Arrays.stream(tasks).sum();
        long answer = BinarySearch.bisectAnswer(lo, hi, canDistribute);

        assertEquals(10, answer);
    }

    @Test
    void edgeCasesSingleElementAndMissing() {
        int[] arr = {4};
        assertEquals(0, BinarySearch.lowerBound(arr, 4));
        assertEquals(1, BinarySearch.upperBound(arr, 4));
        assertEquals(0, BinarySearch.countOccurrences(arr, 10));
    }
}
