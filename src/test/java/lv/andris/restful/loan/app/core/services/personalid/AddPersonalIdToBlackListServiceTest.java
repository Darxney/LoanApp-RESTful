package lv.andris.restful.loan.app.core.services.personalid;

import lv.andris.restful.loan.app.core.database.JpaPersonalIdRepository;
import lv.andris.restful.loan.app.core.matchers.PersonalIdMatcher;
import lv.andris.restful.loan.app.core.requests.personalid.AddPersonalIdToBlacklistRequest;
import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.responses.personalid.AddPersonalIdToBlacklistResponse;
import lv.andris.restful.loan.app.core.services.personalid.validators.AddPersonalIdToBlackListValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddPersonalIdToBlackListServiceTest {

    @Mock
    private JpaPersonalIdRepository repository;
    @Mock
    private AddPersonalIdToBlackListValidator validator;
    @InjectMocks
    private AddPersonalIdToBlackListService service;

    @Test
    public void shouldAddPersonalIdToBlacklist() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddPersonalIdToBlacklistRequest request = new AddPersonalIdToBlacklistRequest("10054-100456");
        AddPersonalIdToBlacklistResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(repository).save(
                argThat(new PersonalIdMatcher("10054-100456")));
    }

    @Test
    public void shouldReturnErrorsOnValidation() {
        AddPersonalIdToBlacklistRequest request = new AddPersonalIdToBlacklistRequest("10054-1");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Personal id", "received invalid data"));
        Mockito.when(validator.validate(any())).thenReturn(errors);

        AddPersonalIdToBlacklistResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Personal id");
        assertEquals(response.getErrors().get(0).getMessage(), "received invalid data");

    }
}
