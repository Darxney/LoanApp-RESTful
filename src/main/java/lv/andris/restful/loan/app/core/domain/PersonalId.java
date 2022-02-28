package lv.andris.restful.loan.app.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "personal_id_blacklist")
//class name should be blacklistEntry or blacklistedPersonalId etc.
public class PersonalId {

    @Id
    @Column(name = "personal_id", nullable = false)
    private String personalId;

    public PersonalId() {

    }

    public PersonalId(String personal_id) {
        this.personalId = personal_id;
    }

    public String getPersonal_id() {
        return personalId;
    }

    public void setPersonal_id(String personal_id) {
        this.personalId = personal_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalId that = (PersonalId) o;
        return personalId.equals(that.personalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalId);
    }

    @Override
    public String toString() {
        return "PersonalId{" +
                "personal_id='" + personalId + '\'' +
                '}';
    }
}
