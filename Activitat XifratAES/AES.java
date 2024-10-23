import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.security.SecureRandom;

public class AES {

    // Constantes de la clase
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "Alejandro";

    // Método para cifrar
    public static byte[] xifraAES(String msg, String clau) throws Exception {
        // Obtenemos los bytes del mensaje
        byte[] msgBytes = msg.getBytes();

        // Generamos el IV de forma aleatoria
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Crear clave a partir de la contraseña (hash)
        SecretKeySpec secretKey = generaClau(clau);

        // Configurar el cifrado AES en modo CBC con relleno PKCS5
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // Cifrar el mensaje
        byte[] xifrat = cipher.doFinal(msgBytes);

        // Combinar el IV y el mensaje cifrado
        byte[] combined = new byte[iv.length + xifrat.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(xifrat, 0, combined, iv.length, xifrat.length);

        return combined;
    }

    // Método para descifrar
    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        // Extraer el IV de los primeros 16 bytes
        byte[] ivExtraido = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(ivExtraido);

        // Extraer la parte cifrada
        byte[] msgXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

        // Generar la clave a partir de la contraseña (hash)
        SecretKeySpec secretKey = generaClau(clau);

        // Configurar el descifrado AES en modo CBC con relleno PKCS5
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // Descifrar el mensaje
        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);

        return new String(msgDesxifrat);
    }

    // Método para generar una clave (hash) a partir de la contraseña
    private static SecretKeySpec generaClau(String clau) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = sha.digest(clau.getBytes("UTF-8"));
        return new SecretKeySpec(clauHash, ALGORISME_XIFRAT);
    }

    // Método principal
    public static void main(String[] args) {
        String msgs[] = {
            "Lorem ipsum dicet",
            "Hola Andrés cómo está tu cuñado",
            "Àgora ïlla Ôtto",
            "!·$%&/() =?¿ª*-+",
            "çñÀáèéìí.,^<"
        };

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            byte[] bXifrats = null;
            String desxifrat = "";

            try {
                // Cifrar el mensaje
                bXifrats = xifraAES(msg, CLAU);

                // Descifrar el mensaje cifrado
                desxifrat = desxifraAES(bXifrats, CLAU);

            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            // Mostrar resultados
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + Base64.getEncoder().encodeToString(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}
