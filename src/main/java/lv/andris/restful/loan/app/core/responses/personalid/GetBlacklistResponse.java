package lv.andris.restful.loan.app.core.responses.personalid;

import lv.andris.restful.loan.app.core.domain.PersonalId;

import java.util.List;

public class GetBlacklistResponse {

    private List<PersonalId> blackList;

    public GetBlacklistResponse(List<PersonalId> blacklist) {
        this.blackList = blacklist;
    }

    public List<PersonalId> getBlackList() {
        return blackList;
    }
}
