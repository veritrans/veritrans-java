#Process Flow

## Credit Card Charging Without 3D Secure Authentication
![Credit Card Without 3D Secure Authentication](sequence_diagram/credit-card-charging-non-3ds.png)

***
## Credit Card Charging With 3D Secure Authentication
![Credit Card With 3D Secure Authentication](sequence_diagram/credit-card-charging-3ds.png)

***
## Credit Card Charging With One-time Tokenization
#### You need to set `save_card_token` flag to `true` for the first credit card transaction and store the `saved_card_token` parameter for next charging  
![Credit Card With One-time Tokenization 2](sequence_diagram/credit-card-one-click2.png)  
<br/>
#### On next charging, you can directly charge using `saved_card_token` parameter  
![Credit Card With One-time Tokenization 1](sequence_diagram/credit-card-one-click1.png)

***
## Credit Card Charging (Full PAN)
***`Coming soon`***  
<br/>
![Credit Card Charging (Full PAN)](sequence_diagram/credit-card-secure-charge.png)

***
## Bank Transfer via Permata VA
![Bank Transfer via Permata VA](sequence_diagram/bank-transfer.png)

***
## BCA KlikBCA
![KlikBCA Transaction](sequence_diagram/bca-klikbca.png)

***
## BCA Klikpay
![BCA Klikpay Transaction](sequence_diagram/bca-klikpay.png)

***
## Mandiri Clickpay
![Mandiri Clickpay Transaction](sequence_diagram/mandiri-clickpay.png)

***
## BRI Epay
![BRI Epay Transaction](sequence_diagram/bri-epay.png)

***
## CIMB Clicks
![CIMB Clicks Transaction](sequence_diagram/cimb-clicks.png)
