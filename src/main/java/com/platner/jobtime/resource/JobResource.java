package com.platner.jobtime.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platner.jobtime.model.Job;
import org.springframework.hateoas.ResourceSupport;

public class JobResource extends ResourceSupport
{
    private  String content;

    @JsonIgnore
    private Job job;

    @JsonCreator
    public JobResource(@JsonProperty("job") Job job)
    {
        this.job = job;
        ObjectMapper mapper = new ObjectMapper();

        try {
            this.content = mapper.writeValueAsString(job);
        } catch (JsonProcessingException e) {
            //*
        }
    }

    public String getContent()
    {
        return content;
    }
}
