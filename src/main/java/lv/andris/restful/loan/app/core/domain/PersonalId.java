package lv.andris.restful.loan.app.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "personal_id_blacklist")
public class PersonalId {

    @Id
    @Column(name = "personal_id", nullable = false)
    private String personal_id;

    public PersonalId() {

    }

    public PersonalId(String personal_id) {
        this.personal_id = personal_id;
    }

    public String getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(String personal_id) {
        this.personal_id = personal_id;
    }

    @Override
    public String toString() {
        return  personal_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalId that = (PersonalId) o;
        return personal_id.equals(that.personal_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personal_id);
    }
}
