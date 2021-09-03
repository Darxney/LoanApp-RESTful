package lv.andris.restful.loan.app.core.services.requestlimiter;

import lv.andris.restful.loan.app.core.database.JpaRequestLimiterRepository;
import lv.andris.restful.loan.app.core.domain.RequestLimiter;
import lv.andris.restful.loan.app.core.responses.requestlimiter.AddRequestLimiterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Component
@Transactional
public class AddRequestLimiterService {

    @Autowired
    private JpaRequestLimiterRepository repository;

    public AddRequestLimiterResponse execute(HttpServletRequest servletRequest) {

        ClientHttp clientHttp = new ClientHttp();
        String countryCode = clientHttp.generateCountryCode(servletRequest);


        Timestamp expiryTime = Timestamp.valueOf(LocalDateTime.now());
        expiryTime.setTime(expiryTime.getTime() + 30000);

        RequestLimiter requestLimiter = new RequestLimiter(countryCode,  1, expiryTime);

        Timestamp expiry = repository.getExpiry(countryCode);

        if(expiry == null){
            repository.save(requestLimiter);
        } else if(Timestamp.valueOf(LocalDateTime.now()).after(expiry)) {
            repository.deleteRequestLimiterForCountry(countryCode);
            repository.save(requestLimiter);
            //can be done with setters
        } else if(repository.getRequestLimiterByCountry(countryCode).toString().contains(countryCode)) {
            repository.incrementRequestCount(countryCode);
        }

        return new AddRequestLimiterResponse(requestLimiter);
    }
}
