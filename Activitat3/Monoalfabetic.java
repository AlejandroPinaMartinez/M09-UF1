import java.util.*;
import java.util.random.RandomGenerator;

public class Monoalfabetic {

    public static final char[] abc = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();

    public static void main(String[] args) {
        Scanner sc = new Scanner();
        System.out.println("Vols permutar el alfabet(p)? Xifrar la cadena (x)? O desxifrar la cadena (d)"?);
        String pcx = sc.nextLine();
        if (pcx!="x" || pcx!="d" || pcx != "p") { System.out.println("Resposta no trobada, ha de ser (p), (x) o (d)");}
        else if (pcx.equals("p")){
        char[] Abcpermutat = permutaAlfabet(abc);
        System.out.println("Abecedari perumtat: " + Abcpermutat);
        } else { 
            System.out.println("Insereix una cadena de text: ");
            char[] cadena = sc.nextLine().toCharArray();
            if(pcx.equals("x")){
            char[] cadenaxifrada = xifraMonoAlfa(cadena);
            System.out.println("Cadena xifrada: " + cadenaxifrada);
            } else if (pcx.equals("d")){
            char[] cadenadesxifrada = desxifraMonoAlfa(cadena);
            System.out.println("Cadena desxifrada: " + cadenadesxifrada);
            }
        }
    }

    public static char[] xifraMonoAlfa (char[] cadena){
        char[] abcpermutat = new char[abc.length];
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

    public static char[] desxifraMonoAlfa (char[] cadena){
        char[] abcpermutat = new char[abc.length];
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

    public static char[] permutaAlfabet (char[] abc){
        char[] abcpermutat = new char[abc.length];
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


