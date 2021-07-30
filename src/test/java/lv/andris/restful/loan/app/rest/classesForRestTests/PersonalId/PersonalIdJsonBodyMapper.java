package lv.andris.restful.loan.app.rest.classesForRestTests.PersonalId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lv.andris.restful.loan.app.core.domain.PersonalId;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalIdJsonBodyMapper {

   private String errors;
   private PersonalId personalId;

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public PersonalId getPersonalId() {
        return personalId;
    }

    public void setPersonalId(PersonalId personalId) {
        this.personalId = personalId;
    }

    @Override
    public String toString() {
        return "PersonalIdJsonBodyMapper{" +
                "errors='" + errors + '\'' +
                ", personalId=" + personalId +
                '}';
    }
}
