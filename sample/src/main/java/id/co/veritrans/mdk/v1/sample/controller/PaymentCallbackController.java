package id.co.veritrans.mdk.v1.sample.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.veritrans.mdk.v1.exception.JsonDeserializeException;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.helper.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by gde on 5/21/15.
 */
@Controller
@RequestMapping("/callback")
public class PaymentCallbackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentCallbackController.class);

    @RequestMapping(value = "payment/notification", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Object paymentNotificationPost(final HttpServletRequest servletRequest) throws IOException, JsonDeserializeException {
        final VtResponse vtResponse = VtResponse.deserializeJson(servletRequest.getInputStream());
        /**
         * Should do something here such as: marking the transaction status by inspecting the vtResponse.
         */
        LOGGER.info("paymentNotificationPost got vtResponse: {}", JsonUtil.toJson(vtResponse));
        return new Object() {
            @JsonProperty
            public String status = "OK";
        };
    }
}
