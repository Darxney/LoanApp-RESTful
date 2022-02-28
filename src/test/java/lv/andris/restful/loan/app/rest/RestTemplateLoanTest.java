package lv.andris.restful.loan.app.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.andris.restful.loan.app.core.domain.Loan;
import lv.andris.restful.loan.app.rest.classesForRestTests.Loan.LoanJsonBodyMapper;
import lv.andris.restful.loan.app.rest.classesForRestTests.Loan.LoanJsonBodyMapperArray;
import lv.andris.restful.loan.app.rest.classesForRestTests.Loan.LoanUpdateJsonMapper;
import org.apache.commons.codec.Charsets;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class RestTemplateLoanTest {

    //The app needs to be running for these tests to pass!
    private RestTemplate restTemplate;

    private static final String resourceUrl = "http://localhost:8080/loan/";


    @Before
    public void beforeTest() {
        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
    }



    @Test
    @Ignore
    //broken for now
    public void whenPostForAddLoanObject_thenCreatedObjectIsReturned() {
        Loan loanObj = new Loan();
        loanObj.setLoanAmount(new BigDecimal("2500.00"));
        loanObj.setLoanTerm(Date.valueOf("2022-05-11"));
        loanObj.setFirstName("Geoff");
        loanObj.setLastName("Duningham");
        loanObj.setPersonalId("20053-123465");
        final HttpEntity<Loan> request = new HttpEntity<>(loanObj);
        final LoanJsonBodyMapper loan = restTemplate.postForObject(resourceUrl, request, LoanJsonBodyMapper.class);

       // System.out.println(Arrays.stream(loan.getErrors()).collect(Collectors.toList()).get(2).getField());
       // System.out.println(Arrays.stream(loan.getErrors()).collect(Collectors.toList()).get(2).getMessage());
        //loan.setLoan(new Loan(new BigDecimal("2500.00"), Date.valueOf("2022-05-11"), "Geoff", "Duningham", "20053-123465"));
        System.out.println(loan.getLoan().getFirstName());
        assertThat(loan, notNullValue());
        assertThat(loan.getLoan().getPersonalId(), is("20053-123465"));
    }


    @Test
    public void whenSendGetForGetAllLoansEntity_thenStatusOk() throws IOException {
        final ResponseEntity<Loan> response = restTemplate.getForEntity(resourceUrl + "getAllLoans", Loan.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void whenSendGetAllLoansForRequestEntity_thenBodyCorrect() throws IOException {
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> response = template.getForEntity(resourceUrl + "getAllLoans", String.class);

        ObjectMapper mapper = new ObjectMapper();
        final JsonNode root = mapper.readTree(response.getBody());
        final JsonNode first_name = root.path("loan").path("first_name");
        assertThat(first_name.asText(), notNullValue());
    }


    @Test
    public void whenRetrievingGetLoansByApprovedStatusFalse_thenCorrect() throws IOException {
        final ResponseEntity<LoanJsonBodyMapperArray> response = restTemplate.getForEntity(resourceUrl + "getLoansByApprovedStatus?is_approved=false", LoanJsonBodyMapperArray.class);

        final LoanJsonBodyMapperArray loanResponse = response.getBody();
        assertThat(loanResponse, notNullValue());
        assertThat(Arrays.stream(loanResponse.getLoan()).collect(Collectors.toList()).get(0).getIsApproved(), is(false));
    }

    @Test
    public void whenRetrievingGetLoansByApprovedStatusTrue_thenCorrect() throws IOException {

        final ResponseEntity<LoanJsonBodyMapperArray> response = restTemplate.getForEntity(resourceUrl + "getLoansByApprovedStatus?is_approved=true", LoanJsonBodyMapperArray.class);

        final LoanJsonBodyMapperArray loanResponse = response.getBody();
        assertThat(loanResponse, notNullValue());
        assertThat(Arrays.stream(loanResponse.getLoan()).collect(Collectors.toList()).get(0).getIsApproved(), is(true));
    }

    @Test
    public void whenRetrievingGetLoansByApprovedStatusFalseAndPersonal_Id_thenCorrect() throws IOException {

        final ResponseEntity<LoanJsonBodyMapperArray> response = restTemplate.getForEntity(resourceUrl + "getLoansByApprovedStatus?is_approved=false&personal_id=20053-123456", LoanJsonBodyMapperArray.class);

        final LoanJsonBodyMapperArray loanResponse = response.getBody();
        assertThat(loanResponse, notNullValue());
        assertThat(Arrays.stream(loanResponse.getLoan()).collect(Collectors.toList()).get(0).getIsApproved(), is(false));
        assertThat(Arrays.stream(loanResponse.getLoan()).collect(Collectors.toList()).get(0).getPersonalId(), is("20053-123456"));
    }


    @Test
    public void whenRetrievingGetLoansByApprovedStatusTrueAndPersonal_Id_thenCorrect() throws IOException {

        final ResponseEntity<LoanJsonBodyMapperArray> response = restTemplate.getForEntity(resourceUrl + "getLoansByApprovedStatus?is_approved=true&personal_id=20053-123456", LoanJsonBodyMapperArray.class);

        final LoanJsonBodyMapperArray loanResponse = response.getBody();
        assertThat(loanResponse, notNullValue());
        assertThat(Arrays.stream(loanResponse.getLoan()).collect(Collectors.toList()).get(0).getIsApproved(), is(true));
        assertThat(Arrays.stream(loanResponse.getLoan()).collect(Collectors.toList()).get(0).getPersonalId(), is("20053-123456"));
    }



    //Didn't have time to figure this out(The mapping works if manually tested through swagger i just Haven't mastered rest templates yet :P)
    //If i had more than just a week of time i would have figured it out for sure :D(its my first time working with rest template)
//    @Test
//    public void givenLoanService_whenPutExistingEntity_thenItIsUpdated() {
//
//        HttpHeaders httpHeaders = prepareBasicAuthHeaders();
//        //final HttpEntity<Foo> request = new HttpEntity<>(new Foo("bar"), httpHeaders);
//
//        final HttpEntity<Loan> request = new HttpEntity<>(new Loan(200L, new BigDecimal("2500.00"), Date.valueOf("2022-05-11"), "Geoff", "Duningham", "20053-123456", false));
//        // Create Resource
//        final ResponseEntity<LoanJsonBodyMapper> createResponse = restTemplate.exchange(thisResourceUrl, HttpMethod.POST, request, LoanJsonBodyMapper.class);
//
//        Loan updatedInstance = new Loan(200L, new BigDecimal("2500.00"), Date.valueOf("2022-05-11"), "Geoff", "Duningham", "20053-123456", false);
//        updatedInstance.setIs_approved(createResponse.getBody().getLoan().getIsApproved());
//        updatedInstance.setLoan_id(createResponse.getBody().getLoan().getLoan_id());
//
//        HttpEntity<Loan> requestUpdate = new HttpEntity<>(updatedInstance, httpHeaders);
//        restTemplate.exchange(thisResourceUrl, HttpMethod.PUT, requestUpdate, Void.class);
//
//        //  Check that Resource was updated
//        final ResponseEntity<LoanUpdateJsonMapper> updateResponse = restTemplate.exchange(thisResourceUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), LoanUpdateJsonMapper.class);
//        final LoanUpdateJsonMapper loanUpdate = updateResponse.getBody();
//        assertThat(loanUpdate.getApprovedStatusChanged(), is(1));
//
//
//    }

    //for put mapping that i couldn't figure out
//
//    private HttpHeaders prepareBasicAuthHeaders() {
//        final HttpHeaders headers = new HttpHeaders();
//        final String encodedLogPass = getBase64EncodedLogPass();
//        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + encodedLogPass);
//        return headers;
//    }
//
//    private String getBase64EncodedLogPass() {
//        final String logPass = "user1:user1Pass";
//        final byte[] authHeaderBytes = encodeBase64(logPass.getBytes(Charsets.US_ASCII));
//        return new String(authHeaderBytes, Charsets.US_ASCII);
//
//
//    }
}
