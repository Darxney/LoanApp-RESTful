package lv.andris.restful.loan.app.core.services.loan;

import lv.andris.restful.loan.app.core.database.JpaLoanRepository;
import lv.andris.restful.loan.app.core.domain.Loan;
import lv.andris.restful.loan.app.core.requests.loan.GetAllLoansRequest;
import lv.andris.restful.loan.app.core.responses.loan.GetAllLoansResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetAllLoansServiceTest {

    @Mock
    private JpaLoanRepository repository;
    @InjectMocks
    private GetAllLoansService service;

    @Test
    public void shouldReturnAllLoansFromDb() {
        GetAllLoansRequest request = new GetAllLoansRequest();
        Loan loanOne = new Loan( new BigDecimal("2500.00"), Date.valueOf("2022-05-11"), "Geoff", "Duningham", "89898-123456");
        Loan loanTwo = new Loan( new BigDecimal("33000.00"), Date.valueOf("2022-01-10"), "Jimmy", "Pearson", "12346-123456");
        Mockito.when(repository.getAllLoans()).thenReturn(List.of(loanOne,loanTwo));
        GetAllLoansResponse response = service.execute(request);
        assertEquals(response.getLoan().size(), 2);
    }
}
