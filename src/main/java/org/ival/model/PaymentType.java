package org.ival.model;

import org.hibernate.annotations.GenericGenerator;
import org.ival.model.base.CreatedBase;

import javax.persistence.*;

@Entity
@Table(name = "payment_type")
public class PaymentType extends CreatedBase {

    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", nullable = false,length = 36)
    private String id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "code", length = 5, nullable = false)
    private String code;

    public PaymentType() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
