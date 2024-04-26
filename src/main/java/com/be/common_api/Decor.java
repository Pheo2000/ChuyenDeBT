package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import javax.persistence.*;

@Entity
@Table(name = "decor")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class Decor extends BaseEntity{
    @Column(name = "id_decor_type")
    private Long idDecorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_decor_type",referencedColumnName="id", nullable = false, insertable = false, updatable = false)
    private DecorType decorType;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "description_decor")
    private String descriptionDecor;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "status")
    private Short status;

}
