package lv.andris.restful.loan.app.core.matchers;

import lv.andris.restful.loan.app.core.domain.PersonalId;
import org.mockito.ArgumentMatcher;

public class PersonalIdMatcher implements ArgumentMatcher<PersonalId> {

    private String personal_id;

    public PersonalIdMatcher(String personal_id) {
        this.personal_id = personal_id;
    }

    @Override
    public boolean matches(PersonalId personalId) {
        return personalId.getPersonal_id().equals(personal_id);
    }
}
