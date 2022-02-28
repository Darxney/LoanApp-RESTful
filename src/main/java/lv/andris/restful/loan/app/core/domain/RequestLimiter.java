package lv.andris.restful.loan.app.core.domain;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "request_limiter")
public class RequestLimiter {

    @Id
    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "requests_made", nullable = false)
    private int requestsMade;

    @Column(name = "expiry", nullable = false)
    private Timestamp expiry;

    public RequestLimiter() {

    }

    public RequestLimiter(String country, int requests_made, Timestamp expiry) {
        this.country = country;
        this.requestsMade = requests_made;
        this.expiry = expiry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRequests_made() {
        return requestsMade;
    }

    public void setRequests_made(int requests_made) {
        this.requestsMade = requests_made;
    }

    public Timestamp getExpiry() {
        return expiry;
    }

    public void setExpiry(Timestamp expiry) {
        this.expiry = expiry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLimiter that = (RequestLimiter) o;
        return requestsMade == that.requestsMade && country.equals(that.country) && expiry.equals(that.expiry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, requestsMade, expiry);
    }

    @Override
    public String toString() {
        return "RequestLimiter{" +
                "country='" + country + '\'' +
                ", requestsMade=" + requestsMade +
                ", expiry=" + expiry +
                '}';
    }
}