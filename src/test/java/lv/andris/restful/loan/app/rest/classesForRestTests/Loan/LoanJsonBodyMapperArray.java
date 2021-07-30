package lv.andris.restful.loan.app.rest.classesForRestTests.Loan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lv.andris.restful.loan.app.core.domain.Loan;


@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanJsonBodyMapperArray {

    private String errors;
    private Loan[] loan;

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public Loan[] getLoan() {
        return loan;
    }

    public void setLoan(Loan[] loan) {
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