package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.Deal;
import com.axc.persistence.domain.charts.deals.DealExpense;
import com.axc.persistence.domain.charts.deals.DealPieChart;
import com.axc.persistence.domain.charts.deals.DealRevenue;
import com.axc.persistence.jpa.BaseJpaService;

import java.util.List;


public interface DealService extends BaseJpaService<Deal, Long> {

    List<DealPieChart> findDealsForProjectPieChart();

    DealRevenue findDealBudgetSum();

    DealExpense findDealExpenses();
}
