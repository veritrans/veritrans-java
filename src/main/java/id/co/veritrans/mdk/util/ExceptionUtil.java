package id.co.veritrans.mdk.util;

import javax.validation.ConstraintViolation;

/**
 * Created by andes on 5/7/15.
 */
public class ExceptionUtil {

    public static String buildExceptionMessage(Object[] arrayConstaraintViolation) {
        StringBuilder errorValidationMessage = new StringBuilder();
        String tmpErrorString = getConstraintViolationMessage(arrayConstaraintViolation[0]);
        errorValidationMessage.append(tmpErrorString);

        for (Object constraint : arrayConstaraintViolation){
            /* Avoid duplicate error message */
            if (!tmpErrorString.equals(getConstraintViolationMessage(constraint))) {
                tmpErrorString = getConstraintViolationMessage(constraint);
                errorValidationMessage
                        .append(", ")
                        .append(getConstraintViolationMessage(tmpErrorString));
            }
        }

        return errorValidationMessage.toString();
    }

    private static String getConstraintViolationMessage(Object constraint) {
        if (constraint instanceof ConstraintViolation) {
            ConstraintViolation violation = ((ConstraintViolation) constraint);
            return violation.getPropertyPath().toString() + " " + violation.getMessage();
        }
        return (String) constraint;
    }

}
