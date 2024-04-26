package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import javax.persistence.*;

@Entity
@Table(name = "favorite")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class Favorite extends BaseEntity{

    @Column(name = "id_decor")
    private Long idDecor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_decor",referencedColumnName="id", nullable = false, insertable = false, updatable = false)
    private Decor decor;

    @Column(name = "id_user")
    private Long idUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user",referencedColumnName="id", nullable = false, insertable = false, updatable = false)
    private NguoiDung user;

}
