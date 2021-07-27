package lv.andris.restful.loan.app.core.requests.loan;

import java.math.BigDecimal;
import java.sql.Date;

public class AddLoanRequest {

    private BigDecimal loan_amount;
    private Date loan_term;
    private String first_name;
    private String last_name;
    private String personal_id;

    public AddLoanRequest() {

    }

    public AddLoanRequest(BigDecimal loan_amount, Date loan_term, String first_name, String last_name, String personal_id) {
        this.loan_amount = loan_amount;
        this.loan_term = loan_term;
        this.first_name = first_name;
        this.last_name = last_name;
        this.personal_id = personal_id;
    }

    public BigDecimal getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(BigDecimal loan_amount) {
        this.loan_amount = loan_amount;
    }

    public Date getLoan_term() {
        return loan_term;
    }

    public void setLoan_term(Date loan_term) {
        this.loan_term = loan_term;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(String personal_id) {
        this.personal_id = personal_id;
    }
}
