package iticbcn.xifratge;
import java.util.*;

class  XifradorMonoalfabetic implements Xifrador {

    public static final char[] abc = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static char[] abcpermutat;

    public String xifraMonoAlfa (String cadena){
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

    public String desxifraMonoAlfa (String xifrada){
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'xifra'");
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desxifra'");
    }
}

