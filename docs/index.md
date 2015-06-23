<br/>

[ ![Download](https://api.bintray.com/packages/pt-midtrans/maven/veritrans-java-client/images/download.svg) ](https://bintray.com/pt-midtrans/maven/veritrans-java-client/_latestVersion)
# Veritrans - Java Client Documentation
This is the official Java Client for Veritrans Payment API.  

Please visit [https://www.veritrans.co.id](https://www.veritrans.co.id) for more information about the products and see the documentations at [http://docs.veritrans.co.id](http://docs.veritrans.co.id) for more technical details. We also have a few [sequence diagrams](sequence_diagram/index.html) which can help you to understands the big picture of our various payment methods process flow.

***

# Installation

## Maven
If you're using Maven as the build tools for your project, please add **[jcenter](https://bintray.com/bintray/jcenter)** repository to your build definition, then add the following dependency to your project's build definition (pom.xml):
```xml
<repositories>
    <repository>
        <id>jcenter</id>
        <name>bintray</name>
        <url>http://jcenter.bintray.com</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>id.co.veritrans</groupId>
        <artifactId>vt-java-client</artifactId>
        <version>1.0.3</version>
    </dependency>
</dependencies>
```

## Gradle
If you're using Gradle as the build tools for your project, please add **[jcenter](https://bintray.com/bintray/jcenter)** repository to your build script then add the following dependency to your project's build definition (build.gradle):
```groovy
repositories {
    maven {
        url  "http://jcenter.bintray.com" 
    }
}

dependencies {
    compile 'id.co.veritrans:vt-java-client:1.0.3'
}
```

## Jar Library
If you want to download veritrans java-client jar library on your own, you can download it directly **[here](https://bintray.com/artifact/download/pt-midtrans/maven/id/co/veritrans/vt-java-client/1.0.3/vt-java-client-1.0.3-all.jar)**

# Usage

## VtGatewayConfig
VtGatewayConfig stores the settings that is needed by the Veritrans-Java Client to properly accessing the Veritrans Payment API.
Please note that every classes related with VtGatewayConfig is `immutable` by design. However a builder class is provided to aid the object creation.  

See [VtGatewayConfig Javadoc](javadoc/id/co/veritrans/mdk/v1/VtGatewayConfig.html).
See [VtGatewayConfigBuilder Javadoc](javadoc/id/co/veritrans/mdk/v1/VtGatewayConfigBuilder.html).
See [id.co.veritrans.mdk.v1.config Javadoc](javadoc/id/co/veritrans/mdk/v1/config/package-summary.html)

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
vtGatewayConfigBuilder.setEnvironmentType(EnvironmentType.SANDBOX);

// config for production environment
vtGatewayConfigBuilder.setEnvironmentType(EnvironmentType.PRODUCTION);
```

<br/>
#### Connection Pool Size
Veritrans-Java Client is designed to use a HTTP Connection Pool to maintain it's HTTP Connection to Veritrans Server, thus we can take benefit from the
HTTP Keep Alive feature and reuse the connection. In order to support this functionality, a connection pool size need to be set explicitly,
to accomodate the needs of every different companies.  
We recommend to set `maximum HTTP Connection Pool Size to 16`, however it is up to you to set it to a lower or higher value. Do remember that a too low or too high
value **`could degrade the system performance`**.
```java
httpConfigBuilder.setMaxConnectionPoolSize(16);
vtGatewayConfigBuilder.setHttpConfig(httpConfigBuilder.createHttpConfig());
```

<br/>
#### Proxy Configuration
You can setup proxy configuration if you need connect to Veritrans Payment API through proxy server.  
See [ProxyConfig Javadoc](javadoc/id/co/veritrans/mdk/v1/config/ProxyConfig.html)  
See [ProxyConfigBuilder Javadoc](javadoc/id/co/veritrans/mdk/v1/config/ProxyConfigBuilder.html)
```java
// this demonstrate configuring proxy settings using method chaining from the builder class
ProxyConfigBuilder proxyConfigBuilder = new ProxyConfigBuilder();

vtGatewayConfigBuilder.setHttpConfig(
    httpConfigBuilder.setProxyConfig(
        proxyConfigBuilder.setHost("proxy host address")
            .setPort(12345)
            .setUsername("proxy username or null")
            .setPassword("proxy password or null")
            .createProxyConfig()
    ).createHttpConfig()
);
```

```java
// this demonstrate configuring proxy settings without method chaining
ProxyConfigBuilder proxyConfigBuilder = new ProxyConfigBuilder();

proxyConfigBuilder.setHost("proxy host address");
proxyConfigBuilder.setPort(12345);
proxyConfigBuilder.setUsername("proxy username or null");
proxyConfigBuilder.setPassword("proxy password or null");

ProxyConfig proxyConfig = proxyConfigBuilder.createProxyConfig();
HttpConfig httpConfig = httpConfigBuilder.setProxyConfig(proxyConfig).createHttpConfig();

vtGatewayConfigBuilder.setHttpConfig(httpConfig);
```

<br/>
## VtGatewayFactory
VtGatewayFactory is a factory class which is used to obtain a reference to various Veritrans Product interface instance, eg: VtDirect instance. This class is also responsible as a manager for every Veritrans Product interface instance returned by the instance of this class. Normally you will make a `single instance of VtGatewayFactory class` and `maintain the reference` to this instance, then use it whenever you need to obtain a reference to a Veritrans Product interface. Some instance of Veritrans Product interface instance returned by this class maybe safe to be cached, such as: VtDirect and VtWeb.  
  
If you need to have multiple VtGatewayFactory with different configuration profiles, consider to make a `VtGatewayFactory instance for each configuration profile` and reuse that VtGatewayFactory instance to obtain reference to Veritrans Product interface instances.  
See [VtGatewayFactory Javadoc](javadoc/id/co/veritrans/mdk/v1/VtGatewayFactory.html)  

Example code to build a VtGatewayFactory:
```java
VtGatewayConfig vtGatewayConfig = vtGatewayConfigBuilder.createVtGatewayConfig();
VtGatewayFactory vtGatewayFactory = new VtGatewayFactory(vtGatewayConfig);
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
VtDirect has method named `charge` which takes an instance of VtDirectChargeRequest subclass as the parameter.
This method will make a charge request to Veritrans Payment API and return a VtResponse as a result, which can be used to determine the status of the transaction.  

See [VtDirectChargeRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/VtDirectChargeRequest.html)  
See [VtResponse](javadoc/id/co/veritrans/mdk/v1/gateway/model/VtResponse.html)  
Visit [http://docs.veritrans.co.id/en/api/methods.html#Charge ](http://docs.veritrans.co.id/en/api/methods.html#Charge)for more information.

#### VtDirectChargeRequest
VtDirectChargeRequest has specific subclass for a specific payment method, ex: for Credit Card payment method, there is a subclass named CreditCardRequest. The list of currently supported payment methods:  

- [BankTransferRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/BankTransferRequest.html)  
- [BriEpayRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/BriEpayRequest.html)  
- [CimbClicksRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/CimbClicksRequest.html)  
- [CreditCardRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/CreditCardRequest.html)  
- [MandiriClickpayRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/MandiriClickpayRequest.html)  
- [BcaKlikpayRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/BcaKlikpayRequest.html)  
- [KlikBcaRequest](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/KlikBcaRequest.html)
  
See [id.co.veritrans.mdk.gateway.model Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/package-summary.html)  
See [id.co.veritrans.mdk.gateway.model.vtdirect Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/package-summary.html)

It is recommended to have a single method to configure the generic VtDirectChargeRequest values. Example for Credit Card charge:
```java
...
// somewhere in the code
CreditCardRequest vtDirectChargeRequest = new CreditCardRequest();
setVtDirectChargeRequestValues(vtDirectChargeRequest);
// continue processing
...

/**
 * This method is just for example.
 * The actual values should be obtained from another sources.
 */
public void setVtDirectChargeRequestValues(VtDirectChargeRequest vtDirectChargeRequest) {
    vtDirectChargeRequest.setTransactionDetails(new TransactionDetails());
    vtDirectChargeRequest.setCustomerDetails(new CustomerDetails());

    vtDirectChargeRequest.getTransactionDetails().setOrderId("your unique order ID");
    vtDirectChargeRequest.getTransactionDetails().setGrossAmount(10000l);

    vtDirectChargeRequest.getCustomerDetails().setEmail("user@domain.com");
    vtDirectChargeRequest.getCustomerDetails().setFirstName("firstName");
    vtDirectChargeRequest.getCustomerDetails().setPhone("0123456789");
    vtDirectChargeRequest.getCustomerDetails().setBillingAddress(new Address());

    Address billingAddress = vtDirectChargeRequest.getCustomerDetails().getBillingAddress();
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
BankTransferRequest vtDirectChargeRequest = new BankTransferRequest();
setVtDirectChargeRequestValues(vtDirectChargeRequest);
vtDirectChargeRequest.setBankTransfer(new BankTransfer());

vtDirectChargeRequest.getBankTransfer().setBank(BankTransfer.Bank.PERMATA);

VtResponse vtResponse = vtDirect.charge(vtDirectChargeRequest);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.PENDING) {
    
    // handle successful Bank Transfer charge request
    // check the permataVaNumber in the vtResponse
} else {
    // handle denied / unexpected response
}
```

<br/>
#### Bri Epay
```java
BriEpayRequest vtDirectChargeRequest = new BriEpayRequest();
setVtDirectChargeRequestValues(vtDirectChargeRequest);

VtResponse vtResponse = vtDirect.charge(vtDirectChargeRequest);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.PENDING) {

    // handle successful Bank Transfer charge request
    // check the redirectUrl value in the vtResponse (redirect the customer to the redirectUrl to continue the payment process)
} else {
    // handle denied / unexpected response
}
```

<br/>
#### Cimb Clicks
See [CimbClicks Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/paymentmethod/CimbClicks.html)
```java
CimbClicksRequest vtDirectChargeRequest = new CimbClicksRequest();
setVtDirectChargeRequestValues(vtDirectChargeRequest);
vtDirectChargeRequest.setCimbClicks(new CimbClicks());

vtDirectChargeRequest.getCimbClicks().setDescription("Payment for Merchant XYZ");

VtResponse vtResponse = vtDirect.charge(vtDirectChargeRequest);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.PENDING) {

    // handle successful Cimb Clicks charge request
    // check the redirectUrl value in the vtResponse (redirect the customer to the redirectUrl to continue the payment process)
} else {
    // handle denied / unexpected response
}
```

<br/>
#### Credit Card Sale
See [CreditCard Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/paymentmethod/CreditCard.html)  
See [CreditCard Process Flow Without 3D Secure Authentication](sequence_diagram/index.html#credit-card-charging-without-3d-secure-authentication)  
See [CreditCard Process Flow With 3D Secure Authentication](sequence_diagram/index.html#credit-card-charging-with-3d-secure-authentication)  

**Every Credit Card transaction must use a `Token` generated by Veritrans Payment API instead of using plain card number.** Please visit [Acquiring Credit Card Token](http://docs.veritrans.co.id/vtdirect/integration_cc.html#step1) for further information.
```java
CreditCardRequest vtDirectChargeRequest = new CreditCardRequest();
setVtDirectChargeRequestValues(vtDirectChargeRequest);

CreditCard creditCard = new CreditCardBuilder()
    .setTokenId("creditCardToken")          // token is obtained from HTTP POST variable.
    .setAcquirerBank(CreditCard.Bank.BNI)   // your acquirer bank for Credit Card
    .setFraudSector("fraud sector")         // merchant fraud sector (applicable only for several merchant) 
    .createCreditCard(); 

vtDirectChargeRequest.setCreditCard(creditCard);

VtResponse vtResponse = vtDirect.charge(vtDirectChargeRequest);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.CAPTURED &&
    vtResponse.getFraudStatus() == FraudStatus.ACCEPTED) {

    // handle successful capture
} else if (vtResponse.getStatusCode().equals("201") &&
    vtResponse.getTransactionStatus() == TransactionStatus.CAPTURED &&
    vtResponse.getFraudStatus() == FraudStatus.CHALLENGE) {

    // handle FDS challenge (you can do this later)
} else {
    // handle denied / unexpected response
}
```

<br/>
#### Credit Card Feature
##### Pre-authorization
See [CreditCard Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/paymentmethod/CreditCard.html)  
See [CreditCard Process Flow Without 3D Secure Authentication](sequence_diagram/index.html#credit-card-charging-without-3d-secure-authentication)  
See [CreditCard Process Flow With 3D Secure Authentication](sequence_diagram/index.html#credit-card-charging-with-3d-secure-authentication)  

**Every Credit Card transaction must use a `Token` generated by Veritrans Payment API instead of using plain card number.** Please visit [Acquiring Credit Card Token](http://docs.veritrans.co.id/vtdirect/integration_cc.html#step1) for further information.
```java
CreditCardRequest vtDirectChargeRequest = new CreditCardRequest();
setVtDirectChargeRequestValues(vtDirectChargeRequest);

CreditCard creditCard = new CreditCardBuilder()
    .setTokenId("creditCardToken")                  // token is obtained from HTTP POST variable.
    .setAcquirerBank(CreditCard.Bank.BNI)           // your acquirer bank for Credit Card
    .setType(CreditCard.TransactionType.AUTHORIZE)  // pre-authorization transaction type
    .createCreditCard(); 
vtDirectChargeRequest.setCreditCard(creditCard);

VtResponse vtResponseAuthorize = vtDirect.charge(vtDirectChargeRequest);

if (vtResponseAuthorize.getStatusCode().equals("200") &&
    vtResponseAuthorize.getTransactionStatus() == TransactionStatus.AUTHORIZED &&
    vtResponseAuthorize.getFraudStatus() == FraudStatus.ACCEPTED) {

    // handle successful capture
} else if (vtResponseAuthorize.getStatusCode().equals("201") &&
    vtResponseAuthorize.getTransactionStatus() == TransactionStatus.AUTHORIZED &&
    vtResponseAuthorize.getFraudStatus() == FraudStatus.CHALLENGE) {

    // handle FDS challenge (you can do this later)
} else {
    // handle denied / unexpected response
}
```

##### Capture
Authorized Credit Card charge request can be captured eventhough the FDS status is `CHALLENGE`, however the transaction won't be settled unless the transaction is approved using the [Credit Card: Accept an FDS challenge capture](#credit-card-accept-an-fds-challenge-capture) feature.
```java
String transactionId = vtResponseAuthorize.getTransactionId();
Long captureAmount = vtResponseAuthorize.getGrossAmount().longValue();
VtResponse vtResponseCapture = vtDirect.capture(transactionId, captureAmount);

if (vtResponseCapture.getStatusCode().equals("200") &&
    vtResponseCapture.getTransactionStatus() == TransactionStatus.CAPTURED) {

    // handle successful capture
} else {
    // handle denied / unexpected response
}
```

##### One-time Tokenization 
See [CreditCard Process Flow With One-time Tokenization](sequence_diagram/index.html#credit-card-charging-with-one-time-tokenization)  
See [Veritrans One Click Documentation](http://docs.veritrans.co.id/en/vtdirect/other_features.html#one-click)

One-time tokenization feature allows you to use one token on several transactions. The token represents the credit card number, expiry date, CVV, and 3D Secure authentication of the customer. This will make transactions easier since the credit card information needs to be filled only once and not recurrently.  
<br/>
Do note that token will be activated only after the initial transaction is performed. To use the one click feature, 3D Secure feature has to be used.

##### Initial transaction
```java
CreditCardRequest vtDirectChargeRequest = new CreditCardRequest();
setVtDirectChargeRequestValues(vtDirectChargeRequest);

CreditCard creditCard = new CreditCardBuilder()
    .setTokenId("creditCardToken")          // token is obtained from HTTP POST variable.
    .setAcquirerBank(CreditCard.Bank.BNI)   // your acquirer bank for Credit Card
    .setSaveCardToken(true)                 // Set this flag true for one time tokenization
    .createCreditCard(); 

vtDirectChargeRequest.setCreditCard(creditCard);
VtResponse vtResponse = vtDirect.charge(vtDirectChargeRequest);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.CAPTURED &&
    vtResponse.getFraudStatus() == FraudStatus.ACCEPTED) {
        // you need to save this token for next recurring transaction
        String oneTimeToken = vtResponse.getSavedCardToken();

} else if (vtResponse.getStatusCode().equals("201") &&
    vtResponse.getTransactionStatus() == TransactionStatus.CAPTURED &&
    vtResponse.getFraudStatus() == FraudStatus.CHALLENGE) {

    // handle FDS challenge (you can do this later)
} else {
    // handle denied / unexpected response
}
```

##### Following transaction
```java
CreditCardRequest vtDirectChargeRequest = new CreditCardRequest();
setVtDirectChargeRequestValues(vtDirectChargeRequest);

CreditCard creditCard = new CreditCardBuilder()
    .setTokenId("savedCardToken")           // using saved card token from initial transaction
    .setAcquirerBank(CreditCard.Bank.BNI)   // your acquirer bank for Credit Card
    .createCreditCard(); 

vtDirectChargeRequest.setCreditCard(creditCard);
VtResponse vtResponse = vtDirect.charge(vtDirectChargeRequest);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.CAPTURED &&
    vtResponse.getFraudStatus() == FraudStatus.ACCEPTED) {
        
        // handle successful transaction
} else if (vtResponse.getStatusCode().equals("201") &&
    vtResponse.getTransactionStatus() == TransactionStatus.CAPTURED &&
    vtResponse.getFraudStatus() == FraudStatus.CHALLENGE) {

    // handle FDS challenge (you can do this later)
} else {
    // handle denied / unexpected response
}
```

##### Charging Full PAN ***`Coming soon`***
***`Only allowed for certain merchant`***  
For PCIDSS compliance merchant, it will be able to charge credit card transaction using customer credit card data instead of using token.

<br/>
#### Mandiri Clickpay
See [MandiriClickpay Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/paymentmethod/MandiriClickpay.html)  
Visit [Veritrans Mandiri Clickpay Documentation](http://docs.veritrans.co.id/sandbox/charge.html#vtdirect-mandiri) for more information.
```java
MandiriClickpayRequest vtDirectChargeRequest = new MandiriClickpayRequest();
setVtDirectChargeRequestValues(vtDirectChargeRequest);
vtDirectChargeRequest.setMandiriClickpay(new MandiriClickpay());

vtDirectChargeRequest.getMandiriClickpay().setCardNumber("4111111111111111");
vtDirectChargeRequest.getMandiriClickpay().setInput1("111111111");
vtDirectChargeRequest.getMandiriClickpay().setInput2("10000");
vtDirectChargeRequest.getMandiriClickpay().setInput3("54321");
vtDirectChargeRequest.getMandiriClickpay().setToken("000000");

VtResponse vtResponse = vtDirect.charge(vtDirectChargeRequest);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.SETTLED) {

    // handle successful Mandiri Clickpay charge request
} else {
    // handle denied / unexpected response
}
```

<br/>
#### BCA KlikPay
See [BcaKlikpay Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/paymentmethod/BcaKlikpay.html)  
```java
BcaKlikpayRequest vtDirectChargeRequest = new BcaKlikpayRequest();
setVtDirectChargeRequestValues(vtDirectChargeRequest);
vtDirectChargeRequest.setBcaKlikpay(new BcaKlikpay());

vtDirectChargeRequest.getBcaKlikpay().setType(1);
vtDirectChargeRequest.getBcaKlikpay().setMiscFee(10000L);
vtDirectChargeRequest.getBcaKlikpay().setDescription("Product X");

VtResponse vtResponse = vtDirect.charge(vtDirectChargeRequest);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.PENDING) {

    // handle successful  BCA Klikpay charge request
    // check the redirectUrl value in the vtResponse (redirect the customer to the redirectUrl to continue the payment process)
} else {
    // handle denied / unexpected response
}
```

<br/>
#### Klik BCA
See [Klik BCA Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/model/vtdirect/paymentmethod/KlikBca.html)  
```java
KlikBcaRequest vtDirectChargeRequest = new KlikBcaRequest();
setVtDirectChargeRequestValues(vtDirectChargeRequest);
vtDirectChargeRequest.setKlikBca(new KlikBca());

vtDirectChargeRequest.getKlikBca().setUserId("user ID");
vtDirectChargeRequest.getKlikBca().setDescription("Transaction description");

VtResponse vtResponse = vtDirect.charge(vtDirectChargeRequest);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.PENDING) {

    // handle successful  BCA Klikpay charge request
    // show instruction to customer on how to pay with klikbca
} else {
    // handle denied / unexpected response
}
```

<br/>
## VtWeb
VtWeb is an interface class, where it's instance can be used to communicate with Veritrans Payment API, but **VtWeb `charge` functionality will be responded with redirect url** which is used to redirect customers to Veritrans's Payment Page. VtWeb instance is safe to share with multiple threads, hence you can safely cache the instance of this class and reuse it multiple times.  

See [VtWeb Javadoc](javadoc/id/co/veritrans/mdk/v1/gateway/VtWeb.html).  
Example code to obtain reference to VtWeb instance:
```java
VtWeb vtWeb = vtGatewayFactory.vtWeb();
```

<br/>
### Charging a transaction
```java
VtWebChargeRequest vtWebChargeRequest = new VtWebChargeRequest();
setVtWebChargeRequestValues(vtWebChargeRequest);
vtWebChargeRequest.setVtWeb(new VtWebParam());
vtWebChargeRequest.getVtWeb().setCreditCardUse3dSecure(true);

VtResponse vtResponse = vtWeb.charge(vtWebChargeRequest);
if (vtResponse.getStatusCode().equals("201")) {
    // Redirect user to redirecturl param [vtResponse.getRedirectUrl()]
} else {
    // Handle denied / unexpected response
}
```

### Set enabled payment method
You can specify manually on every request which payment method that want to be enabled on transaction.
```java
VtWebChargeRequest vtWebChargeRequest = new VtWebChargeRequest();
setVtWebChargeRequestValues(vtWebChargeRequest);
vtWebChargeRequest.setVtWeb(new VtWebParam());
vtWebChargeRequest.getVtWeb().setEnabledPayments(new VtWebParam.PaymentMethod[]{VtWebParam.PaymentMethod.CREDIT_CARD, VtWebParam.PaymentMethod.CIMB_CLICKS});
```

### Handle VtWeb URL parameter response
After customer finishes transaction on vtWeb page, it will be redirected to merchant web page based on redirect url config that already setup before. Veritrans will response with several url parameter that can be handle by merchant :

- order_id
- status_code
- transaction_status
```
<!-- Success transaction example -->
http://www.example.com/finish?order_id=asf1434355961&status_code=200&transaction_status=capture

<!-- Deny transaction example -->
http://www.example.com/deny?order_id=asf1434356038&status_code=202&transaction_status=deny

<!-- Pending transaction example -->
http://www.example.com/pending?order_id=asf1434356096&status_code=201&transaction_status=pending
```

<br />
## Other Features

### Check Transaction Status
#### VtDirect
```java
String orderId = "your unique order ID";
VtResponse vtResponse = vtDirect.status(orderId);
// continue processing based on the vtResponse
```

#### VtWeb
```java
String orderId = "your unique order ID";
VtResponse vtResponse = vtWeb.status(orderId);
// continue processing based on the vtResponse
```

### Check Multiple Transaction Status
```java
ArrayList<String> listOrderIds = new ArrayList<String>();
listOrderIds.add(orderId1);
listOrderIds.add(orderId2);

// Build status request from list of order ID
StatusRequest statusRequest = new StatusRequestBuilder()
    .setOrderIds(listOrderIds)
    .setPage(1)
    .setRowPerPage(10)
    .createStatusRequest();

VtResponse vtResponse = vtDirect.status(statusRequest);
```

### Querying Transaction Status
```java
// Build get status query parameter
final GetStatusParameter parameter = new GetStatusParameterBuilder()
    .setFraudStatus(FraudStatus.CHALLENGE)
    .setTransactionStatus(TransactionStatus.AUTHORIZED)
    .setPaymentMethod(PaymentMethod.CREDIT_CARD)
    .setPage(2)
    .setRowPerPage(10)
    .createGetStatusParameter();

VtResponse response = vtDirect.queryStatus(parameter);
```

<br/>
### Credit Card: Accept an `FDS challenge` capture
#### VtDirect
```java
String orderId = "your unique order ID";
VtResponse vtResponse = vtDirect.approve(orderId);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.CAPTURED) {

    // handle successful capture approval
} else {
    // handle denied / unexpected response
}
```
#### VtWeb
```java
String orderId = "your unique order ID";
VtResponse vtResponse = vtWeb.approve(orderId);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.CAPTURED) {

    // handle successful capture approval
} else {
    // handle denied / unexpected response
}
```

<br/>
### Cancel Transaction
#### VtDirect
```java
String orderId = "your unique order ID";
VtResponse vtResponse = vtDirect.cancel(orderId);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.CANCELLED) {

    // handle successful transaction cancel
} else {
    // handle denied / unexpected response
}
```
#### VtWeb
```java
String orderId = "your unique order ID";
VtResponse vtResponse = vtWeb.cancel(orderId);

if (vtResponse.getStatusCode().equals("200") &&
    vtResponse.getTransactionStatus() == TransactionStatus.CANCELLED) {

    // handle successful transaction cancel
} else {
    // handle denied / unexpected response
}
```

<br/>
## Status Code (Response)
Status Codes used by Veritrans API are categorized into 2xx, 3xx, 4xx dan 5xx. You can check detail of all status code that responded from transaction request in ***[here](http://docs.veritrans.co.id/en/api/status_code.html)***

<br/>
## Notification Handler (Notification URL)
Notification URL is used by Veritrans Payment API to notify a merchant once a payment process has been completed or failed.
It is invoked by Veritrans Payment API through HTTP POST by sending a JSON Message in the request body.
The structure of the JSON Message is identical with the JSON Response when invoking the Veritrans Payment API.  
  
There is a static method provided by VtResponse to help you deserializing the JSON Message into a VtResponse instance.
This method accepts `JSON String` or a `Raw Input Stream`.
**Do remember that it is still the caller responsibility to close the InputStream**.

Below is an example code to handle Notification URL using Java Servlet API:
```java
...
void doPost(HttpServletRequest req, HttpServletResponse resp) {
    try {
        ServletInputStream inputStream = req.getInputStream();
        VtResponse vtResponse = VtResponse.deserializeJson(inputStream);

        // if necessary, we can utilize VtDirect's or VtWeb's Check Transaction Status Feature to make sure the notification is really coming from Veritrans
        String orderId = vtResponse.getOrderId();
        vtResponse = vtDirect.status(orderId); // we used VtDirect in this example, however we can use VtWeb too

        if (vtResponse.getTransactionStatus() == TransactionStatus.SETTLED) {
            // handle settled / successful charge request
        } else {
            // handle denied / unexpected response
        }
    } catch (JsonDeserializeException e) {
        // handle JSON deserialization error
        ...
    } finally {
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
...
```
