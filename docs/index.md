# Veritrans-Java Client Documentation
This is the official Java Client for Veritrans Payment API.  

Please visit [https://www.veritrans.co.id](https://www.veritrans.co.id) for more information about the products and see the documentations at [http://docs.veritrans.co.id](http://docs.veritrans.co.id) for more technical details.

<br/>
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

<br/>
# Usage

## VtGatewayConfig
VtGatewayConfig stores the settings that is needed by the Veritrans-Java Client to properly accessing the Payment API.  
See [VtGatewayConfig Javadoc](javadoc/id/co/veritrans/mdk/VtGatewayConfig.html).

### Server & Client Key
You must set the `Server Key` and `Client Key` as defined in your Merchant Admministration Portal (MAP).
```java
VtGatewayConfig vtGatewayConfig = new VtGatewayConfig();
vtGatewayConfig.setServerKey("Your Server Key");
vtGatewayConfig.setServerKey("Your Client Key");
```

### Environment
You can choose to use Sandbox or Production environment. During development & testing, you might want to set it to Sandbox and use the Production environment when you're ready to go live.  

See [EnvironmentType Javadoc](javadoc/id/co/veritrans/mdk/config/EnvironmentType.html).
```java
vtGatewayConfig.setEnvironment(EnvironmentType.Production);
```

<br/>
## VtDirect
VtDirect instance is used to communicate with Veritrans Payment API. VtDirect instance is safe to share with multiple threads and it is recommended to maintain and reuse the same VtDirect instance.
However if you need to use a different configuration, you may obtain another VtDirect instance for that configuration.  
See [VtDirect Javadoc](javadoc/id/co/veritrans/mdk/gateway/VtDirect.html).

### Obtaining VtDirect instance
VtDirect instance is obtained through the [VtGatewayFactory](javadoc/id/co/veritrans/mdk/VtGatewayFactory.html) by giving the proper VtGatewayConfig object.  
There are multiple ways to obtain VtDirect instance through VtGatewayFactory described in the VtGatewayFactory Javadoc, however this is the recommended way:
```java
VtDirect vtDirect = VtGatewayFactory.vtDirect(vtGatewayConfig);
```

<br/>
### Charging a transaction
VtDirect has method named `charge` which takes an instance of VtDirectChargeParam subclass as the parameter.
This method will make a charge request to Veritrans Payment API and return a VtDirectResponse as a result, which can be used to determine the status of the transaction.  

See [VtDirectChargeParam](javadoc/id/co/veritrans/mdk/gateway/model/VtDirectChargeParam.html)  
See [VtDirectResponse](javadoc/id/co/veritrans/mdk/gateway/model/VtDirectResponse.html)  
Visit [http://docs.veritrans.co.id/sandbox/charge.html](http://docs.veritrans.co.id/sandbox/charge.html) for more information.

<br/>
#### VtDirectChargeParam
VtDirectChargeParam has specific subclass for a specific payment method, ex: for Credit Card payment method, there is a subclass named VtDirectChargeParamCreditCard. The list of currently supported payment methods:  
- [VtDirectChargeParamBankTransfer](javadoc/id/co/veritrans/mdk/gateway/model/VtDirectChargeParamBankTransfer.html)  
- [VtDirectChargeParamCimbClicks](javadoc/id/co/veritrans/mdk/gateway/model/VtDirectChargeParamCimbClicks.html)  
- [VtDirectChargeParamCreditCard](javadoc/id/co/veritrans/mdk/gateway/model/VtDirectChargeParamCreditCard.html)  
- [VtDirectChargeParamMandiriClickpay](javadoc/id/co/veritrans/mdk/gateway/model/VtDirectChargeParamMandiriClickpay.html)  
  
See [id.co.veritrans.mdk.gateway.model Javadoc](javadoc/id/co/veritrans/mdk/gateway/model/package-summary.html)

It is recommended to have a single method to configure the generic VtDirectChargeParam values. Example for Credit Card charge:
```java
...
//somewhere in the code
VtDirectChargeParamCreditCard vtDirectChargeParam = new VtDirectChargeParamCreditCard();
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

    vtDirectChargeParam.getCreditCard().setTokenId("creditCardToken");
    vtDirectChargeParam.getCreditCard().setAcquirerBank(Bank.BNI);
}
```

<br/>
#### Bank Transfer
See [BankTransfer Javadoc](javadoc/id/co/veritrans/mdk/gateway/model/paymentmethod/BankTransfer.html)
```java
VtDirectChargeParamBankTransfer vtDirectChargeParam = new VtDirectChargeParamBankTransfer();
setVtDirectChargeParamValues(vtDirectChargeParam);
vtDirectChargeParam.setBankTransfer(new BankTransfer());

vtDirectChargeParam.getBankTransfer().setDescription("Payment for Merchant XYZ");
vtDirectChargeParam.getBankTransfer().setBank(BankTransfer.Bank.PERMATA);

VtDirectResponse vtDirectResponse = vtDirect.charge(vtDirectChargeParam);

if (vtDirectResponse.getTransactionStatus() == TransactionStatus.PENDING) {
    //handle successful Bank Transfer charge request
    //check the permataVaNumber in the vtDirectResponse
} else {
    //handle denied / unexpected response
}
```

<br/>
#### Cimb Clicks
See [CimbClicks Javadoc](javadoc/id/co/veritrans/mdk/gateway/model/paymentmethod/CimbClicks.html)
```java
VtDirectChargeParamCimbClicks vtDirectChargeParam = new VtDirectChargeParamCimbClicks();
setVtDirectChargeParamValues(vtDirectChargeParam);
vtDirectChargeParam.setCimbClicks(new CimbClicks());

vtDirectChargeParam.getCimbClicks().setDescription("Payment for Merchant XYZ");

VtDirectResponse vtDirectResponse = vtDirect.charge(vtDirectChargeParam);

if (vtDirectResponse.getTransactionStatus() == TransactionStatus.PENDING) {
    //handle successful Cimb Clicks charge request
    //check the redirectUrl value in the vtDirectResponse
} else {
    //handle denied / unexpected response
}
```

<br/>
#### Credit Card Sale
See [CreditCard Javadoc](javadoc/id/co/veritrans/mdk/gateway/model/paymentmethod/CreditCard.html)
```
VtDirectChargeParamCreditCard vtDirectChargeParam = new VtDirectChargeParamCreditCard();
setVtDirectChargeParamValues(vtDirectChargeParam);
vtDirectChargeParam.setCreditCard(new CreditCard());

//token is obtained from HTTP POST variable.
vtDirectChargeParam.getCreditCard().setTokenId("creditCardToken");
//your acquirer bank for Credit Card
vtDirectChargeParam.getCreditCard().setAcquirerBank(CreditCard.Bank.BNI);

VtDirectResponse vtDirectResponse = vtDirect.charge(vtDirectChargeParam);

if (vtDirectResponse.getTransactionStatus() == TransactionStatus.CAPTURED) {
    if (vtDirectResponse.getFraudStatus() == FraudStatus.ACCEPTED) {
        //handle successful capture
    } else if (vtDirectResponse.getFraudStatus() == FraudStatus.CHALLENGE) {
        //handle FDS challenge
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
```java
VtDirectChargeParamCreditCard vtDirectChargeParam = new VtDirectChargeParamCreditCard();
setVtDirectChargeParamValues(vtDirectChargeParam);
vtDirectChargeParam.setCreditCard(new CreditCard());

//token is obtained from HTTP POST variable.
vtDirectChargeParam.getCreditCard().setTokenId("creditCardToken");
//your acquirer bank for Credit Card
vtDirectChargeParam.getCreditCard().setAcquirerBank(CreditCard.Bank.BNI);
vtDirectChargeParam.getCreditCard().setType(CreditCard.TransactionType.AUTHORIZE);

VtDirectResponse vtDirectResponseAuthorize = vtDirect.charge(vtDirectChargeParam);

if (vtDirectResponseAuthorize.getTransactionStatus() == TransactionStatus.AUTHORIZED) {
    if (vtDirectResponseAuthorize.getFraudStatus() == FraudStatus.ACCEPTED) {
        //handle successful authorize
    } else if (vtDirectResponseAuthorize.getFraudStatus() == FraudStatus.CHALLENGE) {
        //handle FDS challenge
    } else {
        //unexpected condition that should never happen
    }
} else {
    //handle denied / unexpected response
}
```

##### Capture
```java
String transactionId = vtDirectResponseAuthorize.getTransactionId();
Long captureAmount = vtDirectResponseAuthorize.getGrossAmount().longValue();
VtDirectResponse vtDirectResponseCapture = vtDirect.capture(transactionId, captureAmount);

if (vtDirectResponseCapture.getTransactionStatus() == TransactionStatus.CAPTURED) {
    //handle successful capture
} else {
    //handle denied / unexpected response
}
```

<br/>
#### Credit Card: Accept an FDS challenged capture
```java
String orderId = "your unique order ID";
VtDirectResponse vtDirectResponse = vtDirect.approve(orderId);

if (vtDirectResponse.getTransactionStatus() == TransactionStatus.CAPTURED) {
    //handle successful capture approval
} else {
    //handle denied / unexpected response
}
```

<br/>
#### Mandiri Clickpay
See [MandiriClickpay Javadoc](javadoc/id/co/veritrans/mdk/gateway/model/paymentmethod/MandiriClickpay.html)
```java
VtDirectChargeParamMandiriClickpay vtDirectChargeParam = new VtDirectChargeParamMandiriClickpay();
setVtDirectChargeParamValues(vtDirectChargeParam);
vtDirectChargeParam.setMandiriClickpay(new MandiriClickpay());

vtDirectChargeParam.getMandiriClickpay().setCardNumber("4111111111111111");
vtDirectChargeParam.getMandiriClickpay().setInput1("111111111");
vtDirectChargeParam.getMandiriClickpay().setInput2("10000");
vtDirectChargeParam.getMandiriClickpay().setInput3("54321");
vtDirectChargeParam.getMandiriClickpay().setToken("000000");

VtDirectResponse vtDirectResponse = vtDirect.charge(vtDirectChargeParam);

if (vtDirectResponse.getTransactionStatus() == TransactionStatus.SETTLED) {
    //handle successful Mandiri Clickpay charge request
} else {
    //handle denied / unexpected response
}
```

<br/>
### Check Transaction Status
```java
String orderId = "your unique order ID";
VtDirectResponse vtDirectResponse = vtDirect.status(orderId);
//continue processing based on the vtDirectResponse
```
