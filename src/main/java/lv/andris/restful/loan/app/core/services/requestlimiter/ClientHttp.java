package lv.andris.restful.loan.app.core.services.requestlimiter;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class ClientHttp {

    public ClientHttp() {
    }

    public String generateCountryCode(ServletRequest servletRequest){

    CountryCode countryCode = new CountryCode();


    String ip = servletRequest.getRemoteAddr();


    Gson gson = new Gson();
    java.net.http.HttpClient client = java.net.http.HttpClient.newBuilder()
            .version(java.net.http.HttpClient.Version.HTTP_1_1)
            .followRedirects(java.net.http.HttpClient.Redirect.NEVER)
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://ip-api.com/json/"+ ip +"?fields=countryCode"))
            .build();
        try {
        HttpResponse<String> response = client
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        countryCode = gson.fromJson(response.body(), CountryCode.class);
    }catch (Exception e) {
            System.out.println("error occurred : " + e.getMessage());

        }
        if(countryCode.getCountryCode() == null){
            return countryCode.setCountryCode("LV");
        }
        return countryCode.getCountryCode();
    }
}
