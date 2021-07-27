package lv.andris.restful.loan.app.core.services;

import lv.andris.restful.loan.app.core.database.JpaLoanRepository;
import lv.andris.restful.loan.app.core.services.loan.validators.AddLoanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Component
@Transactional
public class CountryResolverService {

    @Autowired
    private JpaLoanRepository repository;

    @Autowired
    private AddLoanValidator validator;

    public void get(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://ip-api.com/json/{ip}?fields=countryCode"))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

}
