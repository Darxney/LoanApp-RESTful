package lv.andris.restful.loan.app.core.matchers;

import lv.andris.restful.loan.app.core.domain.Loan;
import org.mockito.ArgumentMatcher;

import java.math.BigDecimal;
import java.sql.Date;

public class LoanMatcher implements ArgumentMatcher<Loan> {

    private BigDecimal loanAmount;
    private Date loanTerm;
    private String firstName;
    private String lastName;
    private String personalId;

    public LoanMatcher(BigDecimal loanAmount, Date loanTerm, String firstName, String lastName, String personalId) {
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
    }

    @Override
    public boolean matches(Loan loan) {
        return loan.getLoanAmount().equals(loanAmount)
                && loan.getLoanTerm().equals(loanTerm)
                && loan.getFirstName().equals(firstName)
                && loan.getLastName().equals(lastName)
                && loan.getPersonalId().equals(personalId);
    }
}
