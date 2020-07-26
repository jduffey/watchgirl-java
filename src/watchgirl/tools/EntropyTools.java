package watchgirl.tools;

import java.security.SecureRandom;
import java.util.UUID;

public class EntropyTools {

    public UUID generateUuid() {
        return UUID.randomUUID();
    }

    public String generateSecretKey() {
        int lengthOfKey = 64;

        SecureRandom r = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        while(sb.length() < lengthOfKey){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, lengthOfKey);
    }
}
