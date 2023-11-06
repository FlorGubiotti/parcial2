import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        ArrayList<String[]> conjuntoDna = new ArrayList<>();

        //Casos de prueba
        conjuntoDna.add(new String[]{"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"}) ; // DNA no mutante
        conjuntoDna.add(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"});  // DNA mutante
        conjuntoDna.add(new String[]{"ATGCAA", "CCGTGC", "TTATAT", "AGAACG", "CCCCCC", "TCACTG"}); //DNA con una fila llena del mismo caracter, no es mutante
        conjuntoDna.add(new String[]{"ATGCDH", "ERTUDW", "TXATAT", "IJTRDH", "MGTUF3", "WDFFG7"}); //DNA con caracteres no válidos
        conjuntoDna.add(new String[]{"ATGCAA", "CAGTGC", "TTATAT", "AGAACG", "GTCCCC"}); //DNA no cuadrado
        conjuntoDna.add(new String[]{"A"}); //DNA con un solo caracter
        conjuntoDna.add(new String[]{"ATGCAA", "CAGTGC", "TTATATTT", "AGAACG", "GTCCCC", "TCACTG"}); //DNA tamaño no uniforme

        for (int i = 0; i < conjuntoDna.size(); i++) {
            String[] dna = conjuntoDna.get(i);

            // Muestra la matriz de ADN
            System.out.println("\nMatriz de ADN en la prueba " + (i + 1) + ":");
            for (String row : dna) {
                StringBuilder formattedRow = new StringBuilder();
                for (char letra : row.toCharArray()) {
                    formattedRow.append(letra).append(' ');
                }
                System.out.println(formattedRow);
            }

            if (MutantDetector.isValidDNA(dna)) {
                try {
                    boolean esMutante = MutantDetector.isMutant(dna);
                    System.out.println("Resultado de la prueba " + (i + 1) + ": " + (esMutante ? "Es un mutante" : "No es un mutante"));
                } catch (Exception.InvalidDNAException e) {
                    System.out.println("Error en la prueba " + (i + 1) + ": " + e.getMessage());
                }
            } else

                System.out.println("Error en la prueba " + (i + 1) + ": La matriz de ADN no es cuadrada o contiene caracteres no válidos");
        }
    }

}