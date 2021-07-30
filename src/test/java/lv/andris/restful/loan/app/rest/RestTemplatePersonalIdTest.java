package lv.andris.restful.loan.app.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.andris.restful.loan.app.core.domain.PersonalId;
import lv.andris.restful.loan.app.rest.classesForRestTests.PersonalId.PersonalIdJsonBodyMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestTemplatePersonalIdTest {

    //The app needs to be running for these tests to pass!

    private RestTemplate restTemplate;

    private static final String resourceUrl = "http://localhost:8080/personalId/";


    @Before
    public void beforeTest() {
        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
    }

    //Run this test only once. If you want to re run this test delete all entries from personal_id_blacklist table in sql
    //otherwise it will not return the value because of validation(refer to AddPersonalIdToBlackListValidator)
    @Test
    public void whenPostForAddPersonalIdObject_thenCreatedObjectIsReturned() {
        final HttpEntity<PersonalId> request = new HttpEntity<>(new PersonalId("20000-100001"));
        final PersonalIdJsonBodyMapper personalId = restTemplate.postForObject(resourceUrl + "addToBlacklist", request, PersonalIdJsonBodyMapper.class);

        assertThat(personalId, notNullValue());
        assertThat(personalId.getPersonalId().getPersonal_id(), is("20000-100001"));
    }

    @Test
    public void whenSendGetForGetBlacklistEntity_thenStatusOk() throws IOException {
        final ResponseEntity<PersonalId> response = restTemplate.getForEntity(resourceUrl + "getBlacklist", PersonalId.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void whenSendGetBlacklistForRequestEntity_thenBodyCorrect() throws IOException {
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> response = template.getForEntity(resourceUrl + "getBlacklist", String.class);

        ObjectMapper mapper = new ObjectMapper();
        final JsonNode root = mapper.readTree(response.getBody());
        System.out.println(root);
        final JsonNode personal_id = root.path("blacklist").path("personal_id");
        assertThat(personal_id.asText(), notNullValue());
    }
}
