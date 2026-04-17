package search;

import java.util.ArrayList;
import java.util.List;

/**
 * Knuth-Morris-Pratt para emparejamiento de patrones en O(n + m).
 * failure[j] = longitud del prefijo propio mas largo de pattern[0..j]
 * que tambien es sufijo.
 */
public class KMP {

    /** Construye la funcion de fallo en O(m). */
    public static int[] buildFailure(String pattern) {
        int m = pattern.length();
        int[] f = new int[m];
        if (m == 0) {
            return f;
        }

        int k = 0;
        for (int i = 1; i < m; i++) {
            while (k > 0 && pattern.charAt(k) != pattern.charAt(i)) {
                k = f[k - 1];
            }
            if (pattern.charAt(k) == pattern.charAt(i)) {
                k++;
            }
            f[i] = k;
        }
        return f;
    }

    /**
     * Retorna todas las posiciones (0-indexed) donde pattern ocurre en text.
     * Complejidad: O(n + m) tiempo, O(m) espacio.
     */
    public static List<Integer> search(String text, String pattern) {
        List<Integer> results = new ArrayList<>();
        if (pattern.isEmpty()) {
            return results;
        }

        int[] f = buildFailure(pattern);
        int n = text.length();
        int m = pattern.length();
        int q = 0; // caracteres del patron ya emparejados

        for (int i = 0; i < n; i++) {
            while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
                q = f[q - 1];
            }
            if (pattern.charAt(q) == text.charAt(i)) {
                q++;
            }
            if (q == m) {
                results.add(i - m + 1);
                q = f[q - 1]; // buscar siguiente ocurrencia
            }
        }
        return results;
    }
}
