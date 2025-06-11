package com.futurenet.cotree.tree.service;

import com.futurenet.cotree.greenpoint.service.GreenPointService;
import com.futurenet.cotree.tree.constants.TreePolicy;
import com.futurenet.cotree.tree.dto.request.GiveWaterRequest;
import com.futurenet.cotree.tree.dto.response.GiveWaterResponse;
import com.futurenet.cotree.tree.repository.TreeRepository;
import com.futurenet.cotree.tree.service.exception.*;
import com.sun.source.tree.Tree;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;
    private final GreenPointService greenPointService;

    @Override
    @Transactional
    public void registerTree(Long memberId) {
        treeRepository.saveTree(memberId);
    }

    @Override
    public Integer getMyTree(Long memberId) {
        Integer exp = treeRepository.getMyTree(memberId);
        if (exp == null) throw new TreeNotFoundException();
        return exp;
    }

    @Override
    @Transactional
    public GiveWaterResponse giveWater(Long memberId, GiveWaterRequest giveWaterRequest) {
        if (!"GIVE_WATER".equalsIgnoreCase(giveWaterRequest.getAction())) {
            throw new InvalidActionException();
        }

        int currentPoint = greenPointService.getPoint(memberId);
        if (currentPoint < TreePolicy.WATER_COST) {
            throw new PointLackException();
        }

        int currentExp = treeRepository.getExp(memberId);
        if (currentExp >= TreePolicy.MAX_EXP) {
            throw new MaxExpReachedException();
        }

        int nextExp = currentExp + TreePolicy.EXP_PER_WATER;
        if (nextExp > TreePolicy.MAX_EXP) {
            nextExp = TreePolicy.MAX_EXP;
        }

        int updated = treeRepository.updateExp(memberId, nextExp);
        if (updated != 1) {
            throw new TreeExpUpdateFailedException();
        }

        greenPointService.usePoint(memberId, TreePolicy.WATER_COST);
        int updatedPoint = greenPointService.getPoint(memberId);
        int remainingWaterUnit = updatedPoint / TreePolicy.WATER_COST;

        return GiveWaterResponse.of(nextExp, remainingWaterUnit);
    }
}
