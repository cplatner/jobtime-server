package com.platner.jobtime.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="activity")
public class Activity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    //@Column(name = "name", nullable = false, length = 255)
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    // @OneToMany
    // private Set<ActivityTimestamp> timestamps;

    // public Set<ActivityTimestamp> getTimestamps()
    // {
    //     return timestamps;
    // }

    // public void setTimestamps(Set<ActivityTimestamp> timestamps)
    // {
    //     this.timestamps = timestamps;
    // }

}
