package com.axc.persistence.domain.charts.deals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class DealPieChartData {
    public BigDecimal budget;
    public BigDecimal expenses;
    public BigDecimal projectedProfit;
}
