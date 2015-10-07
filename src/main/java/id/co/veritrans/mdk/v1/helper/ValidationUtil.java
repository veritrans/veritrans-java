package id.co.veritrans.mdk.v1.helper;

import javax.validation.*;
import java.util.Set;

/**
 * Created by andes on 5/7/15.
 */
public class ValidationUtil {

    public static void validateThrowException(final Validator validator, Object target) {
        final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(buildExceptionMessage(constraintViolations.toArray(new ConstraintViolation[constraintViolations.size()])), constraintViolations);
        }
    }

    public static String buildExceptionMessage(final ConstraintViolation[] violations) {
        final StringBuilder sb = new StringBuilder();

        for (final ConstraintViolation violation : violations) {
            sb.append(getConstraintViolationMessage(violation)).append("\n");
        }
        return sb.toString();
    }

    private static String getConstraintViolationMessage(final ConstraintViolation violation) {
        return violation.getPropertyPath().toString() + ": " + violation.getMessage();
    }
}
