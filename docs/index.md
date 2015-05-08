# Veritrans - Java Client Documentation
This is the official Java Client for Veritrans Payment API.  

Please visit [https://www.veritrans.co.id](https://www.veritrans.co.id) for more information about the products and see the documentations at [http://docs.veritrans.co.id](http://docs.veritrans.co.id) for more technical details. We also have a few [sequence diagrams](sequence_diagram/index.html) which can help you to understands the big picture of our various payment methods process flow.

***

# Installation

## Maven
If you're using Maven as the build tools for your project, add the following dependency to your project's build definition (pom.xml):
```xml
<dependency>
    <groupId>id.co.veritrans</groupId>
    <artifactId>veritrans-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Gradle
If you're using Gradle as the build tools for your project, add the following dependency to your project's build definition (build.gradle):
```groovy
compile 'id.co.veritrans:veritrans-java:1.0.0'
```

***

# Usage

## VtGatewayConfig
VtGatewayConfig stores the settings that is needed by the Veritrans-Java Client to properly accessing the Veritrans Payment API.
Please note that every classes related with VtGatewayConfig is `immutable` by design. However a builder class is provided to aid the object creation.  

See [VtGatewayConfig Javadoc](javadoc/id/co/veritrans/mdk/v1/VtGatewayConfig.html).
See [VtGatewayConfigBuilder Javadoc](javadoc/id/co/veritrans/mdk/v1/VtGatewayConfigBuilder.html).

### Server & Client Key
You must set the `Server Key` and `Client Key` as defined in your [Merchant Administration Portal (MAP)](https://my.sandbox.veritrans.co.id/login).
```java
VtGatewayConfigBuilder vtGatewayConfigBuilder = new VtGatewayConfigBuilder();
vtGatewayConfigBuilder.setServerKey("Your Server Key");
vtGatewayConfigBuilder.setClientKey("Your Client Key");
```

### Environment
You can choose to use Sandbox or Production environment. During development & testing, you might want to set it to Sandbox and use the Production environment when you're ready to go live.  

See [EnvironmentType Javadoc](javadoc/id/co/veritrans/mdk/v1/config/EnvironmentType.html).
```java
// config for sandbox environment
vtGatewayConfigBuilder.setEnvironment(EnvironmentType.SANDBOX);

// config for production environment
vtGatewayConfigBuilder.setEnvironment(EnvironmentType.PRODUCTION);
```

### Proxy Configuration
You can setup proxy configuration if you need connect to Veritrans Payment API through proxy server.
```java
vtGatewayConfigBuilder.getHttpConfig().getProxyConfig().setHost("proxy host address");
vtGatewayConfigBuilder.getHttpConfig().getProxyConfig().setPort(12345);
vtGatewayConfigBuilder.getHttpConfig().getProxyConfig().setUsername("proxy username or null");
vtGatewayConfigBuilder.getHttpConfig().getProxyConfig().setPassword("proxy password or null");
```

<br/>
## VtGatewayFactory
VtGatewayFactory is a factory class which is used to obtain a reference to various Veritrans Product interface instance, eg: VtDirect instance. This class is also responsible as a manager for every Veritrans Product interface instance returned by the instance of this class. Normally you will make a single instance of VtGatewayFactory class and maintain the reference to this instance, then use it whenever you need to obtain a reference to a Veritrans Product interface. Some instance of Veritrans Product interface instance returned by this class maybe safe to be cached, such as: VtDirect and VtWeb.  
  
If you need to have multiple VtGatewayFactory with different configuration profiles, consider to make a VtGatewayFactory instance for each configuration profile and reuse that VtGatewayFactory instance to obtain reference to Veritrans Product interface instances. VtGatewayFactory also has few a convenient methods to directly configure the underlying VtGatewayConfig instance.

See [VtGatewayFactory Javadoc](javadoc/id/co/veritrans/mdk/v1/VtGatewayFactory.html)  
Example code to build a VtGatewayFactory:
```java
VtGatewayFactory vtGatewayFactory = new VtGatewayFactory(vtGatewayConfigBuilder.createVtGatewayConfig());
```

<br/>
## VtDirect
VtDirect is an interface class, where it's instance can be used to communicate with Veritrans Payment API. VtDirect instance is safe to share with multiple threads, hence you can safely cache the instance of this class and reuse it multiple times.  

See [VtDirect Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/VtDirect.html).  
Example code to obtain reference to VtDirect instance:
```java
VtDirect vtDirect = vtGatewayFactory.vtDirect();
```

### Charging a transaction
VtDirect has method named `charge` which takes an instance of VtDirectChargeParam subclass as the parameter.
This method will make a charge request to Veritrans Payment API and return a VtResponse as a result, which can be used to determine the status of the transaction.  

See [VtDirectChargeParam](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/VtDirectChargeParam.html)  
See [VtResponse](javadoc/id/co/veritrans/mdk/v1/gateway/model/VtResponse.html)  
Visit [http://docs.veritrans.co.id/sandbox/charge.html](http://docs.veritrans.co.id/sandbox/charge.html) for more information.

#### VtDirectChargeParam
VtDirectChargeParam has specific subclass for a specific payment method, ex: for Credit Card payment method, there is a subclass named CreditCardRequest. The list of currently supported payment methods:  
- [BankTransferRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/BankTransferRequest.html)  
- [BriEpayRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/BriEpayRequest.html)  
- [CimbClicksRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/CimbClicksRequest.html)  
- [CreditCardRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/CreditCardRequest.html)  
- [MandiriClickpayRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/MandiriClickpayRequest.html)  
  
See [id.co.veritrans.mdk.gateway.model Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/package-summary.html)  
See [id.co.veritrans.mdk.gateway.model.vtdirect Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/package-summary.html)

It is recommended to have a single method to configure the generic VtDirectChargeParam values. Example for Credit Card charge:
```java
...
//somewhere in the code
CreditCardRequest vtDirectChargeParam = new CreditCardRequest();
setVtDirectChargeParamValues(vtDirectChargeParam);
//continue processing
...

/**
 * This method is just for example.
 * The actual values should be obtained from another sources.
 */
public void setVtDirectChargeParamValues(VtDirectChargeParam vtDirectChargeParam) {
    vtDirectChargeParam.setTransactionDetails(new TransactionDetails());
    vtDirectChargeParam.setCustomerDetails(new CustomerDetails());

    vtDirectChargeParam.getTransactionDetails().setOrderId("your unique order ID");
    vtDirectChargeParam.getTransactionDetails().setGrossAmount(10000l);

    vtDirectChargeParam.getCustomerDetails().setEmail("user@domain.com");
    vtDirectChargeParam.getCustomerDetails().setFirstName("firstName");
    vtDirectChargeParam.getCustomerDetails().setPhone("0123456789");
    vtDirectChargeParam.getCustomerDetails().setBillingAddress(new Address());

    Address billingAddress = vtDirectChargeParam.getCustomerDetails().getBillingAddress();
    billingAddress.setAddress("Random Street 6A");
    billingAddress.setCity("Jakarta Pusat");
    billingAddress.setPostalCode("12210");
}
```

<br/>
#### Bank Transfer
See [BankTransfer Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/paymentmethod/BankTransfer.html)  
See [Bank Transfer Process Flow](sequence_diagram/index.html#process-flow-for-charging-via-bank-transfer)
```java
BankTransferRequest vtDirectChargeParam = new BankTransferRequest();
setVtDirectChargeParamValues(vtDirectChargeParam);
vtDirectChargeParam.setBankTransfer(new BankTransfer());

vtDirectChargeParam.getBankTransfer().setDescription("Payment for Merchant XYZ");
vtDirectChargeParam.getBankTransfer().setBank(BankTransfer.Bank.PERMATA);

VtResponse vtResponse = vtDirect.charge(vtDirectChargeParam);

if (vtResponse.getTransactionStatus() == TransactionStatus.PENDING) {
    //handle successful Bank Transfer charge request
    //check the permataVaNumber in the vtResponse
} else {
    //handle denied / unexpected response
}
```

<br/>
#### Bri Epay
```java
BriEpayRequest vtDirectChargeParam = new BriEpayRequest();
setVtDirectChargeParamValues(vtDirectChargeParam);

VtResponse vtResponse = vtDirect.charge(vtDirectChargeParam);

if (vtResponse.getTransactionStatus() == TransactionStatus.PENDING) {
    //handle successful Bank Transfer charge request
    //check the redirectUrl value in the vtResponse
} else {
    //handle denied / unexpected response
}
```

<br/>
#### Cimb Clicks
See [CimbClicks Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/paymentmethod/CimbClicks.html)
```java
CimbClicksRequest vtDirectChargeParam = new CimbClicksRequest();
setVtDirectChargeParamValues(vtDirectChargeParam);
vtDirectChargeParam.setCimbClicks(new CimbClicks());

vtDirectChargeParam.getCimbClicks().setDescription("Payment for Merchant XYZ");

VtResponse vtResponse = vtDirect.charge(vtDirectChargeParam);

if (vtResponse.getTransactionStatus() == TransactionStatus.PENDING) {
    //handle successful Cimb Clicks charge request
    //check the redirectUrl value in the vtResponse
} else {
    //handle denied / unexpected response
}
```

<br/>
#### Credit Card Sale
See [CreditCard Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/paymentmethod/CreditCard.html)  
See [CreditCard Process Flow Without 3D Secure Authentication](sequence_diagram/index.html#credit-card-charging-without-3d-secure-authentication)  
See [CreditCard Process Flow With 3D Secure Authentication](sequence_diagram/index.html#credit-card-charging-with-3d-secure-authentication)  

**Every Credit Card transaction must use a `Token` generated by Veritrans Payment API instead of using plain card number.** Please visit [Acquiring Credit Card Token](http://docs.veritrans.co.id/vtdirect/integration_cc.html#step1) for further information.
```
CreditCardRequest vtDirectChargeParam = new CreditCardRequest();
setVtDirectChargeParamValues(vtDirectChargeParam);
vtDirectChargeParam.setCreditCard(new CreditCard());

//token is obtained from HTTP POST variable.
vtDirectChargeParam.getCreditCard().setTokenId("creditCardToken");
//your acquirer bank for Credit Card
vtDirectChargeParam.getCreditCard().setAcquirerBank(CreditCard.Bank.BNI);

VtResponse vtResponse = vtDirect.charge(vtDirectChargeParam);

if (vtResponse.getTransactionStatus() == TransactionStatus.CAPTURED) {
    if (vtResponse.getFraudStatus() == FraudStatus.ACCEPTED) {
        //handle successful capture
    } else if (vtResponse.getFraudStatus() == FraudStatus.CHALLENGE) {
        //handle FDS challenge, you can do this later
    } else {
        //unexpected condition that should never happen
    }
} else {
    //handle denied / unexpected response
}
```

<br/>
#### Credit Card Feature: Pre-authorization & Capture
##### Pre-authorization
See [CreditCard Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/paymentmethod/CreditCard.html)  
See [CreditCard Process Flow Without 3D Secure Authentication](sequence_diagram/index.html#credit-card-charging-without-3d-secure-authentication)  
See [CreditCard Process Flow With 3D Secure Authentication](sequence_diagram/index.html#credit-card-charging-with-3d-secure-authentication)  

**Every Credit Card transaction must use a `Token` generated by Veritrans Payment API instead of using plain card number.** Please visit [Acquiring Credit Card Token](http://docs.veritrans.co.id/vtdirect/integration_cc.html#step1) for further information.
```java
CreditCardRequest vtDirectChargeParam = new CreditCardRequest();
setVtDirectChargeParamValues(vtDirectChargeParam);
vtDirectChargeParam.setCreditCard(new CreditCard());

//token is obtained from HTTP POST variable.
vtDirectChargeParam.getCreditCard().setTokenId("creditCardToken");
//your acquirer bank for Credit Card
vtDirectChargeParam.getCreditCard().setAcquirerBank(CreditCard.Bank.BNI);
vtDirectChargeParam.getCreditCard().setType(CreditCard.TransactionType.AUTHORIZE);

VtResponse vtResponseAuthorize = vtDirect.charge(vtDirectChargeParam);

if (vtResponseAuthorize.getTransactionStatus() == TransactionStatus.AUTHORIZED) {
    if (vtResponseAuthorize.getFraudStatus() == FraudStatus.ACCEPTED) {
        //handle successful authorize
    } else if (vtResponseAuthorize.getFraudStatus() == FraudStatus.CHALLENGE) {
        //handle FDS challenge, you can do this later
    } else {
        //unexpected condition that should never happen
    }
} else {
    //handle denied / unexpected response
}
```

##### Capture
Authorized Credit Card charge request can be captured eventhough the FDS status is `CHALLENGE`, however the transaction won't be settled unless the transaction is approved using the [Credit Card: Accept an FDS challenge capture](#credit-card-accept-an-fds-challenge-capture) feature.
```java
String transactionId = vtResponseAuthorize.getTransactionId();
Long captureAmount = vtResponseAuthorize.getGrossAmount().longValue();
VtResponse vtResponseCapture = vtDirect.capture(transactionId, captureAmount);

if (vtResponseCapture.getTransactionStatus() == TransactionStatus.CAPTURED) {
    //handle successful capture
} else {
    //handle denied / unexpected response
}
```

<br/>
#### Mandiri Clickpay
See [MandiriClickpay Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/paymentmethod/MandiriClickpay.html)  
Visit [Veritrans Mandiri Clickpay Documentation](http://docs.veritrans.co.id/sandbox/charge.html#vtdirect-mandiri) for more information.
```java
MandiriClickpayRequest vtDirectChargeParam = new MandiriClickpayRequest();
setVtDirectChargeParamValues(vtDirectChargeParam);
vtDirectChargeParam.setMandiriClickpay(new MandiriClickpay());

vtDirectChargeParam.getMandiriClickpay().setCardNumber("4111111111111111");
vtDirectChargeParam.getMandiriClickpay().setInput1("111111111");
vtDirectChargeParam.getMandiriClickpay().setInput2("10000");
vtDirectChargeParam.getMandiriClickpay().setInput3("54321");
vtDirectChargeParam.getMandiriClickpay().setToken("000000");

VtResponse vtResponse = vtDirect.charge(vtDirectChargeParam);

if (vtResponse.getTransactionStatus() == TransactionStatus.SETTLED) {
    //handle successful Mandiri Clickpay charge request
} else {
    //handle denied / unexpected response
}
```

<br/>
## VtWeb
VtWeb is an interface class, where it's instance can be used to communicate with Veritrans Payment API, but **VtWeb doesn't has VtDirect's `charge` functionality**, instead it has a method to get a redirection URL which is used to redirect customers to Veritrans's Payment Page. VtWeb instance is safe to share with multiple threads, hence you can safely cache the instance of this class and reuse it multiple times.  

See [VtWeb Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/VtWeb.html).  
Example code to obtain reference to VtWeb instance:
```java
VtWeb vtWeb = vtGatewayFactory.vtWeb();
```

<br/>
### Charging a transaction
WIP

<br/>
#### VtWebChargeParam
WIP

<br/>
## Other Features

### Check Transaction Status
#### VtDirect
```java
String orderId = "your unique order ID";
VtResponse vtResponse = vtDirect.status(orderId);
//continue processing based on the vtResponse
```
#### VtWeb
```java
String orderId = "your unique order ID";
VtResponse vtResponse = vtWeb.status(orderId);
//continue processing based on the vtResponse
```

<br/>
### Credit Card: Accept an `FDS challenge` capture
#### VtDirect
```java
String orderId = "your unique order ID";
VtResponse vtResponse = vtDirect.approve(orderId);

if (vtResponse.getTransactionStatus() == TransactionStatus.CAPTURED) {
    //handle successful capture approval
} else {
    //handle denied / unexpected response
}
```
#### VtWeb
```java
String orderId = "your unique order ID";
VtResponse vtResponse = vtWeb.approve(orderId);

if (vtResponse.getTransactionStatus() == TransactionStatus.CAPTURED) {
    //handle successful capture approval
} else {
    //handle denied / unexpected response
}
```

<br/>
### Cancel Transaction
#### VtDirect
```java
String orderId = "your unique order ID";
VtResponse vtResponse = vtDirect.cancel(orderId);

if (vtResponse.getTransactionStatus() == TransactionStatus.CANCELLED) {
    //handle successful transaction cancel
} else {
    //handle denied / unexpected response
}
```
#### VtWeb
```java
String orderId = "your unique order ID";
VtResponse vtResponse = vtWeb.approve(orderId);

if (vtResponse.getTransactionStatus() == TransactionStatus.CANCELLED) {
    //handle successful transaction cancel
} else {
    //handle denied / unexpected response
}
```

<br/>
## Notification Handler (Notification URL)
Notification URL is used by Veritrans Payment API to notify a merchant once a payment process has been completed or failed.
It is invoked by Veritrans Payment API through HTTP POST by sending a JSON Message in the request body.
The structure of the JSON Message is identical with the JSON Response when invoking the Veritrans Payment API.  
  
There is a static method provided by VtResponse to help you deserializing the JSON Message into a VtResponse instance.
This method accepts [JSON String](file:///Users/gde/Documents/dev/maverick/veritrans-java/site/javadoc/id/co/veritrans/mdk/v1/gateway/model/VtResponse.html#deserializeJson-java.lang.String-) or a [Raw Input Stream](javadoc/id/co/veritrans/mdk/v1/gateway/model/VtResponse.html#deserializeJson-java.io.InputStream-).
**Do remember that it is still the caller responsibility to close the InputStream**.

Below is an example code to handle Notification URL using Java Servlet API:
```java
...
void doPost(HttpServletRequest req, HttpServletResponse resp) {
    try {
        ServletInputStream inputStream = req.getInputStream();
        VtResponse vtResponse = VtResponse.deserializeJson(inputStream);

        //if necessary, we can utilize VtDirect's or VtWeb's Check Transaction Status Feature to make sure the notification is really coming from Veritrans
        String orderId = vtResponse.getOrderId();
        vtResponse = vtDirect.status(orderId); //we used VtDirect in this example, however we can use VtWeb too

        if (vtResponse.getTransactionStatus() == TransactionStatus.SETTLED) {
            //handle settled / successful charge request
        } else {
            //handle denied / unexpected response
        }
    } catch (JsonDeserializeException e) {
        //handle JSON deserialization error
        ...
    } finally {
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
...
```
