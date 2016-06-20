package microblogging.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import microblogging.model.User;

public class MicrobloggingUtil {

    public static User generateRandomUser() {
        User result = new User(
                "test" + UUID.randomUUID().toString(),
                UUID.randomUUID().toString());
        return result;
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                .format(date);
    }
}
