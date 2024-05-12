package com.axc.persistence.domain.charts.deals;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class DealExpense {
    public String name = "Expenses";
    public BigDecimal rent;
    public BigDecimal salaries;
    public BigDecimal otherExpenses;

    public DealExpense(BigDecimal rent, BigDecimal salaries, BigDecimal otherExpenses) {
        this.rent = rent;
        this.salaries = salaries;
        this.otherExpenses = otherExpenses;
    }
}
