package lv.andris.restful.loan.app.core.database;

import lv.andris.restful.loan.app.core.domain.PersonalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPersonalIdRepository extends JpaRepository<PersonalId, String> {

        //memory will be depleted with too many entries
        @Query("SELECT p From PersonalId p")
        List<PersonalId> getBlackList();
}
