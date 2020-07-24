package watchgirl;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class CustomHasher {

    private final HashFunction sha256 = Hashing.sha256();

    public String getDigest(String time, String secret) {
        String stringToHash = "" + time + secret;

        return sha256.hashString(stringToHash, StandardCharsets.UTF_8).toString();
    }
}
