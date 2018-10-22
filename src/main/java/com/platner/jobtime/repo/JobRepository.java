package com.platner.jobtime.repo;

import java.util.List;

import com.platner.jobtime.model.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long>
{
    List<Job> findByName(String name);
}
