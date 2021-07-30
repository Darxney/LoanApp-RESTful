package lv.andris.restful.loan.app.rest.classesForRestTests.Loan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanUpdateJsonMapper {
    //Meant to be used with givenLoanService_whenPutExistingEntity_thenItIsUpdated() Test

    String errors;
    int approvedStatusChanged;

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public int getApprovedStatusChanged() {
        return approvedStatusChanged;
    }

    public void setApprovedStatusChanged(int approvedStatusChanged) {
        this.approvedStatusChanged = approvedStatusChanged;
    }

    @Override
    public String toString() {
        return "LoanUpdateJsonMapper{" +
                "errors='" + errors + '\'' +
                ", approvedStatusChanged=" + approvedStatusChanged +
                '}';
    }
}
