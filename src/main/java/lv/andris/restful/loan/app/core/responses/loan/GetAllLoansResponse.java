package lv.andris.restful.loan.app.core.responses.loan;

import lv.andris.restful.loan.app.core.domain.Loan;

import java.util.List;

public class GetAllLoansResponse {

    private List<Loan> loans;

    public GetAllLoansResponse(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Loan> getLoan() {
        return loans;
    }
}
