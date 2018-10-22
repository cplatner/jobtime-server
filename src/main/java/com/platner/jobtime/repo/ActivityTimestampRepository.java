package com.platner.jobtime.repo;

import com.platner.jobtime.model.ActivityTimestamp;
import org.springframework.data.repository.CrudRepository;

public interface ActivityTimestampRepository extends CrudRepository<ActivityTimestamp, Long>
{
}
