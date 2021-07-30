package lv.andris.restful.loan.app.core.requests.loan;

public class ChangeLoanStatusRequest {
    private Long loan_id;
    private boolean is_approved;

    public ChangeLoanStatusRequest() {

    }

    public ChangeLoanStatusRequest(Long loan_id, boolean is_approved) {
        this.loan_id = loan_id;
        this.is_approved = is_approved;

    }

    public Long getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(Long loan_id) {
        this.loan_id = loan_id;
    }

    public boolean getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }
}
