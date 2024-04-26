package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "history_find")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class HistoryFind extends BaseEntity{
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "value_text")
    private String valueText;
}
