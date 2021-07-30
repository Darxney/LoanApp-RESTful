package lv.andris.restful.loan.app.core.services.loan.validators;

import lv.andris.restful.loan.app.core.requests.loan.ChangeLoanStatusRequest;
import lv.andris.restful.loan.app.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ChangeLoanStatusValidator {

    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(ChangeLoanStatusRequest request) {
        errors.clear();
        validateLoanId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateLoanId(ChangeLoanStatusRequest request) {
        return (request.getLoan_id() == null)
                 ? Optional.of(new CoreError("Loan Id", "Must not be empty"))
                 : Optional.empty();
    }
}
