package com.futurenet.cotree.admin.service;

import com.futurenet.cotree.admin.dto.response.*;
import com.futurenet.cotree.admin.repository.AdminEcoItemStatisticRepository;
import com.futurenet.cotree.item.constants.ItemClassification;
import com.futurenet.cotree.member.constant.MemberAge;
import com.futurenet.cotree.member.constant.MemberGender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEcoItemStatisticServiceImpl implements AdminEcoItemStatisticService {

    private final AdminEcoItemStatisticRepository adminEcoItemStatisticRepository;

    @Override
    @Transactional
    public List<EcoPurchaseCountResponse> getEcoPurchaseCount() {
        return Arrays.asList(
                new EcoPurchaseCountResponse(ItemClassification.GENERAL.getValue(), adminEcoItemStatisticRepository.getGeneralOrderItemCount()),
                new EcoPurchaseCountResponse(ItemClassification.ECO.getValue(), adminEcoItemStatisticRepository.getEcoOrderItemCount())
        );
    }

    @Override
    @Transactional
    public List<EcoPurchaseAgeResponse> getEcoPurchaseAgeCount() {
        List<EcoPurchaseAgeResponse> result = new ArrayList<>();

        for (MemberAge ageEnum : MemberAge.values()) {
            int count = adminEcoItemStatisticRepository.getEcoOrderItemCountByAge(ageEnum.getValue());
            result.add(new EcoPurchaseAgeResponse(ageEnum.getValue(), count));
        }
        return result;
    }

    @Override
    @Transactional
    public List<EcoPurchaseGenderResponse> getEcoPurchaseGenderCount() {
        return Arrays.asList(
                new EcoPurchaseGenderResponse(MemberGender.M.getValue(), adminEcoItemStatisticRepository.getEcoOrderItemCountByGender(MemberGender.M.getValue())),
                new EcoPurchaseGenderResponse(MemberGender.F.getValue(), adminEcoItemStatisticRepository.getEcoOrderItemCountByGender(MemberGender.F.getValue()))
        );
    }

    @Override
    @Transactional
    public List<PopularEcoItemResponse> getEcoPopularItem() {
        return adminEcoItemStatisticRepository.getPopularEcoItems();
    }

    @Override
    public List<EcoPurchaseCategoryResponse> getPurchaseCountByCategory() {
        return adminEcoItemStatisticRepository.getPurchaseByCategory();
    }


}
