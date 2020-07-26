package watchgirl.admin;

import java.util.HashMap;
import java.util.UUID;

public class SecretKeeper {

    private static SecretKeeper secretKeeper;
    private final HashMap<UUID, String> uuidSecrets = new HashMap<>();

    private SecretKeeper() {    }

    public static SecretKeeper getInstance() {
        if (secretKeeper == null) {
            secretKeeper = new SecretKeeper();
        }
        return secretKeeper;
    }

    public void registerCameraSecret(UUID cameraId, String secret) {
        uuidSecrets.put(cameraId, secret);
    }

    public String getSecret(UUID cameraId) {
        return uuidSecrets.get(cameraId);
    }
}
