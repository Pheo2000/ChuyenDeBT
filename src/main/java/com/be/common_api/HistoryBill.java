package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "history_bill")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class HistoryBill extends BaseEntity{

    @Column(name = "id_bill")
    private Long idBill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_bill",referencedColumnName="id", nullable = false, insertable = false, updatable = false)
    private Bill bill;

    @Column(name = "status")
    private Short status;
}
