package id.co.veritrans.mdk.gateway.model;

import id.co.veritrans.mdk.gateway.model.paymentmethod.CreditCard;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by gde on 5/5/15.
 */
public class VtDirectChargeParamCreditCard extends VtDirectChargeParam {

    @Valid
    @NotNull
    private CreditCard creditCard;

    public VtDirectChargeParamCreditCard() {
    }

    public VtDirectChargeParamCreditCard(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails, final CreditCard creditCard) {
        super(transactionDetails, transactionItems, customerDetails);
        this.creditCard = creditCard;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.CREDIT_CARD;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(final CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final VtDirectChargeParamCreditCard that = (VtDirectChargeParamCreditCard) o;

        if (creditCard != null ? !creditCard.equals(that.creditCard) : that.creditCard != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (creditCard != null ? creditCard.hashCode() : 0);
        return result;
    }
}
