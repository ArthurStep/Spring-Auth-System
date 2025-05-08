package main.artfix.passtimenote.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class AESService {

    @Value("${app.aes.key}")
    private String KEY;

    public String encrypt(String originalString) {
        try {
            byte[] keyBytes = KEY.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(originalString.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("AES Error. encrypt");
            System.err.println("AES Error. encrypt");

        }
        return originalString;
    }

    public String decrypt(String encryptedString) {
        try {
            byte[] keyBytes = KEY.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedString);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("AES Error. decrypt");
            System.err.println("AES Error. decrypt");

        }
        return encryptedString;
    }

}
