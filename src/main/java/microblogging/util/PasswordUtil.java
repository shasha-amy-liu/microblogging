package microblogging.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.Base64Utils;

public class PasswordUtil {

    private PasswordUtil() {
    }

    private volatile static PasswordUtil instance;

    /**
     * Implement a thread-safe singleton, lazy-loading and double checked locking
     * @return
     */
    public static PasswordUtil getInstance() {
        if (instance == null) {
            synchronized (PasswordUtil.class) {
                instance = new PasswordUtil();
            }
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
