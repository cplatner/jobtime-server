package com.platner.jobtime.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
public class ActivityTimestamp
{
    protected ActivityTimestamp()
    {
    }

    public ActivityTimestamp(LocalDateTime start, LocalDateTime stop)
    {
        this.start = start;
        this.stop = stop;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private LocalDateTime start;

    public LocalDateTime getStart()
    {
        return start;
    }

    public void setStart(LocalDateTime start)
    {
        this.start = start;
    }

    private LocalDateTime stop;

    public LocalDateTime getStop()
    {
        return stop;
    }

    public void setStop(LocalDateTime stop)
    {
        this.stop = stop;
    }

    // @ManyToOne
    // private Activity activity;
    // public Activity getActivity()
    // {
    //     return activity;
    // }

    // public void setActivity(Activity activity)
    // {
    //     this.activity = activity;
    // }
}
