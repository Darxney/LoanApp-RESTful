package lv.andris.restful.loan.app.core.services.loan.validators;

import lv.andris.restful.loan.app.core.database.JpaPersonalIdRepository;
import lv.andris.restful.loan.app.core.requests.loan.AddLoanRequest;
import lv.andris.restful.loan.app.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddLoanValidator {

    @Autowired
    private JpaPersonalIdRepository repository;

    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(AddLoanRequest request) {
        errors.clear();
        validateLoanAmount(request).ifPresent(errors::add);
        validateLoanTerm(request).ifPresent(errors::add);
        validateFirstName(request).ifPresent(errors::add);
        validateLastName(request).ifPresent(errors::add);
        validatePersonalId(request).ifPresent(errors::add);

        return errors;
    }

    private Optional<CoreError> validateLoanAmount(AddLoanRequest request) {
        return (request.getLoan_amount() == null)
                ? Optional.of(new CoreError("Loan Amount", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateLoanTerm(AddLoanRequest request) {
        return (request.getLoan_term() == null)
                ? Optional.of(new CoreError("Loan Term", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateFirstName(AddLoanRequest request) {
        return (request.getFirst_name() == null || request.getFirst_name().isEmpty())
                ? Optional.of(new CoreError("First Name", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateLastName(AddLoanRequest request) {
        return (request.getLast_name() == null || request.getLast_name().isEmpty())
                ? Optional.of(new CoreError("Last Name", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePersonalId(AddLoanRequest request) {
        if (request.getPersonal_id() == null || request.getPersonal_id().isEmpty()) {
                return Optional.of(new CoreError("Personal id", "Must not be empty"));
        }else if(!request.getPersonal_id().matches("\\d\\d\\d\\d\\d\\-\\d\\d\\d\\d\\d\\d") ) {
            return Optional.of(new CoreError("Personal id", "received invalid data"));
        }else if((repository.getBlackList().toString().contains(request.getPersonal_id()))) {
            return Optional.of(new CoreError("Personal id", "This person is blacklisted (Personal id: " + request.getPersonal_id() + ")"));
        }

        return Optional.empty();
    }

}
