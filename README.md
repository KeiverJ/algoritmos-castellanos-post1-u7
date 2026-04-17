# algoritmos-castellanos-post1-u7

Actividad Post-Contenido 1 - Unidad 7
Diseno de Algoritmos y Sistemas - UDES (2026)

## Objetivo

Implementar en Java 17+ algoritmos de busqueda y recuperacion de informacion:

- Variantes robustas de busqueda binaria (`lowerBound`, `upperBound`, `countOccurrences`, `bisectAnswer`).
- KMP (funcion de fallo + busqueda de ocurrencias).
- Suffix Array (construccion por comparacion + LCP de Kasai + consulta de patron).
- Benchmark JMH para comparar busqueda lineal, binaria y KMP.

## Estructura del proyecto

Ver [pom.xml](pom.xml).

- [src/main/java/search/BinarySearch.java](src/main/java/search/BinarySearch.java)
- [src/main/java/search/KMP.java](src/main/java/search/KMP.java)
- [src/main/java/search/SuffixArray.java](src/main/java/search/SuffixArray.java)
- [src/main/java/search/SearchBenchmark.java](src/main/java/search/SearchBenchmark.java)
- [src/test/java/search/BinarySearchTest.java](src/test/java/search/BinarySearchTest.java)
- [src/test/java/search/KMPTest.java](src/test/java/search/KMPTest.java)
- [src/test/java/search/SuffixArrayTest.java](src/test/java/search/SuffixArrayTest.java)

## Complejidades teoricas

### BinarySearch

- `lowerBound`: O(log n) tiempo, O(1) espacio.
- `upperBound`: O(log n) tiempo, O(1) espacio.
- `countOccurrences`: O(log n) tiempo, O(1) espacio.
- `bisectAnswer`: O(log(hi-lo+1)) llamadas al predicado, O(1) espacio.

### KMP

- `buildFailure`: O(m) tiempo, O(m) espacio.
- `search`: O(n + m) tiempo, O(m) espacio.

### SuffixArray

- Construccion por comparacion de sufijos: O(n log^2 n) (como exige la guia).
- `buildLCP` (Kasai): O(n) tiempo, O(n) espacio auxiliar.
- `contains`: O(m log n) tiempo (comparacion por prefijo), O(1) espacio extra.

## Checkpoints verificados por tests

Ejecutar:

```bash
mvn test
```

Cobertura de checkpoints y casos borde en:

- [src/test/java/search/BinarySearchTest.java](src/test/java/search/BinarySearchTest.java)
- [src/test/java/search/KMPTest.java](src/test/java/search/KMPTest.java)
- [src/test/java/search/SuffixArrayTest.java](src/test/java/search/SuffixArrayTest.java)

Notas de consistencia respecto al PDF:

- En el caso de asignacion de tareas `[3,5,2,8,4]` con `k=3`, la carga minima correcta es `10`.
- Para `KMP.search("AABAABAABAAB", "AABA")`, las ocurrencias correctas son `[0, 3, 6]`.

## Benchmark JMH

Comando ejecutado para obtener resultados empiricos reales en este entorno:

```bash
java -jar target/benchmarks.jar search.SearchBenchmark -wi 3 -i 5 -f 1 -r 1s -w 1s -rf csv -rff target/jmh-real.csv
```

### Resultados (Average Time, us/op)

| Algoritmo      | n=10000 | n=100000 | n=1000000 |
|----------------|--------:|---------:|----------:|
| linearSearch   | 1.630   | 15.037   | 142.921   |
| binarySearch   | 0.015   | 0.019    | 0.024     |
| kmpSearch      | 51.982  | 476.515  | 4530.748  |

### Analisis breve

- `binarySearch` escala logaritmicamente y se mantiene casi constante al crecer `n`.
- `linearSearch` crece aproximadamente lineal con `n`.
- `kmpSearch` tambien crece lineal en este experimento porque el texto aumenta y el patron no aparece.
- Esta corrida usa 1 fork real, 3 iteraciones de warmup y 5 de medicion; los resultados son mas estables que una corrida rapida de depuracion.

## Capturas

Agregar capturas del desarrollo y ejecucion en la carpeta:

- [capturas](capturas)

Sugeridas:

- Ejecucion de `mvn test`.
- Salida de benchmark JMH.
- Historial de commits descriptivos.