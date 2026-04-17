package search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SuffixArrayTest {

    @Test
    void checkpointBananaSaAndLcp() {
        SuffixArray sa = new SuffixArray("banana");
        assertArrayEquals(new int[]{5, 3, 1, 0, 4, 2}, sa.sa);
        assertArrayEquals(new int[]{0, 1, 3, 0, 0, 2}, sa.lcp);
    }

    @Test
    void checkpointContainsQueries() {
        SuffixArray sa = new SuffixArray("banana");
        assertTrue(sa.contains("ana"));
        assertFalse(sa.contains("xyz"));
    }

    @Test
    void edgeCaseEmptyPattern() {
        SuffixArray sa = new SuffixArray("banana");
        assertTrue(sa.contains(""));
    }
}
