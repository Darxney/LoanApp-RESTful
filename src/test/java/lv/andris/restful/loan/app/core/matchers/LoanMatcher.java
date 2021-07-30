package lv.andris.restful.loan.app.core.matchers;

import lv.andris.restful.loan.app.core.domain.Loan;
import org.mockito.ArgumentMatcher;

import java.math.BigDecimal;
import java.sql.Date;

public class LoanMatcher implements ArgumentMatcher<Loan> {

    private BigDecimal loan_amount;
    private Date loan_term;
    private String first_name;
    private String last_name;
    private String personal_id;

    public LoanMatcher(BigDecimal loan_amount, Date loan_term, String first_name, String last_name, String personal_id) {
        this.loan_amount = loan_amount;
        this.loan_term = loan_term;
        this.first_name = first_name;
        this.last_name = last_name;
        this.personal_id = personal_id;
    }

    @Override
    public boolean matches(Loan loan) {
        return loan.getLoan_amount().equals(loan_amount)
                && loan.getLoan_term().equals(loan_term)
                && loan.getFirst_name().equals(first_name)
                && loan.getLast_name().equals(last_name)
                && loan.getPersonal_id().equals(personal_id);
    }
}
