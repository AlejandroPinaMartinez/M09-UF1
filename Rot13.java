import java.util.Scanner;

public class Rot13 {
    
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserte la contraseña:");
        String contraseña = sc.nextLine();
        String abcP = "abcdefghijklmnopqrstuvwxyz";
        String abcG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String xifrada = xifraRot13(contraseña, abcP, abcG);
        System.out.println(xifrada);
    }

    public static String xifraRot13 (String contraseña, String abcP, String abcG) {
    String xifrat= "";
        for (int i=0; i < contraseña.length(); i++) {
            for (int j=0; j < abcG.length(); j++) {
                    if (abcG.charAt(j) == contraseña.charAt(i) ) {
                        xifrat=xifrat + abcG.charAt(j+13);
                    }
                    else if (abcP.charAt(j) == contraseña.charAt(i) ) {
                        xifrat=xifrat + abcP.charAt(j+13);
                    }
            }
        }
        return xifrat;
    }

    //public String desxifraRot13{
        
    //}
}
