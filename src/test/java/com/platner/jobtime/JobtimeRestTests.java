package com.platner.jobtime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
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

public class JobtimeRestTests
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private JobRepository repo;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void jobController_findJobById_isOk()
    {
    }

    @Test
    public void jobController_findAll_isOk()
    {
        Job first = new Job("First");
        repo.save(first);
        Job second = new Job("Second");
        repo.save(second);
        Job third = new Job("Third");
        repo.save(third);

        Iterable<Job> response = restTemplate.getForObject("http://localhost:8080/api/jobs", Iterable.class);

        //* make sure there are at least 3 items
        Assert.assertTrue(Iterables.size(response) >= 3);

        repo.delete(first);
        repo.delete(second);
        repo.delete(third);
    }

    @Test
    public void jobController_createJob_isOk() throws JsonProcessingException
    {
        long id = 0;
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Job 1");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> bodyAndHeaders = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

        try {
            JobResource response = restTemplate.postForObject("http://localhost:8080/api/job", bodyAndHeaders, JobResource.class, Collections.EMPTY_MAP);

            id = response.getJobId();

            //* Verify call works
            Assert.assertNotNull(response);
            Assert.assertEquals(Long.valueOf(id), response.getJobId());
            Assert.assertEquals("Job 1", response.getJobName());

            //* Verify the repository has the new object
            Optional<Job> jobFromRepo = repo.findById(id);
            Assert.assertEquals("Job 1", jobFromRepo.get().getName());
        } finally {
            repo.deleteById(id);
        }
    }

    @Test
    public void jobController_delete_isOk()
    {
        Job first = new Job("To Delete");
        repo.save(first);
        Iterable<Job> response = restTemplate.getForObject("http://localhost:8080/api/jobs", Iterable.class);
        int itemsInRepo = Iterables.size(response);
        Assert.assertTrue(itemsInRepo >= 1);
        String format = String.format("http://localhost:8080/api/job/%d", first.getId());
        restTemplate.delete(format);

        response = restTemplate.getForObject("http://localhost:8080/api/jobs", Iterable.class);

        Assert.assertTrue(Iterables.size(response) < itemsInRepo);

        Job deletedJob = restTemplate.getForObject(String.format("http://localhost:8080/api/job/%d", first.getId()), Job.class);
        Assert.assertNull(deletedJob);
    }

    @Test
    public void jobRepo_generalTest_isOk()
    {
        repo.deleteAll();

        repo.save(new Job("Jack"));
        repo.save(new Job("Chloe"));
        repo.save(new Job("Kim"));
        repo.save(new Job("David"));
        repo.save(new Job("Michelle"));

        Iterable<Job> jobs = repo.findAll();
        Assert.assertEquals(5, Iterables.size(jobs));

        Optional<Job> maybeJob = repo.findById(3L);
        Assert.assertNotNull(maybeJob.get());
        Assert.assertEquals("Kim", maybeJob.get().getName());

        jobs = repo.findByName("Chloe");
        Assert.assertEquals(1, Iterables.size(jobs));
        Job job = jobs.iterator().next();
        Assert.assertNotNull(job);
        Assert.assertEquals("Chloe", job.getName());
    }
}
