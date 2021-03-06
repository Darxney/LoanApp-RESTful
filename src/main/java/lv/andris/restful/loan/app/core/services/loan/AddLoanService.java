package lv.andris.restful.loan.app.core.services.loan;

import lv.andris.restful.loan.app.core.database.JpaLoanRepository;
import lv.andris.restful.loan.app.core.domain.Loan;
import lv.andris.restful.loan.app.core.requests.loan.AddLoanRequest;
import lv.andris.restful.loan.app.core.responses.loan.AddLoanResponse;
import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.services.loan.validators.AddLoanValidator;
import lv.andris.restful.loan.app.core.services.requestlimiter.Validators.RequestLimitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
@Transactional
public class AddLoanService {

    @Autowired
    private JpaLoanRepository repository;

    @Autowired
    private AddLoanValidator validator;

    @Autowired
    private RequestLimitValidator limitValidator;

    public AddLoanResponse execute(AddLoanRequest request, HttpServletRequest servletRequest) {


        List<CoreError> limits = limitValidator.validate(servletRequest);
        List<CoreError> errors = validator.validate(request);

        if(!errors.isEmpty()) {
            return new AddLoanResponse(errors);
        } else if(!limits.isEmpty()) {
            return new AddLoanResponse(limits);
        }

        Loan loan = new Loan(request.getLoan_amount(), request.getLoan_term(), request.getFirst_name(), request.getLast_name(), request.getPersonal_id());
        repository.save(loan);
        System.out.println("New loan has been added to the list");


        return new AddLoanResponse(loan);
    }
}
