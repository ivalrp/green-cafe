package org.ival.model;

import org.hibernate.annotations.GenericGenerator;
import org.ival.model.base.CreatedBase;

import javax.persistence.*;

@Entity
@Table(name = "job_position")
public class JobPosition extends CreatedBase {

    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", nullable = false,length = 36)
    private String id;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "salary", nullable = false)
    private Double salary;

    public JobPosition() {
        super();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getSalary() {
        return salary;
    }
}
