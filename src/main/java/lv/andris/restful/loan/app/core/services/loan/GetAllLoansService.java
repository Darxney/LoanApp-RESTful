package lv.andris.restful.loan.app.core.services.loan;


import lv.andris.restful.loan.app.core.database.JpaLoanRepository;
import lv.andris.restful.loan.app.core.domain.Loan;
import lv.andris.restful.loan.app.core.requests.loan.GetAllLoansRequest;
import lv.andris.restful.loan.app.core.responses.loan.GetAllLoansResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllLoansService {

    @Autowired
    private JpaLoanRepository repository;

    public GetAllLoansResponse execute(GetAllLoansRequest request) {
        List<Loan> loans = repository.getAllLoans();
        return new GetAllLoansResponse(loans);
    }

}
