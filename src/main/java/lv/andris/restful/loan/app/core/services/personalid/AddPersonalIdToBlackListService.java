package lv.andris.restful.loan.app.core.services.personalid;



import lv.andris.restful.loan.app.core.database.JpaPersonalIdRepository;
import lv.andris.restful.loan.app.core.domain.PersonalId;
import lv.andris.restful.loan.app.core.requests.personalid.AddPersonalIdToBlacklistRequest;
import lv.andris.restful.loan.app.core.responses.personalid.AddPersonalIdToBlacklistResponse;
import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.services.personalid.validators.AddPersonalIdToBlackListValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddPersonalIdToBlackListService {

    @Autowired
    JpaPersonalIdRepository repository;

    @Autowired
    AddPersonalIdToBlackListValidator validator;

    public AddPersonalIdToBlacklistResponse execute(AddPersonalIdToBlacklistRequest request) {
        List<CoreError> errors = validator.validate(request);

        if(!errors.isEmpty()) {
            return new AddPersonalIdToBlacklistResponse(errors);
        }

        PersonalId personalId = new PersonalId(request.getPersonal_id());
        repository.save(personalId);
        System.out.println("New Personal Id has been added to the blacklist");


        return new AddPersonalIdToBlacklistResponse(personalId);
    }
}
