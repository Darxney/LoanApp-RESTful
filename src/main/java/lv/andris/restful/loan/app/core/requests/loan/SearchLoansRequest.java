package lv.andris.restful.loan.app.core.requests.loan;

public class SearchLoansRequest {

    private boolean isApproved;
    private String personalId;

    public SearchLoansRequest() {

    }

   public SearchLoansRequest(boolean isApproved) {
        this.isApproved = isApproved;
   }

    public SearchLoansRequest(boolean isApproved, String personalId) {
        this.isApproved = isApproved;
        this.personalId = personalId;
    }

    public boolean getIs_approved() {
        return isApproved;
    }

    public String getPersonal_id() {
        return personalId;
    }

    public boolean isGetPersonal_idProvided() {
        return this.personalId != null && !this.personalId.isEmpty();
    }

}
