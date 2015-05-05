package id.co.veritrans.mdk.gateway.model;

import id.co.veritrans.mdk.gateway.model.paymentmethod.BankTransfer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by gde on 5/5/15.
 */
public class VtDirectChargeParamBankTransfer extends VtDirectChargeParam {

    @Valid
    @NotNull
    private BankTransfer bankTransfer;

    public VtDirectChargeParamBankTransfer() {
    }

    public VtDirectChargeParamBankTransfer(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails, final BankTransfer bankTransfer) {
        super(transactionDetails, transactionItems, customerDetails);
        this.bankTransfer = bankTransfer;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.BANK_TRANSFER;
    }

    public BankTransfer getBankTransfer() {
        return bankTransfer;
    }

    public void setBankTransfer(final BankTransfer bankTransfer) {
        this.bankTransfer = bankTransfer;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final VtDirectChargeParamBankTransfer that = (VtDirectChargeParamBankTransfer) o;

        if (bankTransfer != null ? !bankTransfer.equals(that.bankTransfer) : that.bankTransfer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bankTransfer != null ? bankTransfer.hashCode() : 0);
        return result;
    }
}
