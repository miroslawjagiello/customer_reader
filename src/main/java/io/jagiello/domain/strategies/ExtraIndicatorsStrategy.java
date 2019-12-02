package io.jagiello.domain.strategies;

import io.jagiello.domain.CustomerView;

import java.math.BigDecimal;

public interface ExtraIndicatorsStrategy {
    BigDecimal countLmgzc(CustomerView customer);
    BigDecimal countDxcf(CustomerView customer);
}
