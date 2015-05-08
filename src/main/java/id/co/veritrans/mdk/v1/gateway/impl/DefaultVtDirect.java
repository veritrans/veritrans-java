package id.co.veritrans.mdk.v1.gateway.impl;

import id.co.veritrans.mdk.v1.exception.InvalidVtConfigException;
import id.co.veritrans.mdk.v1.exception.VtConnectionException;
import id.co.veritrans.mdk.v1.helper.ValidationUtil;
import id.co.veritrans.mdk.v1.exception.VtException;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.VtDirectChargeParam;
import id.co.veritrans.mdk.v1.helper.JsonUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.Set;

/**
 * Created by gde on 5/4/15.
 */
public class DefaultVtDirect extends DefaultVtGateway implements VtDirect {

    public DefaultVtDirect(final VtGatewaySession vtGatewaySession) {
        super(vtGatewaySession);
    }

    @Override
    public VtResponse charge(VtDirectChargeParam vtDirectChargeParam) throws VtException {

        /* Validate request before send */
        Set<ConstraintViolation<VtDirectChargeParam>> err = ValidationUtil.getValidator().validate(vtDirectChargeParam);
        if (!err.isEmpty()) {
            String errorMessage = ValidationUtil.buildExceptionMessage(err.toArray());
            throw new InvalidVtConfigException(errorMessage);
        }

        try {
            CloseableHttpClient httpClient = getVtGatewaySession().getHttpClient();
            String requestMessage = JsonUtil.toJson(vtDirectChargeParam);
            HttpResponse response = httpClient.execute(new HttpPost(requestMessage));
        } catch (IOException e) {
            throw new VtConnectionException(e);
        }

        return null;
    }

    @Override
    public VtResponse capture(final String transactionId, final Long amount) {
        return null;
    }

}
