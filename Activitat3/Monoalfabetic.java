import java.util.*;
import java.util.random.RandomGenerator;

public class Monoalfabetic {

    public static final char[] abc = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static char[] abcpermutat;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insereix una cadena de text: ");
        String cadena = sc.nextLine();
        System.out.println("Abecedari inicial:  " + Arrays.toString(abc));
        char[] Abcpermutat = permutaAlfabet(abc);
        System.out.println("Abecedari permutat: " + Arrays.toString(Abcpermutat));
        String cadenaxifrada = xifraMonoAlfa(cadena);
        System.out.println("Cadena inicial:     " + cadena);
        System.out.println("Cadena xifrada:     " + cadenaxifrada);
        String cadenadesxifrada = desxifraMonoAlfa(cadenaxifrada);
        System.out.println("Cadena desxifrada:  " + cadenadesxifrada);
    }

    public static String xifraMonoAlfa (String cadena){
        String xifrada="";
        for (int j= 0; j<cadena.length(); j++){
            if (Character.isLetter(cadena.charAt(j))) {
                if (Character.isUpperCase(cadena.charAt(j))) {
                    for (int k = 0; k<abc.length; k++) {
                        if(Character.toLowerCase(cadena.charAt(j))==abc[k]){
                            xifrada = xifrada + Character.toUpperCase(abcpermutat[k]);
                        }
                    } 
                } else {
                    for (int k = 0; k<abc.length; k++) {
                        if(cadena.charAt(j)==abc[k]){
                            xifrada = xifrada + (abcpermutat[k]);
                        }
                    } 
                }
            } else {
                    xifrada=xifrada + cadena.charAt(j);
            }
        }
        return xifrada;
    }

    public static String desxifraMonoAlfa (String xifrada){
        String desxifrada="";
        for (int j= 0; j<xifrada.length(); j++){
            if (Character.isLetter(xifrada.charAt(j))) {
                if (Character.isUpperCase(xifrada.charAt(j))) {
                    for (int k = 0; k<abc.length; k++) {
                        if(Character.toLowerCase(xifrada.charAt(j))==abcpermutat[k]){
                            desxifrada = desxifrada + Character.toUpperCase(abc[k]);
                        }
                    } 
                } else {
                    for (int k = 0; k<abc.length; k++) {
                        if(xifrada.charAt(j)==abcpermutat[k]){
                            desxifrada = desxifrada + (abc[k]);
                        }
                    } 
                }
            } else {
                    desxifrada=desxifrada + xifrada.charAt(j);
            }
        }
        return desxifrada;
    }

    public static char[] permutaAlfabet (char[] abc){
        abcpermutat = new char[abc.length];
        Random rnd = new Random();

        List<Character> charList = new ArrayList<>();
        for (char c : abc) {
            charList.add(c);
        }
        
        Collections.shuffle(charList, rnd);
        
        for (int i = 0; i < abc.length; i++) {
            abcpermutat[i] = charList.get(i);
        }
        
        return abcpermutat;
    }
}


