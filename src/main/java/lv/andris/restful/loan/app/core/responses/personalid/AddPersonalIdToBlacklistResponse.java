package lv.andris.restful.loan.app.core.responses.personalid;

import lv.andris.restful.loan.app.core.domain.PersonalId;
import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.responses.CoreResponse;

import java.util.List;

public class AddPersonalIdToBlacklistResponse extends CoreResponse {

    private PersonalId newPersonalId;

    public AddPersonalIdToBlacklistResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddPersonalIdToBlacklistResponse(PersonalId newPersonalId) {
        this.newPersonalId = newPersonalId;
    }

    public PersonalId getNewPersonalId() {
        return newPersonalId;
    }

}
