package com.platner.jobtime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platner.jobtime.model.Job;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, value = "server.port:8080")
@RunWith(SpringJUnit4ClassRunner.class)

public class BootdemoApplicationTests
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private JobRepository repo;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void jobController_createJob_jobOk() throws JsonProcessingException
    {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Job 1");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> bodyAndHeaders = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

        try {
            JobResource response = restTemplate.postForObject("http://localhost:8080/api/job", bodyAndHeaders, JobResource.class, Collections.EMPTY_MAP);

            //* Verify call works
            Assert.assertNotNull(response);
            Assert.assertEquals(Long.valueOf(6), response.getJobId());
            Assert.assertEquals("Job 1", response.getJobName());

            //* Verify the repository has the new object
            Optional<Job> jobFromRepo = repo.findById(Long.valueOf(6));
            Assert.assertEquals("Job 1", jobFromRepo.get().getName());
            repo.delete(jobFromRepo.get());
        } finally {
            // repo.delete(Long.valueOf(6));
        }
    }
}
