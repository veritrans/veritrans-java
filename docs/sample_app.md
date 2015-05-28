# Sample Store Application
Veritrans java-client sample store application
***

## How to build and run

You can get sample store source code from [Veritrans Java Client](https://github.com/gde-vt/veritrans-java/) repository on `~/veritrans-java/sample` directory.  
This sample store application use [***Spring Boot Framework***](http://projects.spring.io/spring-boot/)
<br />

Veritrans sample store use gradle build tools for compile, build, and running the application.  
Before build and run sample store application, you need to setup several config first such as merchant `server-key` and `client-key`
which can be obtained from [Veritrans Merchant Admin Portal](https://my.sandbox.veritrans.co.id/login).  
<br/>

You need to setup config on these file:  

* `~/veritrans-java/sample/src/main/resources/config/application.yml` -> `server-key` and `client-key`  
* `~/veritrans-java/sample/src/main/resources/static/assets/vtdirect.js` -> `client-key`  
<br/>

To run the apps, you can just simply run this command on sample store project directory `~/veritrans-java/sample` :
```shell
./gradlew bootRun
```

or if you have gradle installed on you machine, you can use this command :
```shell
gradle bootRun
```

After gradle task build and run the apps, you can go to [http://localhost:8080/index](http://localhost:8080/index) on your browser and try out the sample store application. On the sample store, you can try to make a transaction. After that you can check your transaction status on [Veritrans Merchant Admin Portal](https://my.sandbox.veritrans.co.id/login).  

***
## Veritrans java client usage
Veritrans java-client library usage example can be shown on `VtPaymentManager.java` class. `VtPaymentManager.java` class is spring singleton beans which is use to hold `VtGatewayFactory` parameter so you can get the factory easily. You need to construct `VtGatewayFactory` object using several merchant config. In sample store case, merchant config was store on `application.yml` and loaded by `VtPaymentConfig.java` class.  
<br />

***`VtPaymentManager.java` - VtGatewayFactory example:***
```java
@PostConstruct
public void setup() {
    vtGatewayFactory = new VtGatewayFactory(new VtGatewayConfigBuilder()
            .setClientKey(vtPaymentConfig.getClientKey())
            .setServerKey(vtPaymentConfig.getServerKey())
            .setEnvironmentType(EnvironmentType.valueOf(vtPaymentConfig.getEnvironment()))
            .setMaxConnectionPoolSize(vtPaymentConfig.getConnectionPoolSize())
            .createVtGatewayConfig()
    );
}
```
<br />

After that, you can get `VtDirect` object which will be use to trigger the transaction. Before charge a transaction, you need to construct request message that will be used as `VtDirect` charge parameter. For example, sample store application use `CreditCardRequest` as charge parameter.
> an **`important note for credit card transaction`**, you need to convert **`customer card credentials`** into **`token`** first before you can charge the transaction. This part will be explained more detail on [javascript part](#veritrans-javascript-credit-card) below
<br />

***`AbstractVtDirectController.java` - VtDirect example:***
```java
private VtDirect vtDirect;

@PostConstruct
public void setup() {
    vtDirect = vtPaymentManager.getVtGatewayFactory().vtDirect();
}
```

<br />
***`CreditCardController.java` - CreditCardRequest and charging example:***
```java
public ModelAndView checkoutCreditCardPost(/* Parameter */) {

    /* Construct creit card request */
    final CreditCardRequest request = createCreditCardRequest(/* Parameter */);

    /* Charge transaction using credit card request */
    try {
        final VtResponse vtResponse = vtDirect.charge(creditCardRequest);
        // ...
        if (vtResponse.getStatusCode().equals("200")) {
            // ...
        }
    } catch (RestClientException e) {
        // ...
    }
}
```
<br />

When you call `vtDirect.charge(creditCardRequest)` method, it will send request to Veritrans Payment API and return the response into `VtResponse` object. You can check whether transaction was success or not from `VtResponse` object.
> For transaction with `fraudStatus` is `challenge`, you need to `approve` the transaction manually using method call `vtDirect.approve(order_id)`. If you not approve challenged transaction until settlement time, transaction will be canceled automatically by system.

<br />
### Veritrans javascript (credit card)
Credit card transaction have special handling compare than another payment type. For security reason, merchant `will not recieve any customer card credentials` and will be replaced with `veritrans card token`.
<br />

Veritrans card token can be obtained using javascript library that you need to setup on merchant web page. You can see detail overview about this javascript library on [Veritrans credit card documentation](http://docs.veritrans.co.id/en/vtdirect/integration_cc.html). For example on sample store application, there is two javascript file need to setup on merchant web page (see `layout.html` on `footer-vtdirect-creditcard` part) :

1. `https://api.sandbox.veritrans.co.id/v2/assets/js/veritrans.min.js`
2. `/assets/vtdirect.js`

The first file was used to trigger `get_token` transaction request to veritrans payment api. This request will translate customer card credentials into veritrans token that can be use to charge a credit card transaction.
<br />
