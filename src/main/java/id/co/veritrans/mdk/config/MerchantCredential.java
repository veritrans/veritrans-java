package id.co.veritrans.mdk.config;

/**
 * This class will contain merchant credential (server key and client key)
 * that will be used for merchant authentication on veritrans API
 *
 * <br><br>
 * Created by gde on 4/30/15.
 */
public class MerchantCredential {

    private String serverKey;
    private String clientKey;

    /**
     * This object contains merchant credential that will be use for merchant authentication
     * <br>Merchant credential value can recieve from veritrans Merchant Administration Portal
     *
     * <br><br>
     * <i>If use this constructor, is mandatory to setup merchant server_key and client_key manually</i>
     */
    public MerchantCredential() {
    }

    /**
     * This object contains merchant credential that will be use for merchant authentication
     * <br>
     * <i>Merchant credential value can recieve from veritrans Merchant Administration Portal</i>
     *
     * @param serverKey Plain merchant server key
     * @param clientKey Plain merchant client key
     */
    public MerchantCredential(final String serverKey, final String clientKey) {
        this.serverKey = serverKey;
        this.clientKey = clientKey;
    }

    public String getServerKey() {
        return serverKey;
    }

    public void setServerKey(final String serverKey) {
        this.serverKey = serverKey;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(final String clientKey) {
        this.clientKey = clientKey;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final MerchantCredential that = (MerchantCredential) o;

        if (clientKey != null ? !clientKey.equals(that.clientKey) : that.clientKey != null) return false;
        if (serverKey != null ? !serverKey.equals(that.serverKey) : that.serverKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serverKey != null ? serverKey.hashCode() : 0;
        result = 31 * result + (clientKey != null ? clientKey.hashCode() : 0);
        return result;
    }
}
