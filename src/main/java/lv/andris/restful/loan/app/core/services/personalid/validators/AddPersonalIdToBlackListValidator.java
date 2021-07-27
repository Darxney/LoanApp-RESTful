package lv.andris.restful.loan.app.core.services.personalid.validators;



import lv.andris.restful.loan.app.core.requests.personalid.AddPersonalIdToBlacklistRequest;
import lv.andris.restful.loan.app.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddPersonalIdToBlackListValidator {

    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(AddPersonalIdToBlacklistRequest request) {
        errors.clear();
        validatePersonal_Id(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePersonal_Id(AddPersonalIdToBlacklistRequest request) {
        if (request.getPersonal_id() == null || request.getPersonal_id().isEmpty()) {
            return Optional.of(new CoreError("Personal id", "Must not be empty"));
        }else if(!request.getPersonal_id().matches("\\d\\d\\d\\d\\d\\-\\d\\d\\d\\d\\d\\d") ) {
            return Optional.of(new CoreError("Personal id", "received invalid data"));
        }
        return Optional.empty();
    }
}
