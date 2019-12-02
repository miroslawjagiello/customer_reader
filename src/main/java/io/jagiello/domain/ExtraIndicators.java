package io.jagiello.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtraIndicators {
    @Id
    private Long customerId;
    private BigDecimal lmgzc;
    private BigDecimal dxcf;

}
