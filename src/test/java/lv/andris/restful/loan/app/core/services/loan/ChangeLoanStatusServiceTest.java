package lv.andris.restful.loan.app.core.services.loan;

import lv.andris.restful.loan.app.core.database.JpaLoanRepository;
import lv.andris.restful.loan.app.core.requests.loan.ChangeLoanStatusRequest;
import lv.andris.restful.loan.app.core.responses.loan.ChangeLoanStatusResponse;
import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.services.loan.validators.ChangeLoanStatusValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangeLoanStatusServiceTest {

    @Mock
    private JpaLoanRepository repository;
    @Mock
    private ChangeLoanStatusValidator validator;
    @InjectMocks
    private ChangeLoanStatusService service;

    @Test
    public void shouldChangeLoanApprovedStatus() {
        ChangeLoanStatusRequest request = new ChangeLoanStatusRequest(60L, true);
        ChangeLoanStatusResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldNotChangeLoanApprovedStatus() {
        ChangeLoanStatusRequest request = new ChangeLoanStatusRequest(null, true);
        List<CoreError> errors = new ArrayList<>(List.of(new CoreError("Loan Id", "Must not be empty")));
        when(validator.validate(request)).thenReturn(errors);
        ChangeLoanStatusResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Loan Id");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }
}
