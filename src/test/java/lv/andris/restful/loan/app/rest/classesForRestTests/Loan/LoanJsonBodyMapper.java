package lv.andris.restful.loan.app.rest.classesForRestTests.Loan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lv.andris.restful.loan.app.core.domain.Loan;
import lv.andris.restful.loan.app.core.responses.CoreError;


@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanJsonBodyMapper {

    private CoreError[] errors;
    private Loan loan;

    public CoreError[] getErrors() {
        return errors;
    }

    public void setErrors(CoreError[] errors) {
        this.errors = errors;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "errors='" + errors + '\'' +
                ", loan=" + loan +
                '}';
    }
}
