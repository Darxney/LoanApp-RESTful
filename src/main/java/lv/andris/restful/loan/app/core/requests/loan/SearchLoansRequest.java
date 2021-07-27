package lv.andris.restful.loan.app.core.requests.loan;

public class SearchLoansRequest {

    private boolean is_approved;
    private String personal_id;

    public SearchLoansRequest() {

    }

   public SearchLoansRequest(boolean is_approved) {
        this.is_approved = is_approved;
   }

    public SearchLoansRequest(boolean is_approved, String personal_id) {
        this.is_approved = is_approved;
        this.personal_id = personal_id;
    }

    public boolean getIs_approved() {
        return is_approved;
    }

    public String getPersonal_id() {
        return personal_id;
    }

    public boolean isGetPersonal_idProvided() {
        return this.personal_id != null && !this.personal_id.isEmpty();
    }

}
