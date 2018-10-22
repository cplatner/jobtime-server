package com.platner.jobtime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platner.jobtime.repo.JobRepository;
import com.platner.jobtime.resource.JobResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, value = "server.port:8080")
@RunWith(SpringJUnit4ClassRunner.class)

public class BootdemoApplicationTests
{
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    //Required to delete the data added for tests.
    //Directly invoke the APIs interacting with the DB
    @Autowired
    private JobRepository bookRepository;

    //Test RestTemplate to invoke the APIs.
    private TestRestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void testCreateBookApi() throws JsonProcessingException
    {
        //Building the Request body data
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Job 1");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> bodyAndHeaders = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

        //Invoking the API
        JobResource apiResponse = restTemplate.postForObject("http://localhost:8080/api/job", bodyAndHeaders, JobResource.class, Collections.EMPTY_MAP);

        Assert.assertNotNull(apiResponse);

        //Asserting the response of the API.
//        String message = apiResponse.get("message").toString();
//        assertEquals("Book created successfully", message);
//        Long bookId = ((Map<String, Object>) apiResponse.get("book")).get("id").toString();
//
//        assertNotNull(bookId);
//
//        //Fetching the Book details directly from the DB to verify the API succeeded
//        Job bookFromDb = bookRepository.findById(bookId);
//        assertEquals("Book 1", bookFromDb.getName());
//
//        //Delete the data added for testing
//        bookRepository.delete(bookId);

    }
}
