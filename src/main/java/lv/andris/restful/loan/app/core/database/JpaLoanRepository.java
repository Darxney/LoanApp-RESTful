package lv.andris.restful.loan.app.core.database;

import lv.andris.restful.loan.app.core.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaLoanRepository extends JpaRepository<Loan, Long> {

        @Query("SELECT l FROM Loan l")
        List<Loan> getAllLoans();

        //list all approved/unapproved loans
        @Query("SELECT l FROM Loan l WHERE isApproved = :is_approved")
        List<Loan> getLoansByApprovedStatus(@Param("is_approved") boolean is_approved);

        //list all approved/unapproved loans by user
        @Query("SELECT l FROM Loan l WHERE isApproved = :is_approved AND personalId = :personal_id")
        List<Loan> getApprovedOrUnapprovedLoansByPersonalId(@Param("is_approved") boolean is_approved,@Param("personal_id") String personal_id);


        //Change loan approved status
        @Modifying
        @Query("UPDATE Loan AS l SET l.isApproved = :is_approved WHERE l.loanId = :loan_id")
        int changeLoanStatus(@Param("loan_id") Long loan_id, @Param("is_approved") boolean is_approved);


}
