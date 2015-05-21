package id.co.veritrans.mdk.v1.gateway.model.vtweb.paymentoption;

import java.util.List;

/**
 * Installment option can be configured using an instance of this class and provided to the
 * {@link id.co.veritrans.mdk.v1.gateway.model.vtweb.PaymentOption} instance.
 * <p/>
 * Created by gde on 5/20/15.
 */
public class Installment {

    private Boolean required;
    private Term installmentTerms;

    public Installment() {
    }

    public Installment(final Boolean required) {
        this.required = required;
    }

    public Boolean isRequired() {
        return required;
    }

    public void setRequired(final Boolean required) {
        this.required = required;
    }

    public Term getInstallmentTerms() {
        return installmentTerms;
    }

    public void setInstallmentTerms(final Term installmentTerms) {
        this.installmentTerms = installmentTerms;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Installment that = (Installment) o;

        if (installmentTerms != null ? !installmentTerms.equals(that.installmentTerms) : that.installmentTerms != null)
            return false;
        if (required != null ? !required.equals(that.required) : that.required != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = required != null ? required.hashCode() : 0;
        result = 31 * result + (installmentTerms != null ? installmentTerms.hashCode() : 0);
        return result;
    }

    /**
     * The {@link id.co.veritrans.mdk.v1.gateway.model.vtweb.paymentoption.Installment} term parameter for each bank.
     */
    public static class Term {

        /**
         * List of installment term for BNI Bank.
         */
        private List<Integer> bni;
        /**
         * List of installment term for Mandiri Bank.
         */
        private List<Integer> mandiri;

        public Term() {
        }

        public Term(final List<Integer> bni, final List<Integer> mandiri) {
            this.bni = bni;
            this.mandiri = mandiri;
        }

        public List<Integer> getBni() {
            return bni;
        }

        public void setBni(final List<Integer> bni) {
            this.bni = bni;
        }

        public List<Integer> getMandiri() {
            return mandiri;
        }

        public void setMandiri(final List<Integer> mandiri) {
            this.mandiri = mandiri;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final Term term = (Term) o;

            if (bni != null ? !bni.equals(term.bni) : term.bni != null) return false;
            if (mandiri != null ? !mandiri.equals(term.mandiri) : term.mandiri != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = bni != null ? bni.hashCode() : 0;
            result = 31 * result + (mandiri != null ? mandiri.hashCode() : 0);
            return result;
        }
    }
}
