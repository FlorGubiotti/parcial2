import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MutantDetector {

    // Valido los arreglos
    public static boolean isValidDNA(String[] dna) {
        if (dna == null || dna.length != 6) {
            return false;
        }

        int expectedLength = 6;

        Pattern invalidCharacterPattern = Pattern.compile("[^ATCG]");

        for (String s : dna) {
            if (s.length() != expectedLength) {
                return false;
            }

            Matcher invalidCharacterMatcher = invalidCharacterPattern.matcher(s);
            if (invalidCharacterMatcher.find()) {
                return false;
            }
        }
        return true;
    }
    public static boolean isMutant(String[] dna) throws Exception.InvalidDNAException {
        int n = dna.length;
        char[][] dnaMatrix = new char[n][];

        //Convierto arreglos en matriz
        for (int i = 0; i < n; i++) {
            dnaMatrix[i] = dna[i].toCharArray();
        }

        int sequenceCount = 0;

        // Verificar secuencias horizontales
        String dnaString = String.join("", dna);
        Matcher horizontalMatcher = Pattern.compile("(A{4,}|T{4,}|C{4,}|G{4,})").matcher(dnaString);
        while (horizontalMatcher.find()) {
            sequenceCount++;
        }

        // Verificar secuencias verticales
        for (int i = 0; i < n; i++) {
            StringBuilder column = new StringBuilder();
            for (int j = 0; j < n; j++) {
                column.append(dnaMatrix[j][i]);
            }
            Matcher verticalMatcher = Pattern.compile("(A{4,}|T{4,}|C{4,}|G{4,})").matcher(column.toString());
            while (verticalMatcher.find()) {
                sequenceCount++;
            }
        }

        // Verificar secuencias diagonales
        for (int i = 0; i < n; i++) {
            StringBuilder diagonalDownRight = new StringBuilder();
            StringBuilder diagonalDownLeft = new StringBuilder();
            for (int j = 0; j < n - i; j++) {
                diagonalDownRight.append(dnaMatrix[i + j][j]);
                diagonalDownLeft.append(dnaMatrix[i + j][n - j - 1]);
            }
            Matcher diagonalMatcher = Pattern.compile("(A{4,}|T{4,}|C{4,}|G{4,})").matcher(diagonalDownRight.toString());
            while (diagonalMatcher.find()) {
                sequenceCount++;
            }
            diagonalMatcher = Pattern.compile("(A{4,}|T{4,}|C{4,}|G{4,})").matcher(diagonalDownLeft.toString());
            while (diagonalMatcher.find()) {
                sequenceCount++;
            }
        }

        return sequenceCount > 1;
    }
}

