package watchgirl;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class HmacGenerator {

    /*
    From https://stackoverflow.com/questions/7124735/hmac-sha256-algorithm-for-signature-calculation
     */

       public String generateHmac(String message, String key) throws Exception {
           String algorithm = "HmacSHA256";
           Charset charset = StandardCharsets.UTF_8;

           Mac sha256Hmac = Mac.getInstance(algorithm);
           SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(charset), algorithm);
           sha256Hmac.init(secret_key);

           return Hex.encodeHexString(sha256Hmac.doFinal(message.getBytes(charset)));
       }
}
