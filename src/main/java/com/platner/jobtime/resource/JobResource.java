package com.platner.jobtime.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class JobResource extends ResourceSupport
{
    private Long jobId;
    private String jobName;

    @JsonCreator
    public JobResource(@JsonProperty("jobId") Long jobId, @JsonProperty("jobName") String jobName)
    {
        this.jobId = jobId;
        this.jobName = jobName;
    }

    public Long getJobId()
    {
        return jobId;
    }

    public String getJobName()
    {
        return jobName;
    }
}
