package lv.andris.restful.loan.app.core.services.requestlimiter.Validators;

import lv.andris.restful.loan.app.core.database.JpaRequestLimiterRepository;
import lv.andris.restful.loan.app.core.responses.CoreError;
import lv.andris.restful.loan.app.core.services.requestlimiter.ClientHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RequestLimitValidator {

    @Autowired
    private JpaRequestLimiterRepository repository;


    List<CoreError> limits = new ArrayList<>();

    public List<CoreError> validate(HttpServletRequest request) {
        limits.clear();
        validateRequest(request).ifPresent(limits::add);
        return limits;
    }


    private Optional<CoreError> validateRequest(HttpServletRequest request) {
        ClientHttp clientHttp = new ClientHttp();
        String countryCode = clientHttp.generateCountryCode(request);

         if(repository.getRequestsMade(countryCode) < 5){
            return Optional.empty();
        } else if (Timestamp.valueOf(LocalDateTime.now()).before(repository.getExpiry(countryCode))) {
           return Optional.of(new CoreError("Please wait", "There are too many applications coming from your country"));
       }
       return Optional.empty();
    }
}
