package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import javax.persistence.*;

@Entity
@Table(name = "decor_type")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class DecorType extends BaseEntity{
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private String image;
    @Column(name = "status")
    private Short status;
}
