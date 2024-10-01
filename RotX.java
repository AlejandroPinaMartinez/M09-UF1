import java.util.Scanner;

public class RotX {

    public static final char[] abcP = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final char[] abcG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Insereixi el text :");
        String text = sc.nextLine(); 
    
        if (text.isEmpty()) {
            System.out.println("text no vàlid");
        } else {
            System.out.println("Vols xifrar [x], desxifrar [d] o fer força bruta [f]?");
            String xdf = sc.nextLine();

            if (xdf.equals("f") || xdf.equals("x") || xdf.equals("d")) {
                System.out.println("Insereixi el desplaçament :");
                int desplaçament = Integer.parseInt(sc.nextLine());

                if (desplaçament > 26 || desplaçament < 0) {
                    System.out.println("El desplaçament ha d'estar dins la longuitud del abecedari (>= a 0 i <= a 26)");
                } else {
                    String xifrada = xifraRotX(text, desplaçament);

                    if (xdf.equals("f")) {
                        System.out.println(xifrada + " <-- És el text xifrat");
                        String forçabruta = forcaBrutaRotX(xifrada);
                        System.out.println(forçabruta);
                    }

                    if (xdf.equals("x")) {
                        System.out.println("Text xifrat :" + xifrada);
                    } else if (xdf.equals("d")) {
                        String desxifrada = desxifraRotX(text, desplaçament);
                        System.out.println("Text desxifrat :" + desxifrada);
                    }
                }
            } else {
                System.out.println("Text no vàlid");
            }
        }
    }

    public static String xifraRotX(String text, int desplaçament) {
        String xifrat = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                for (int j = 0; j < 26; j++) {
                    if (abcG[j] == text.charAt(i)) {
                        if (j + desplaçament <= abcG.length-1) {
                            xifrat = xifrat + abcG[j + desplaçament];
                        } else {
                            xifrat = xifrat + abcG[(j + desplaçament) - abcG.length];
                        }
                    } else if (abcP[j] == text.charAt(i)) {
                        if (j + desplaçament <= abcP.length-1) {
                            xifrat = xifrat + abcP[j + desplaçament];
                        } else {
                            xifrat = xifrat + abcP[(j + desplaçament) - abcP.length];
                        }
                    }
                }
            } else {
                xifrat = xifrat + text.charAt(i);
            }
        }
        return xifrat;
    }

    public static String desxifraRotX(String text, int desplaçament) {
        String desxifrar = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                for (int j = 0; j < 26; j++) {
                    if (abcG[j] == text.charAt(i)) {
                        if (j - desplaçament >= 0) {
                            desxifrar = desxifrar + abcG[j - desplaçament];
                        } else {
                            desxifrar = desxifrar + abcG[(j - desplaçament) + abcG.length];
                        }
                    } else if (abcP[j] == text.charAt(i)) {
                        if (j - desplaçament >= 0) {
                            desxifrar = desxifrar + abcP[j - desplaçament];
                        } else {
                            desxifrar = desxifrar + abcP[(j - desplaçament) + abcP.length];
                        }
                    }
                }
            } else {
                desxifrar = desxifrar + text.charAt(i);
            }
        }
        return desxifrar;
    }

    public static String forcaBrutaRotX(String xifrada) {
        String forcaBruta="";
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < xifrada.length(); i++) {
                if (Character.isLetter(xifrada.charAt(i))) {
                    for (int j = 0; j < 26; j++) {
                        if (abcG[j] == xifrada.charAt(i)) {
                            if (j - k >= 0) {
                                forcaBruta = forcaBruta + abcG[j - k];
                            } else {
                                forcaBruta = forcaBruta + abcG[(j - k) + abcG.length];
                            }
                        } else if (abcP[j] == xifrada.charAt(i)) {
                            if (j - k >= 0) {
                                forcaBruta = forcaBruta + abcP[j - k];
                            } else {
                                forcaBruta = forcaBruta + abcP[(j - k) + abcP.length];
                            }
                        }
                    }
                } else {
                    forcaBruta = forcaBruta + xifrada.charAt(i);
                }
            }
            if (k==1){
            forcaBruta = forcaBruta + " <-- Text xifrat, desxifrat amb " + k + " desplaçament " + "\n";
            } else {
            forcaBruta = forcaBruta + " <-- Text xifrat, desxifrat amb " + k + " desplaçaments " + "\n";
            }
        }
        return forcaBruta;
    }
}

