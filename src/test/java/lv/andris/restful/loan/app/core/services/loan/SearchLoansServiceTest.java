package lv.andris.restful.loan.app.core.services.loan;

import lv.andris.restful.loan.app.core.database.JpaLoanRepository;
import lv.andris.restful.loan.app.core.domain.Loan;
import lv.andris.restful.loan.app.core.requests.loan.SearchLoansRequest;
import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.responses.loan.SearchLoansResponse;
import lv.andris.restful.loan.app.core.services.loan.validators.SearchLoansValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SearchLoansServiceTest {

    @Mock
    private JpaLoanRepository repository;
    @Mock
    private SearchLoansValidator validator;
    @InjectMocks
    private SearchLoansService service;

    @Test
    public void shouldReturnErrorsWhenValidatorFails() {
        SearchLoansRequest request = new SearchLoansRequest(false, " ");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Personal id", "received invalid data"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchLoansResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(repository);
    }

    @Test
    public void shouldReturnUnapprovedLoans() {
        SearchLoansRequest request = new SearchLoansRequest(false);
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan( new BigDecimal("33000.00"), Date.valueOf("2022-01-10"), "Jimmy", "Pearson", "12346-123456", false));
        Mockito.when(repository.getLoansByApprovedStatus(false)).thenReturn(loans);
        SearchLoansResponse response = service.execute(request);
        assertEquals(response.getLoan().size(), 1);
        assertEquals(response.getLoan().get(0).getIsApproved(), false);

    }

    @Test
    public void shouldReturnApprovedLoans() {
        SearchLoansRequest request = new SearchLoansRequest(true);
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan( new BigDecimal("33000.00"), Date.valueOf("2022-01-10"), "Jimmy", "Pearson", "12346-123456", true));
        Mockito.when(repository.getLoansByApprovedStatus(true)).thenReturn(loans);
        SearchLoansResponse response = service.execute(request);
        assertEquals(response.getLoan().size(), 1);
        assertEquals(response.getLoan().get(0).getIsApproved(), true);

    }

    @Test
    public void shouldReturnUnapprovedLoansByPersonalId() {
        SearchLoansRequest request = new SearchLoansRequest(false,"12356-923456");
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan( new BigDecimal("35000.00"), Date.valueOf("2022-05-11"), "Abraham", "Pearson", "12356-923456", false));
        Mockito.when(repository.getApprovedOrUnapprovedLoansByPersonalId(false, "12356-923456")).thenReturn(loans);
        SearchLoansResponse response = service.execute(request);
        assertEquals(response.getLoan().size(), 1);
        assertEquals(response.getLoan().get(0).getIsApproved(), false);
        assertEquals(response.getLoan().get(0).getPersonalId(), "12356-923456");

    }

    @Test
    public void shouldReturnApprovedLoansByPersonalId() {
        SearchLoansRequest request = new SearchLoansRequest(true,"12356-923456");
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan( new BigDecimal("35000.00"), Date.valueOf("2022-05-11"), "Abraham", "Pearson", "12356-923456", true));
        loans.add(new Loan( new BigDecimal("5000.00"), Date.valueOf("2022-01-20"), "Abraham", "Pearson", "12356-923456", true));
        Mockito.when(repository.getApprovedOrUnapprovedLoansByPersonalId(true, "12356-923456")).thenReturn(loans);
        SearchLoansResponse response = service.execute(request);
        assertEquals(response.getLoan().size(), 2);
        assertEquals(response.getLoan().get(0).getIsApproved(), true);
        assertEquals(response.getLoan().get(0).getPersonalId(), "12356-923456");
        assertEquals(response.getLoan().get(1).getIsApproved(), true);
        assertEquals(response.getLoan().get(1).getPersonalId(), "12356-923456");

    }
}
