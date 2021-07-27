package lv.andris.restful.loan.app.core.services.personalid;

import lv.andris.restful.loan.app.core.database.JpaPersonalIdRepository;
import lv.andris.restful.loan.app.core.domain.PersonalId;
import lv.andris.restful.loan.app.core.requests.personalid.GetBlacklistRequest;
import lv.andris.restful.loan.app.core.responses.personalid.GetBlacklistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional
public class GetBlacklistService {

    @Autowired
    JpaPersonalIdRepository repository;

    public GetBlacklistResponse execute(GetBlacklistRequest request) {
        List<PersonalId> blacklist = repository.getBlackList();
        return new GetBlacklistResponse(blacklist);

    }
}
