package lv.andris.restful.loan.app.core.responses.requestlimiter;

import lv.andris.restful.loan.app.core.domain.RequestLimiter;

public class AddRequestLimiterResponse  {

    private RequestLimiter newRequestLimiter;


    public AddRequestLimiterResponse(RequestLimiter newRequestLimiter) {
        this.newRequestLimiter = newRequestLimiter;
    }

    public RequestLimiter getNewRequestLimiter() {
        return newRequestLimiter;
    }

}
