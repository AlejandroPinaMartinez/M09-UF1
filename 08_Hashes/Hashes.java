import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    int npass = 0;

    // Main
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
        System.out.printf("===========================\n");
        System.out.printf("Algorisme: %s\n", algorismes[i]);
        System.out.printf("Hash: %s\n",aHashes[i]);
        System.out.printf("---------------------------\n");
        System.out.printf("-- Inici de força bruta ---\n");
        
        long t1 = System.currentTimeMillis();
        pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
        long t2 = System.currentTimeMillis();
        
        System.out.printf("Pass : %s\n", pwTrobat);
        System.out.printf("Provats: %d\n", h.npass);
        System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
        System.out.printf("---------------------------\n\n");
        }
    }
    
    // Metode per calcular el hash SHA-512 d'una contraseña junt amb unsalt
    public String getSHA512AmbSalt(String pw, String salt) {
        try {

            // instància de l'algoritme SHA-512
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            // Actualitza l'objecte MessageDigest amb els bytes de la sal
            digest.update(salt.getBytes()); 

            // Calcul del hash dels bytes de la contrasenya, ja combinada amb la sal.
            byte[] hash = digest.digest(pw.getBytes());

            // convertir el hash (bytes) a una representació hexadecimal.
            HexFormat hex = HexFormat.of(); 

            // retorna hash en format hexadecimal
            return hex.formatHex(hash);
        } catch (NoSuchAlgorithmException e) { // Maneig d'excepció
            e.printStackTrace();
            return null; 
        }
    }
    
    // Mètode per generar un hash utilitzant PBKDF2 amb una salt
    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {

            byte[] abSalt = salt.getBytes(StandardCharsets.UTF_8);
            KeySpec spec = new PBEKeySpec(pw.toCharArray(), abSalt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            // convertir el hash (bytes) a una representació hexadecimal.
            HexFormat hex = HexFormat.of();

            // retorna hash en format hexadecimal
            return hex.formatHex(hash);
        } catch (Exception e) { // Maneig d'excepció
            e.printStackTrace();
            return null;
        }
    }

    public String forcaBruta(String alg, String hash, String salt) {
        String charset = "abcdefABCDEF1234567890!";  // Charset de caràcters possibles per a la contrasenya
        char[] password = new char[6];  // Poso que la contrasenya provari una longitud màxima de 6 caràcters
        
        
        // Bucle anidat per provar totes les combinacions possibles de 6 caràcters que hem afegit a char[] password
        for (int i = 0; i < charset.length(); i++) {
            password[0] = charset.charAt(i); // Primera posició
            for (int j = 0; j < charset.length(); j++) {
                password[1] = charset.charAt(j); // 2na posició
                for (int k = 0; k < charset.length(); k++) {
                    password[2] = charset.charAt(k); // ...
                    for (int l = 0; l < charset.length(); l++) {
                        password[3] = charset.charAt(l); 
                        for (int m = 0; m < charset.length(); m++) {
                            password[4] = charset.charAt(m); 
                            for (int n = 0; n < charset.length(); n++) {
                                password[5] = charset.charAt(n); 
                                String attempt = new String(password);  // Genero la contrasenya
                                npass++; // Incremento el contador de contrasenyes provades
                                
                                String generatedHash = (alg.equals("SHA-512")) ? 
                                        getSHA512AmbSalt(attempt, salt) : getPBKDF2AmbSalt(attempt, salt); // Genero el hash de la contrasenya
                                        
                                if (generatedHash != null && generatedHash.equals(hash)) {
                                    return attempt;  // Retoro la contrasenya trobada
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return null; 
    }
    
    //retorna el interval de temps que tarda en realitzar les operacions, amb la sortida especificada
    public String getInterval(long t1, long t2) {
        long interval = t2 - t1;
    
        long days = interval / (24 * 60 * 60 * 1000);
        interval %= 24 * 60 * 60 * 1000;
    
        long hours = interval / (60 * 60 * 1000);
        interval %= 60 * 60 * 1000;
    
        long minutes = interval / (60 * 1000);
        interval %= 60 * 1000;
    
        long seconds = interval / 1000;
        long millis = interval % 1000;
    
        return days + " dies / " + hours + " hores / " + minutes + " minuts / " + seconds + " segons / " + millis + " millis";
    }
    
}

