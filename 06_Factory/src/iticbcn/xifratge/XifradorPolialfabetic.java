package iticbcn.xifratge;
import java.util.*;

class XifradorPolialfabetic implements Xifrador {

    public static final char[] abc = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static int clauSecreta;
    public static Random random;
    public static char[] abcpermutat;

    public XifradorPolialfabetic() {
        abcpermutat = new char[abc.length];
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

    public void initRandom (long clauSecreta) {
        random = new Random(clauSecreta);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            // Convertir la clau a long
            long clauSecreta = Long.parseLong(clau);
            initRandom(clauSecreta);

            // Cifrar el missatge
            String xifrada="";
            for (int j= 0; j<msg.length(); j++){
                if (Character.isLetter(msg.charAt(j))) {
                    permutaAlfabet();
                    if (Character.isUpperCase(msg.charAt(j))) {
                        for (int k = 0; k<abc.length; k++) {
                            if(Character.toLowerCase(msg.charAt(j))==abc[k]){
                                xifrada = xifrada + Character.toUpperCase(abcpermutat[k]);
                            }
                        } 
                    } else {
                        for (int k = 0; k<abc.length; k++) {
                            if(msg.charAt(j)==abc[k]){
                                xifrada = xifrada + (abcpermutat[k]);
                            }
                        } 
                    }
                } else {
                        xifrada=xifrada + msg.charAt(j);
                }
            }
            return new TextXifrat(xifrada.getBytes());

        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            // Convertir la clau a long
            long clauSecreta = Long.parseLong(clau);
            initRandom(clauSecreta);

            // Descifrar el mensaje
            String xifrada = new String(xifrat.toString());
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

        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }
}
