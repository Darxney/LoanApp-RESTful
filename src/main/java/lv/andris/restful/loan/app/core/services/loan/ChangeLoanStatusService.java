package lv.andris.restful.loan.app.core.services.loan;

import lv.andris.restful.loan.app.core.database.JpaLoanRepository;
import lv.andris.restful.loan.app.core.requests.loan.ChangeLoanStatusRequest;
import lv.andris.restful.loan.app.core.responses.loan.ChangeLoanStatusResponse;
import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.services.loan.validators.ChangeLoanStatusValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ChangeLoanStatusService {

    @Autowired
    private JpaLoanRepository repository;
    @Autowired
    private ChangeLoanStatusValidator validator;


    public ChangeLoanStatusResponse execute(ChangeLoanStatusRequest request) {
        List<CoreError> errors = validator.validate(request);

        if(!errors.isEmpty()) {
            return new ChangeLoanStatusResponse(errors);
        }

        return new ChangeLoanStatusResponse(repository.changeLoanStatus(request.getLoan_id(), request.getIs_approved()));
    }




}
