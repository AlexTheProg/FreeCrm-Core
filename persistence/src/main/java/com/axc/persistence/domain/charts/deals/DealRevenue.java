package com.axc.persistence.domain.charts.deals;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class DealRevenue {
    public String name = "Revenue";
    public BigDecimal revenue;

    public DealRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }
}
