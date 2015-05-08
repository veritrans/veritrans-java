package id.co.veritrans.mdk.v1.helper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created by andes on 5/7/15.
 */
public class ValidationUtil {

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

    public static Validator getValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }

    private static String getConstraintViolationMessage(Object constraint) {
        if (constraint instanceof ConstraintViolation) {
            ConstraintViolation violation = ((ConstraintViolation) constraint);
            return violation.getPropertyPath().toString() + " " + violation.getMessage();
        }
        return (String) constraint;
    }
}
