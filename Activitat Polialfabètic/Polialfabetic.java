import java.util.*;
public class Polialfabetic {

    public static final char[] abc = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static int clauSecreta;
    public static Random random;
    public static char[] abcpermutat;

    public static void main ( String [] args ) {
        String msgs[] = { "Test 01 àrbritre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila" };
        String msgsXifrats [] = new String [ msgs. length ];
        System.out.println ( "Xifratge: \n --------" );
        for ( int i = 0; i < msgs. length ; i ++) {
        initRandom (clauSecreta);
        msgsXifrats [ i ] = xifraPoliAlfa (msgs[ i ]);
        System.out.printf ( "%-34s -> %s%n", msgs[ i ], msgsXifrats [ i ]);
        }
        System . out . println ( "Desxifratge: \n -----------" );
        for ( int i = 0; i < msgs. length ; i ++) {
        initRandom (clauSecreta);
        String msg = desxifraPoliAlfa (msgsXifrats [ i ]);
        System . out . printf ( "%-34s -> %s%n", msgsXifrats [ i ], msg);
        }
    }

    public static String xifraPoliAlfa (String cadena){
        String xifrada="";
        for (int j= 0; j<cadena.length(); j++){
            if (Character.isLetter(cadena.charAt(j))) {
                permutaAlfabet();
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

    public static String desxifraPoliAlfa (String xifrada){
        String desxifrada="";
        for (int j= 0; j<xifrada.length(); j++){
            if (Character.isLetter(xifrada.charAt(j))) {
                permutaAlfabet();
                if (Character.isUpperCase(xifrada.charAt(j))) {
                    for (int k = 0; k<abc.length; k++) { 
                        if(Character.toLowerCase(xifrada.charAt(j))==abcpermutat[k]){
                            desxifrada = desxifrada + Character.toUpperCase(abc[k]);
                        }
                    } 
                } else {
                    for (int k = 0; k<abc.length; k++) {
                        if(xifrada.charAt(j)==abcpermutat[k]){
                            desxifrada = desxifrada + Character.toLowerCase(abc[k]);
                        }
                    } 
                }
            } else {
                    desxifrada=desxifrada + xifrada.charAt(j);
            }
        }
        return desxifrada;
    }

    public static void permutaAlfabet () {
        abcpermutat = new char[abc.length];

        List<Character> charList = new ArrayList<>();
        for (char c : abc) {
            charList.add(c);
        }
        
        Collections.shuffle(charList, random);
        
        for (int i = 0; i < abc.length; i++) {
            abcpermutat[i] = charList.get(i);
        }
    }

    public static void initRandom (int clauSecreta) {
        random = new Random(clauSecreta);
    }
}
