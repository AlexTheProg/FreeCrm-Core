package com.axc.persistence.domain.charts.deals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class DealPieChart {
    public DealPieChartData dealData;
    public String companyName;

    public DealPieChart(BigDecimal budget, BigDecimal estimatedCosts, BigDecimal projectedProfits, String companyName) {
        this.dealData = new DealPieChartData(budget, estimatedCosts, projectedProfits);
        this.companyName = companyName;
    }
}
