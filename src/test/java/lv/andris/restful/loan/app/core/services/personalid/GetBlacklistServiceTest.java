package lv.andris.restful.loan.app.core.services.personalid;

import lv.andris.restful.loan.app.core.database.JpaPersonalIdRepository;
import lv.andris.restful.loan.app.core.domain.PersonalId;
import lv.andris.restful.loan.app.core.requests.personalid.GetBlacklistRequest;
import lv.andris.restful.loan.app.core.responses.personalid.GetBlacklistResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetBlacklistServiceTest {

    @Mock
    private JpaPersonalIdRepository repository;
    @InjectMocks
    private GetBlacklistService service;


    @Test
    public void shouldGetBlacklistFromDb() {
        List<PersonalId> blacklist = new ArrayList<>();
        blacklist.add(new PersonalId("12344-100532"));
        Mockito.when(repository.getBlackList()).thenReturn(blacklist);

        GetBlacklistRequest request = new GetBlacklistRequest();
        GetBlacklistResponse response = service.execute(request);
        assertEquals(response.getBlackList().size(), 1);
        assertEquals(response.getBlackList().get(0).getPersonal_id(), "12344-100532");
    }
}
