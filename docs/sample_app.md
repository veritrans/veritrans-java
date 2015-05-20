# Sample Store Application
Veritrans java-client sample store application
***

## Source code
<!-- TODO: change repository url -->
You can get sample store source code from [Veritrans Java Client](https://github.com/gde-vt/veritrans-java/) repository on `~/veritrans-java/sample` directory.  
Sample store application using [**Spring Boot Framework**](http://projects.spring.io/spring-boot/)

### Code structure
#### Model
```
src
├── main
│   ├── java
│   │   └── id
│   │       └── co
│   │           └── veritrans
│   │               └── mdk
│   │                   └── v1
│   │                       └── sample
│   │                           ├── db
│   │                           │   ├── model
│   │                           │   │   ├── Product.java
│   │                           │   │   ├── Transaction.java
│   │                           │   │   └── TransactionItem.java
│   │                           │   └── repo
│   │                           │       ├── ProductRepo.java
│   │                           │       ├── TransactionItemRepo.java
│   │                           │       └── TransactionRepo.java
```

This sample store store model data on 3 java class.  
**Work in progress**

#### View
```
src
├── main
│   └── resources
│       └── templates
│           ├── checkout
│           │   ├── choose_payment.html
│           │   ├── credit_card.html
│           │   └── success.html
│           ├── checkout.html
│           ├── index.html
│           └── layout.html
```

Veritrans sample store using [Thymeleaf](http://www.thymeleaf.org/) templating engine for view part.  
**Work in progress**

##### Javascript
**Work in progress**

#### Controller
```
src
├── main
│   ├── java
│   │   └── id
│   │       └── co
│   │           └── veritrans
│   │               └── mdk
│   │                   └── v1
│   │                       └── sample
│   │                           ├── controller
│   │                           │   ├── CartController.java
│   │                           │   ├── CheckoutPageController.java
│   │                           │   └── IndexPageController.java
```

**Work in progress**

***
## How to build and run

Veritrans sample store use gradle build tools for compile, build, and running the application.  
Before build and run sample application, you need to setup several config first such as merchant `server-key` and `client-key`
which can be obtained on [Veritrans Merchant Admin Portal](https://my.sandbox.veritrans.co.id/login).  
<br/>

You need to setup config on these file:  

* `~/veritrans-java/sample/src/main/resources/config/application.yml` -> `server-key` and `client-key`  
* `~/veritrans-java/sample/src/main/resources/static/assets/vtdirect.js` -> `client-key`  
<br/>

To run the apps, you can just simply run this command on sample project directory [`~/veritrans-java/sample`] :
```shell
./gradlew bootRun
```

After gradle task build and run the apps, you can go to [`http://localhost:8080/index`](http://localhost:8080/index) on your browser and tried out the sample store. On the webpage, you can try to make a transaction. After that you can check your transaction status on [Veritrans Merchant Admin Portal](https://my.sandbox.veritrans.co.id/login).  

***
## Veritrans java client
<!-- TODO : show sample usage of veritrans java client -->
**Work in progress**