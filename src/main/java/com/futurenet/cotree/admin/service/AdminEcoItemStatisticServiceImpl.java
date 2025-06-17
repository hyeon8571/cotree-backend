package com.futurenet.cotree.admin.service;

import com.futurenet.cotree.admin.dto.response.EcoPurchaseAgeResponse;
import com.futurenet.cotree.admin.dto.response.EcoPurchaseCountResponse;
import com.futurenet.cotree.admin.dto.response.EcoPurchaseGenderResponse;
import com.futurenet.cotree.admin.repository.AdminEcoItemStatisticRepository;
import com.futurenet.cotree.item.constants.ItemClassification;
import com.futurenet.cotree.member.constant.MemberAge;
import com.futurenet.cotree.member.constant.MemberGender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEcoItemStatisticServiceImpl implements AdminEcoItemStatisticService {

    private final AdminEcoItemStatisticRepository adminEcoItemStatisticRepository;

    @Override
    public List<EcoPurchaseCountResponse> getEcoPurchaseCount() {
        return Arrays.asList(
                new EcoPurchaseCountResponse(ItemClassification.GENERAL, adminEcoItemStatisticRepository.getGeneralOrderItemCount()),
                new EcoPurchaseCountResponse(ItemClassification.ECO, adminEcoItemStatisticRepository.getEcoOrderItemCount())
        );
    }

    @Override
    public List<EcoPurchaseAgeResponse> getEcoPurchaseAgeCount() {
        List<EcoPurchaseAgeResponse> result = new ArrayList<>();

        for (MemberAge ageEnum : MemberAge.values()) {
            int count = adminEcoItemStatisticRepository.getEcoOrderItemCountByAge(ageEnum.getValue());
            result.add(new EcoPurchaseAgeResponse(ageEnum, count));
        }
        return result;
    }

    @Override
    public List<EcoPurchaseGenderResponse> getEcoPurchaseGenderCount() {
        return Arrays.asList(
                new EcoPurchaseGenderResponse(MemberGender.M, adminEcoItemStatisticRepository.getEcoOrderItemCountByGender(MemberGender.M)),
                new EcoPurchaseGenderResponse(MemberGender.F, adminEcoItemStatisticRepository.getEcoOrderItemCountByGender(MemberGender.F))
        );
    }
}
