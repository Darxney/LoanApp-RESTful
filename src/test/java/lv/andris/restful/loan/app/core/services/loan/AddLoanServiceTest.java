package lv.andris.restful.loan.app.core.services.loan;

import lv.andris.restful.loan.app.core.database.JpaLoanRepository;
import lv.andris.restful.loan.app.core.database.JpaRequestLimiterRepository;
import lv.andris.restful.loan.app.core.matchers.LoanMatcher;
import lv.andris.restful.loan.app.core.requests.loan.AddLoanRequest;
import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.responses.loan.AddLoanResponse;
import lv.andris.restful.loan.app.core.services.loan.validators.AddLoanValidator;
import lv.andris.restful.loan.app.core.services.requestlimiter.Validators.RequestLimitValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddLoanServiceTest {

    @Mock
    private JpaLoanRepository repository;
    @Mock
    private JpaRequestLimiterRepository requestLimiterRepository;
    @Mock
    private AddLoanValidator validator;
    @Mock
    private RequestLimitValidator limitValidator;
    @Mock
    private HttpServletRequest servletRequest;
    @InjectMocks
    private AddLoanService service;

    @Test
    public void shouldAddLoanToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(limitValidator.validate(any())).thenReturn(new ArrayList<>());
        AddLoanRequest request = new AddLoanRequest(new BigDecimal("2500.00"), Date.valueOf("2022-05-11"), "Geoff", "Duningham", "89898-123456");
        AddLoanResponse response = service.execute(request, servletRequest);
        assertFalse(response.hasErrors());
        Mockito.verify(repository).save(
                argThat(new LoanMatcher(new BigDecimal("2500.00"), Date.valueOf("2022-05-11"), "Geoff", "Duningham", "89898-123456")));

    }

    @Test
    public void shouldReturnErrorsOnValidation() {
        AddLoanRequest request = new AddLoanRequest(new BigDecimal("3500.00"), Date.valueOf("2022-06-11"), "Bad", "PersonalId", "123-1234");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Personal id", "received invalid data"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddLoanResponse response = service.execute(request, servletRequest);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Personal id");
        assertEquals(response.getErrors().get(0).getMessage(), "received invalid data");

        Mockito.verifyNoInteractions(repository);
    }

    @Test
    public void shouldReturnErrorsOnRequestLimitReached() {
        AddLoanRequest request = new AddLoanRequest(new BigDecimal("4500.00"), Date.valueOf("2022-05-11"), "Geoff", "Duningham", "89898-123456");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Please wait", "There are too many applications coming from your country"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddLoanResponse response = service.execute(request, servletRequest);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Please wait");
        assertEquals(response.getErrors().get(0).getMessage(), "There are too many applications coming from your country");

        Mockito.verifyNoInteractions(repository);
        Mockito.verifyNoInteractions(requestLimiterRepository);
    }

}
