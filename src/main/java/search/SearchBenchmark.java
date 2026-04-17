package search;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class SearchBenchmark {

    @Param({"10000", "100000", "1000000"})
    private int n;

    private int[] sortedArr;
    private String text;
    private String pattern;

    @Setup
    public void setup() {
        sortedArr = new int[n];
        for (int i = 0; i < n; i++) {
            sortedArr[i] = i * 2;
        }

        text = "a".repeat(n);
        // Patron que no existe: fuerza una exploracion completa en KMP.
        pattern = "a".repeat(20) + "b";
    }

    @Benchmark
    public int linearSearch() {
        for (int i = 0; i < sortedArr.length; i++) {
            if (sortedArr[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

    @Benchmark
    public int binarySearch() {
        return BinarySearch.lowerBound(sortedArr, n - 1);
    }

    @Benchmark
    public List<Integer> kmpSearch() {
        return KMP.search(text, pattern);
    }
}
