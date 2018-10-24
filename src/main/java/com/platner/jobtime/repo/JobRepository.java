package com.platner.jobtime.repo;

import com.platner.jobtime.model.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long>
{
    Iterable<Job> findByName(String name);
}
