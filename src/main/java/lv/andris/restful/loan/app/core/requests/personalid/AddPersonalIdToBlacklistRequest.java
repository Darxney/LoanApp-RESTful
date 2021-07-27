package lv.andris.restful.loan.app.core.requests.personalid;

public class AddPersonalIdToBlacklistRequest {

    private String personal_id;

    AddPersonalIdToBlacklistRequest() {

    }

    AddPersonalIdToBlacklistRequest(String personal_id) {
        this.personal_id = personal_id;

    }

    public String getPersonal_id() {
        return personal_id;
    }
}
