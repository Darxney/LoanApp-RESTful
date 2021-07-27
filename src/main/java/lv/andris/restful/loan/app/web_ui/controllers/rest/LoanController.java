package lv.andris.restful.loan.app.web_ui.controllers.rest;

import lv.andris.restful.loan.app.core.requests.loan.AddLoanRequest;
import lv.andris.restful.loan.app.core.requests.loan.GetAllLoansRequest;
import lv.andris.restful.loan.app.core.requests.loan.SearchLoansRequest;
import lv.andris.restful.loan.app.core.responses.loan.AddLoanResponse;
import lv.andris.restful.loan.app.core.responses.loan.GetAllLoansResponse;
import lv.andris.restful.loan.app.core.responses.loan.SearchLoansResponse;
import lv.andris.restful.loan.app.core.services.loan.AddLoanService;
import lv.andris.restful.loan.app.core.services.loan.GetAllLoansService;
import lv.andris.restful.loan.app.core.services.loan.SearchLoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private AddLoanService addLoanService;

    @Autowired
    private GetAllLoansService getAllLoanService;

    @Autowired
    private SearchLoansService searchLoansService;


    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddLoanResponse addLoan(@RequestBody AddLoanRequest request) {
        return addLoanService.execute(request);
    }

    @GetMapping(path = "/getAllLoans", produces = "application/json")
    public GetAllLoansResponse response() {
        GetAllLoansRequest request = new GetAllLoansRequest();
        return getAllLoanService.execute(request);
    }

    //list all approved or unapproved loans
    @GetMapping(path = "/getLoansByApprovedStatus", produces = "application/json")
     public SearchLoansResponse searchLoanByApprovedStatus(@RequestParam boolean is_approved) {

        SearchLoansRequest request = new SearchLoansRequest(is_approved);
        return searchLoansService.execute(request);
    }

    //list all approved or unapproved loans by user
    @GetMapping(path = "/getLoansByApprovedStatusAndPersonalId", produces = "application/json")
    public SearchLoansResponse searchLoanByApprovedStatusAndPersonalId(@RequestParam boolean is_approved,
                                                                       @RequestParam String personal_id) {

        SearchLoansRequest request = new SearchLoansRequest(is_approved, personal_id);
        return searchLoansService.execute(request);

    }


}

