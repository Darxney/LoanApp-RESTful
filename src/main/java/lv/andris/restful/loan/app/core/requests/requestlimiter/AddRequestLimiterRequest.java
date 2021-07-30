package lv.andris.restful.loan.app.core.requests.requestlimiter;


import java.sql.Timestamp;

public class AddRequestLimiterRequest {

    private String country;
    private int requests_made;
    private Timestamp expiry;

    public AddRequestLimiterRequest(String country, int requests_made, Timestamp expiry) {
        this.country = country;
        this.requests_made = requests_made;
        this.expiry = expiry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRequests_made() {
        return requests_made;
    }

    public void setRequests_made(int requests_made) {
        this.requests_made = requests_made;
    }

    public Timestamp getExpiry() {
        return expiry;
    }

    public void setExpiry(Timestamp expiry) {
        this.expiry = expiry;
    }
}
