package iticbcn.xifratge;
import java.util.*;

class XifradorPolialfabetic implements Xifrador {

    public static final char[] abc = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static int clauSecreta;
    public static Random random;
    public static char[] abcpermutat;

    public String xifraPoliAlfa (String cadena){
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

    public String desxifraPoliAlfa (String xifrada){
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

    public void permutaAlfabet () {
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

    public void initRandom (int clauSecreta) {
        random = new Random(clauSecreta);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'xifra'");
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desxifra'");
    }
}
