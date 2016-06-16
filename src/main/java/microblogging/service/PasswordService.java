package microblogging.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.Base64Utils;

public class PasswordService {

    private PasswordService() {
    }

    private static PasswordService instance;

    public synchronized static PasswordService getInstance() {
        if (instance == null) {
            instance = new PasswordService();
        }

        return instance;
    }

    public synchronized String encode(String plain) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA"); // step 2
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            md.update(plain.getBytes("UTF-8")); // step 3
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte raw[] = md.digest(); // step 4
        return Base64Utils.encodeToString(raw);
    }

    public boolean check(String raw, String stored) {
        String encrypted = encode(raw);
        return encrypted.equals(stored);
    }
}
