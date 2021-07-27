package lv.andris.restful.loan.app.web_ui.controllers.rest;

import lv.andris.restful.loan.app.core.requests.personalid.AddPersonalIdToBlacklistRequest;
import lv.andris.restful.loan.app.core.requests.personalid.GetBlacklistRequest;
import lv.andris.restful.loan.app.core.responses.personalid.AddPersonalIdToBlacklistResponse;
import lv.andris.restful.loan.app.core.responses.personalid.GetBlacklistResponse;
import lv.andris.restful.loan.app.core.services.personalid.AddPersonalIdToBlackListService;
import lv.andris.restful.loan.app.core.services.personalid.GetBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personalId")
public class PersonalIdController {

    @Autowired
    private AddPersonalIdToBlackListService addPersonalIdToBlackListService;

    @Autowired
    private GetBlacklistService getBlacklistService;

    @PostMapping(path = "/addToBlacklist",
            consumes = "application/json",
            produces = "application/json")
    public AddPersonalIdToBlacklistResponse addPersonalIdToBlacklist(@RequestBody AddPersonalIdToBlacklistRequest request) {
        return addPersonalIdToBlackListService.execute(request);
    }

    @GetMapping(path = "/getBlacklist", produces = "application/json")
    public GetBlacklistResponse response() {
        GetBlacklistRequest request = new GetBlacklistRequest();
        return getBlacklistService.execute(request);
    }
}
