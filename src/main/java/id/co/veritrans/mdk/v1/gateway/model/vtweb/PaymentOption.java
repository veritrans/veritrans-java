package id.co.veritrans.mdk.v1.gateway.model.vtweb;

import id.co.veritrans.mdk.v1.gateway.model.vtweb.paymentoption.Installment;

/**
 * Payment option parameter can be customized by configuring an instance of this class and provide it to the
 * {@link id.co.veritrans.mdk.v1.gateway.model.vtweb.VtWebParam} instance.
 *
 * Created by gde on 5/20/15.
 */
public class PaymentOption {

    /**
     * The installment parameter.
     */
    private Installment installment;

    public PaymentOption() {
    }

    public PaymentOption(final Installment installment) {
        this.installment = installment;
    }

    public Installment getInstallment() {
        return installment;
    }

    public void setInstallment(final Installment installment) {
        this.installment = installment;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final PaymentOption that = (PaymentOption) o;

        if (installment != null ? !installment.equals(that.installment) : that.installment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return installment != null ? installment.hashCode() : 0;
    }
}
