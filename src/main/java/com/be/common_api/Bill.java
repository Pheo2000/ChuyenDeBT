package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import javax.persistence.*;

@Entity
@Table(name = "bill")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class Bill extends BaseEntity{

    @Column(name = "id_user")
    private Long idUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user",referencedColumnName="id", nullable = false, insertable = false, updatable = false)
    private NguoiDung user;

    @Column(name = "total")
    private Integer total;

    @Column(name = "method_payment")
    private Short methodPayment;

    @Column(name = "status")
    private Short status;
}
