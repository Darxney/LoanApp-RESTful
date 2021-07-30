package lv.andris.restful.loan.app.core.responses.loan;

import lv.andris.restful.loan.app.core.domain.Loan;
import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.responses.CoreResponse;

import java.util.List;

public class AddLoanResponse extends CoreResponse {

    private Loan newLoan;

    public AddLoanResponse(List<CoreError> errors) {
        super (errors);
    }

    public AddLoanResponse(Loan newLoan) {
        this.newLoan = newLoan;
    }

    public Loan getLoan() {
        return newLoan;
    }

}
