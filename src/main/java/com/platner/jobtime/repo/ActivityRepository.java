package com.platner.jobtime.repo;

import com.platner.jobtime.model.Activity;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, Long>
{
}
