package lv.andris.restful.loan.app.core.responses.loan;

import lv.andris.restful.loan.app.core.domain.Loan;
import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.responses.CoreResponse;

import java.util.List;

public class SearchLoansResponse extends CoreResponse {

    private List<Loan> loans;

    public SearchLoansResponse(List<CoreError> errors , List<Loan> loans) {
        super(errors);
        this.loans = loans;
    }

    public List<Loan> getLoan() {
        return loans;
    }
}
