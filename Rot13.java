import java.util.Scanner;

public class Rot13 {
    public static final String abcP = "abcdefghijklmnopqrstuvwxyz";
    public static final String abcG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserte la contraseña:");
        String contraseña = sc.nextLine();
        if (contraseña.isEmpty()){System.out.println("Contraseña no vàlida");} 
        else {
        String xifrada = xifraRot13(contraseña);
        System.out.println("Contraseña xifrada :" + xifrada);
        String desxifrada = desxifraRot13(xifrada);
        System.out.println("Contraseña desxifrada :" + desxifrada);
        }
    }

    public static String xifraRot13 (String contraseña) {
    String xifrat= "";
        for (int i=0; i < contraseña.length(); i++) {
            if (Character.isLetter(contraseña.charAt(i))) {
                for (int j=0; j < 26; j++) {
                        if (abcG.charAt(j) == contraseña.charAt(i) ) {
                            if (j+13<=26){
                                xifrat=xifrat + abcG.charAt(j+13);
                            } else {
                                xifrat=xifrat + (abcG.charAt((j+13)-26));
                            }
                        }
                        else if (abcP.charAt(j) == contraseña.charAt(i) ) {
                            if (j+13<=26){
                                xifrat=xifrat + abcP.charAt(j+13);
                            } else {
                                xifrat=xifrat + (abcP.charAt((j+13)-26));
                            }
                        } 
                }
            } else {
                xifrat = xifrat + contraseña.charAt(i);
            }
        }
        return xifrat;
    }

    public static String desxifraRot13 (String xifrada){
        String desxifrar= "";
        for (int i=0; i < xifrada.length(); i++) {
            if (Character.isLetter(xifrada.charAt(i))) {
                for (int j=0; j < 26; j++) {
                        if (abcG.charAt(j) == xifrada.charAt(i) ) {
                            if (j-13>=0){
                                desxifrar=desxifrar + abcG.charAt(j-13);
                            } else {
                                desxifrar=desxifrar + (abcG.charAt((j-13)+26));
                            }
                        }
                        else if (abcP.charAt(j) == xifrada.charAt(i) ) {
                            if (j-13>=0){
                                desxifrar=desxifrar + abcP.charAt(j-13);
                            } else {
                                desxifrar=desxifrar + (abcP.charAt((j-13)+26));
                            }
                        } 
                }
            } else {
                desxifrar = desxifrar + xifrada.charAt(i);
            }
        }
        return desxifrar;
        
    }
}
