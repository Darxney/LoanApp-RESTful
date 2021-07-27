package lv.andris.restful.loan.app.core.services.loan.validators;

import lv.andris.restful.loan.app.core.requests.loan.SearchLoansRequest;
import lv.andris.restful.loan.app.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchLoansValidator {
    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(SearchLoansRequest request) {
        errors.clear();
        validatePersonalId(request).ifPresent(errors::add);

        return errors;
    }

    private Optional<CoreError> validatePersonalId(SearchLoansRequest request) {
        if (request.getPersonal_id() == null || request.getPersonal_id().isEmpty()) {
            return Optional.of(new CoreError("Personal id", "must not be empty"));
        }else if(!request.getPersonal_id().matches("\\d\\d\\d\\d\\d\\-\\d\\d\\d\\d\\d\\d") ) {
            return Optional.of(new CoreError("Personal id", "received invalid data"));
        }
        return Optional.empty();
    }

}
