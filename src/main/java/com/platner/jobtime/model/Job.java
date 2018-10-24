package com.platner.jobtime.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
//@Table(name="JOB")
public class Job
{
    protected Job()
    {
    }

    public Job(String name)
    {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;
    // @Column(name = "name")
    private String name;

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        return String.format("Job[id=%d, name='%s']", id, name);
    }

    // @ManyToMany
    // @JoinTable(name = "job_category",
    //         joinColumns = {@JoinColumn(name = "job_id", nullable = false, updatable = false)},
    //         inverseJoinColumns = {@JoinColumn(name = "category_id", nullable = false, updatable = false)})
    // private Set<Category> categories;

    // public Set<Category> getCategories()
    // {
    //     return this.categories;
    // }

    // public void setCategories(Set<Category> categories)
    // {
    //     this.categories = categories;
    // }
}
