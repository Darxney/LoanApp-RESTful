package lv.andris.restful.loan.app.core.services.loan;

import lv.andris.restful.loan.app.core.database.JpaLoanRepository;
import lv.andris.restful.loan.app.core.domain.Loan;
import lv.andris.restful.loan.app.core.requests.loan.SearchLoansRequest;
import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.responses.loan.SearchLoansResponse;
import lv.andris.restful.loan.app.core.services.loan.validators.SearchLoansValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class SearchLoansService {

    @Autowired
    private JpaLoanRepository repository;

    @Autowired
    private SearchLoansValidator validator;

    public SearchLoansResponse execute(SearchLoansRequest request) {

        List<CoreError> errors = validate(request);
        if (!errors.isEmpty()) {
            return new SearchLoansResponse(errors, null);
        }

        List<Loan> loans = search(request);
        return new SearchLoansResponse(null ,loans);
    }

    private List<Loan> search(SearchLoansRequest request) {

        List<Loan> loans = new ArrayList<>();

        if(request.isGetPersonal_idProvided()) {
            loans = repository.getApprovedOrUnapprovedLoansByPersonalId(request.getIs_approved(), request.getPersonal_id());
        } else {
            loans = repository.getLoansByApprovedStatus(request.getIs_approved());
        }

        return loans;

    }

    private List<CoreError> validate(SearchLoansRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if(request.isGetPersonal_idProvided()) {
            errors = validator.validate(request);
        }
        return errors;
    }

}
