package search;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KMPTest {

    @Test
    void checkpointFailureFunction() {
        int[] expected = {0, 0, 1, 2, 0, 1, 2, 3, 4};
        assertArrayEquals(expected, KMP.buildFailure("ABABCABAB"));
    }

    @Test
    void checkpointSearchOverlaps() {
        List<Integer> expected = List.of(0, 3, 6);
        assertEquals(expected, KMP.search("AABAABAABAAB", "AABA"));
    }

    @Test
    void edgeCasesEmptyPatternAndEqualsText() {
        assertEquals(List.of(), KMP.search("ABC", ""));
        assertEquals(List.of(0), KMP.search("ABC", "ABC"));
    }
}
