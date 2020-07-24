package watchgirl;

public class SecretKeeper {

    private final String secret;

    public SecretKeeper(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }
}
