package lv.andris.restful.loan.app.core.database;

import lv.andris.restful.loan.app.core.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaLoanRepository extends JpaRepository<Loan, Long> {

        @Query("SELECT l FROM Loan l")
        List<Loan> getAllLoans();

        //list all approved/unapproved loans
        @Query("SELECT l FROM Loan l WHERE is_approved = :is_approved")
        List<Loan> getLoansByApprovedStatus(@Param("is_approved") boolean is_approved);

        //list all approved/unapproved loans by user
        @Query("SELECT l FROM Loan l WHERE is_approved = :is_approved AND personal_id = :personal_id")
        List<Loan> getApprovedOrUnapprovedLoansByPersonalId(@Param("is_approved") boolean is_approved,@Param("personal_id") String personal_id);

       // Optional<Loan> getLoanById(Long id);

}
