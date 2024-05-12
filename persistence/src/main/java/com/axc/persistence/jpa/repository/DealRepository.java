package com.axc.persistence.jpa.repository;

import com.axc.persistence.domain.Deal;
import com.axc.persistence.domain.charts.deals.DealExpense;
import com.axc.persistence.domain.charts.deals.DealPieChart;
import com.axc.persistence.domain.charts.deals.DealRevenue;
import com.axc.persistence.jpa.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DealRepository extends BaseJpaRepository<Deal, Long> {

    @Query("""
    select new com.axc.persistence.domain.charts.deals.DealPieChart(d.budget, d.estimatedCosts, d.projectedProfits, c.name) from Deal d
    join Branch b on d.branch = b
    join Company c on b.company = c
    """)
    List<DealPieChart> findDealsForProjectPieChart();

    @Query("""
    select new com.axc.persistence.domain.charts.deals.DealRevenue(sum(d.budget)) from Deal d
    """)
    DealRevenue findDealBudgetSum();

    @Query("""
    select new com.axc.persistence.domain.charts.deals.DealExpense(sum(d.estimatedCosts), sum(wm.salary), sum(w.rent)) from Deal d
    join WorkspaceMember wm on d.workspaceMember = wm
    join Workspace w on wm.workspace = w
    """)
    DealExpense findDealExpenses();
}
