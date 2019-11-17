package io.jagiello.domain;

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
public class ExtraIndicators {
    @Id
    private Long customerId;
    private BigDecimal lmgzc;
    private BigDecimal dxcf;

    public ExtraIndicators(Long customerId, BigDecimal lmgzc, BigDecimal dxcf) {
        this.customerId = customerId;
        this.lmgzc = lmgzc;
        this.dxcf = dxcf;
    }
}
