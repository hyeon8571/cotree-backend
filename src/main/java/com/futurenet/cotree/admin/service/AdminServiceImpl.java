package com.futurenet.cotree.admin.service;

import com.futurenet.cotree.admin.constants.StatPeriod;
import com.futurenet.cotree.admin.dto.response.InsightOverviewResponse;
import com.futurenet.cotree.admin.dto.response.PointStat;
import com.futurenet.cotree.greenpoint.repository.GreenPointRepository;
import com.futurenet.cotree.item.repository.ItemRepository;
import com.futurenet.cotree.member.repository.MemberRepository;
import com.futurenet.cotree.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final GreenPointRepository greenPointRepository;

    @Override
    public InsightOverviewResponse getInsightOverview() {
        LocalDate from = LocalDate.now().minusDays(29);
        LocalDate to = LocalDate.now().plusDays(1);

        long revenue = paymentRepository.getMonthlyRevenue(from, to);
        long orderCount = paymentRepository.getMonthlyOrderCount(from, to);
        long newUserCount = memberRepository.getNewMemberCount(from, to);
        long totalItems = itemRepository.getTotalItemCount();
        long ecoItems = itemRepository.getTotalEcoItemCount();
        int ecoRate = totalItems == 0 ? 0 : (int) ((ecoItems * 100.0) / totalItems);

        return InsightOverviewResponse.builder()
                .totalRevenue(revenue)
                .newUserCount(newUserCount)
                .totalOrderCount(orderCount)
                .ecoProductRate(ecoRate)
                .build();
    }

    @Override
    public List<PointStat> getPointStatsByRange(String range) {
        StatPeriod period = StatPeriod.from(range);
        LocalDate today = LocalDate.now();
        LocalDate from = today.minusDays(period.getDays() - 1);
        LocalDate to = today.plusDays(1);
        return greenPointRepository.getPointStatsByRange(from, to);
    }
}
