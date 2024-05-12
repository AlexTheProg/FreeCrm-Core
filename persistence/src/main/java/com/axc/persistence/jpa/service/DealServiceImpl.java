package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.Deal;
import com.axc.persistence.domain.charts.deals.DealExpense;
import com.axc.persistence.domain.charts.deals.DealPieChart;
import com.axc.persistence.domain.charts.deals.DealRevenue;
import com.axc.persistence.jpa.BaseJpaRepository;
import com.axc.persistence.jpa.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;


    @Override
    public BaseJpaRepository<Deal, Long> repository() {
        return dealRepository;
    }

    @Override
    public List<DealPieChart> findDealsForProjectPieChart() {
        return dealRepository.findDealsForProjectPieChart();
    }

    @Override
    public DealRevenue findDealBudgetSum() {
        return dealRepository.findDealBudgetSum();
    }

    @Override
    public DealExpense findDealExpenses() {
        return dealRepository.findDealExpenses();
    }
}
