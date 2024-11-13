import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Hashes {
    int npass = 0;
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
    
    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(salt.getBytes()); 
            byte[] hash = digest.digest(pw.getBytes());
            HexFormat hex = HexFormat.of(); 
            return hex.formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            int iterations = 10000;
            int keyLength = 512;
            char[] chars = pw.toCharArray();
            byte[] saltBytes = salt.getBytes();
            javax.crypto.SecretKeyFactory skf = javax.crypto.SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            javax.crypto.spec.PBEKeySpec spec = new javax.crypto.spec.PBEKeySpec(chars, saltBytes, iterations, keyLength);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            return hex.formatHex(hash);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String forcaBruta(String alg, String hash, String salt) {
        String charset = "abcdefABCDEF1234567890!";  // Charset de caràcters possibles per a la contrasenya
        char[] password = new char[6];  // La contrasenya provarà una longitud màxima de 6 caràcters
        StringBuilder sb = new StringBuilder();
        

        
        // Bucle anidat per provar totes les combinacions possibles de 6 caràcters
        for (int i = 0; i < charset.length(); i++) {
            password[0] = charset.charAt(i); // Primera posició
            for (int j = 0; j < charset.length(); j++) {
                password[1] = charset.charAt(j); // Segona posició
                for (int k = 0; k < charset.length(); k++) {
                    password[2] = charset.charAt(k); // Tercera posició
                    for (int l = 0; l < charset.length(); l++) {
                        password[3] = charset.charAt(l); // Quarta posició
                        for (int m = 0; m < charset.length(); m++) {
                            password[4] = charset.charAt(m); // Cinquena posició
                            for (int n = 0; n < charset.length(); n++) {
                                password[5] = charset.charAt(n); // Sisena posició
                                String attempt = new String(password);  // Generem la contrasenya
                                npass++; // Incrementem el contador de contrasenyes provades
                                
                                String generatedHash = (alg.equals("SHA-512")) ? 
                                        getSHA512AmbSalt(attempt, salt) : getPBKDF2AmbSalt(attempt, salt); // Generem el hash de la contrasenya
                                        
                                if (generatedHash != null && generatedHash.equals(hash)) {
                                    long endTime = System.currentTimeMillis();  // Temps final
                                    System.out.println("Hash trencat: " + attempt);
                                    return attempt;  // Retornem la contrasenya trobada
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return null; // Si no es troba cap coincidència
    }
    

    public String getInterval(long t1, long t2) {
        long interval = t2 - t1;
        return interval + " ms";
        
    }
}

