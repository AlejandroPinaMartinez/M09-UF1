package iticbcn.xifratge;
import java.util.*;

class  XifradorMonoalfabetic implements Xifrador {

    public static final char[] abc = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static char[] abcpermutat;

    // Constructor per inicialitzar la permutació
    public XifradorMonoalfabetic() {
        abcpermutat = permutaAlfabet(abc);
    }

    public char[] permutaAlfabet (char[] abc){
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try{
            if (clau != null) {
                throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
            }
            String xifrada="";
            for (int j= 0; j<msg.length(); j++){
                if (Character.isLetter(msg.charAt(j))) {
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
        } catch (Exception e) {
            throw new ClauNoSuportada("Error de cifrado: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            if (clau != null) {
                throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
            }
            String xifrada = new String(xifrat.toString());
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
            return desxifrada.toString();

        } catch (Exception e) {
            throw new ClauNoSuportada("Error de cifrado: " + e.getMessage());
        }
    }
}


