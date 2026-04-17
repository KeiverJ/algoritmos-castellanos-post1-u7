package search;

import java.util.Arrays;

/**
 * Suffix Array con construccion por ordenamiento (O(n log^2 n)) y
 * array LCP mediante algoritmo de Kasai (O(n)).
 * Permite busqueda de patron en O(m log n).
 */
public class SuffixArray {
    private final String s;
    private final int n;
    public final int[] sa; // sa[i] = inicio del i-esimo sufijo menor
    public final int[] lcp; // lcp[i] = LCP entre sufijos sa[i-1] y sa[i]

    public SuffixArray(String s) {
        this.s = s;
        this.n = s.length();
        this.sa = buildSA();
        this.lcp = buildLCP();
    }

    private int[] buildSA() {
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        Arrays.sort(idx, (a, b) -> s.substring(a).compareTo(s.substring(b)));

        int[] out = new int[n];
        for (int i = 0; i < n; i++) {
            out[i] = idx[i];
        }
        return out;
    }

    /** Algoritmo de Kasai: construye LCP en O(n). */
    private int[] buildLCP() {
        int[] rank = new int[n];
        int[] out = new int[n];

        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] > 0) {
                int j = sa[rank[i] - 1];
                while (i + h < n && j + h < n && s.charAt(i + h) == s.charAt(j + h)) {
                    h++;
                }
                out[rank[i]] = h;
                if (h > 0) {
                    h--;
                }
            }
        }
        return out;
    }

    /**
     * Verifica si pattern ocurre en el texto usando busqueda binaria.
     * Complejidad: O(m log n).
     */
    public boolean contains(String pattern) {
        if (pattern.isEmpty()) {
            return true;
        }
        int lo = 0;
        int hi = n - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            String suffix = s.substring(sa[mid]);
            String prefix = suffix.length() >= pattern.length()
                    ? suffix.substring(0, pattern.length())
                    : suffix;
            int c = prefix.compareTo(pattern);

            if (c == 0) {
                return true;
            }
            if (c < 0) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return false;
    }
}
