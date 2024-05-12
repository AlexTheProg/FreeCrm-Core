package com.axc.web.presentation.controllers.deal;


import com.axc.persistence.domain.charts.deals.DealExpense;
import com.axc.persistence.domain.charts.deals.DealPieChart;
import com.axc.persistence.domain.charts.deals.DealRevenue;
import com.axc.persistence.jpa.service.DealService;
import com.axc.web.presentation.controllers.task.TaskController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Deals", description = "Deal entity endpoints")
@RestController
@RequestMapping(value = DealController.API_URI)
@Validated
@Slf4j
@RequiredArgsConstructor
public class DealController {
    public static final String API_URI = "/api/deals";
    private final DealService dealService;

    @GetMapping()
    public List<DealPieChart> findForDealCharts() {
        return dealService.findDealsForProjectPieChart();
    }

    @GetMapping("/total-revenue")
    public DealRevenue findTotalRevenue() {
        return dealService.findDealBudgetSum();
    }

    @GetMapping("/expenses")
    public DealExpense findExpenses() {
        return dealService.findDealExpenses();
    }
}
