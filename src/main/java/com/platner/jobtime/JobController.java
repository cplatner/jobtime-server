package com.platner.jobtime;

import com.platner.jobtime.model.Job;
import com.platner.jobtime.repo.JobRepository;
import com.platner.jobtime.resource.JobResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class JobController
{
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private JobRepository repository;

    public JobController(JobRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping(value = "/api/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Job> getAll()
    {
        return this.repository.findAll();
    }

    @GetMapping(value = "/api/job/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Job findById(@PathVariable Long id)
    {
        return this.repository.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/job", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobResource> createUser(@RequestBody Job job)
    {
        //* job object is updated with new id
        this.repository.save(job);
        JobResource jobResource = new JobResource(job.getId(), job.getName());
        jobResource.add(linkTo(methodOn(JobController.class).createUser(job)).withSelfRel());

        return new ResponseEntity<>(jobResource, HttpStatus.CREATED);
    }
}
