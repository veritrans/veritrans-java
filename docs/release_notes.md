# Release Notes

## Release 1.0.9
### 7 October 2015
- **[Bug fix]** Add custom field parameter to handle notification response

## Release 1.0.8
### 22 September 2015
- **[Bug fix]** Remove all validation to avoid library conflict

## Release 1.0.7
### 16 September 2015
- **[Bug fix]** Downgrade javax.el-api for support jdk 1.6 


## Release 1.0.6
### 27 July 2015
- **[Feature]** Add full PAN charging feature for PCIDSS compliance merchant


## Release 1.0.5
### 25 June 2015
- **[Bug fix]** Fix unclosed http client connection issue 


## Release 1.0.4
### 25 June 2015
- **[Feature]** Implement http client connect timeout and socket timeout


## Release 1.0.3
### 18 June 2015
- **[Bug fix]** Fix missing transaction status (TransactionStatus.EXPIRED)
- **[Bug fix]** Fix get status issue for credit card get multiple order id status
- **[Feature]** Add fat jar for single jar download integration
- **[Feature]** Add fraud sector parameter for credit card transaction
- **[Feature]** Add fat jar for single jar download integration
- **[Feature]** Add get transaction status query feature


## Release 1.0.2
### 16 June 2015
- **[Feature]** Added get multiple order status feature


## Release 1.0.1
### 15 June 2015
- **[Bug fix]** Fix VtWeb parameter issue


## Release 1.0.0
### 21 May 2015
Initial veritrans java-client release and sample store.  

- **[Feature]** Integrate veritrans java-client through `maven` or `gradle` dependency
- **[Feature]** Veritrans java-client config setup
- **[Feature]** Support for VtDirect credit card transaction
- **[Feature]** Support for VtDirect bank transfer transaction
- **[Feature]** Support for VtDirect bri epay transaction
- **[Feature]** Support for VtDirect cimb clicks transaction
- **[Feature]** Support for VtDirect mandiri clickpay transaction
- **[Feature]** Support for VtDirect bca klikpay transaction
- **[Feature]** Support for VtDirect klikbca transaction
- **[Feature]** Support for VtWeb transaction
- **[Feature]** Get transaction status
- **[Feature]** Accept challenged transaction
- **[Feature]** Cancel authorized transaction
