package lv.andris.restful.loan.app.core.responses.loan;

import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.responses.CoreResponse;

import java.util.List;

public class ChangeLoanStatusResponse extends CoreResponse {

    private int isApprovedStatusChanged;

    public ChangeLoanStatusResponse(List<CoreError> errors) {
        super(errors);
    }

    public ChangeLoanStatusResponse(int isApprovedStatusChanged) {
        this.isApprovedStatusChanged = isApprovedStatusChanged;
    }

    public void ChangeLoanStatusResponse() {
    }

    public int getApprovedStatusChanged() {
        return isApprovedStatusChanged;
    }

}
