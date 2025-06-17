package com.futurenet.cotree.tree.service;

import com.futurenet.cotree.greenpoint.service.GreenPointService;
import com.futurenet.cotree.order.repository.OrderItemRepository;
import com.futurenet.cotree.tree.constants.TreePolicy;
import com.futurenet.cotree.tree.domain.Tree;
import com.futurenet.cotree.tree.dto.request.GiveWaterRequest;
import com.futurenet.cotree.tree.dto.response.GiveWaterResponse;
import com.futurenet.cotree.tree.dto.response.MyTreeResponse;
import com.futurenet.cotree.tree.dto.response.MyTreeSummaryResponse;
import com.futurenet.cotree.tree.repository.TreeRepository;
import com.futurenet.cotree.tree.service.exception.*;
import com.futurenet.cotree.tree.service.growth.GrowthCalculator;
import com.futurenet.cotree.tree.service.growth.GrowthPlan;
import com.futurenet.cotree.tree.service.growth.GrowthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;
    private final GreenPointService greenPointService;
    private final OrderItemRepository orderItemRepository;
    private final GrowthValidator growthValidator;
    private final GrowthCalculator growthCalculator;

    @Override
    @Transactional
    public void registerTree(Long memberId) {
        treeRepository.saveTree(memberId);
    }

    @Override
    public MyTreeResponse getMyTree(Long memberId) {
        Integer exp = treeRepository.getMyTree(memberId);
        if (exp == null) throw new TreeException(TreeErrorCode.TREE_NOT_FOUND);
        int point = greenPointService.getPoint(memberId);
        int remainingWaterUnit = point / TreePolicy.WATER_COST;
        return MyTreeResponse.of(exp, remainingWaterUnit);
    }

    @Override
    @Transactional
    public GiveWaterResponse giveWater(Long memberId, GiveWaterRequest giveWaterRequest) {
        growthValidator.validateAction(giveWaterRequest.getAction(), "GIVE_WATER");

        int currentPoint = greenPointService.getPoint(memberId);
        int currentExp = treeRepository.getTreeExp(memberId);
        Tree tree = new Tree(currentExp);

        growthValidator.validateCanGain(currentPoint, tree);

        GrowthPlan growthPlan = growthCalculator.calculate(tree, currentPoint, false);
        if (growthPlan.isPointInsufficient()) throw new TreeException(TreeErrorCode.POINT_LACK);
        if (growthPlan.isMaxExpReached()) throw new TreeException(TreeErrorCode.MAX_EXP_REACHED);
        if (growthPlan.isEmpty()) throw new TreeException(TreeErrorCode.TREE_EXP_UPDATE_FAILED);

        tree.gainExp(growthPlan.waterUnits());

        updateExp(memberId, tree.getExp());
        greenPointService.usePoint(memberId, TreePolicy.WATER_COST);

        return buildResponse(memberId, tree.getExp());
    }

    @Override
    @Transactional
    public GiveWaterResponse giveAllWater(Long memberId, GiveWaterRequest giveWaterRequest) {
        growthValidator.validateAction(giveWaterRequest.getAction(), "GIVE_ALL_WATER");

        int currentPoint = greenPointService.getPoint(memberId);
        int currentExp = treeRepository.getTreeExp(memberId);
        Tree tree = new Tree(currentExp);

        growthValidator.validateCanGain(currentPoint, tree);

        GrowthPlan growthPlan = growthCalculator.calculate(tree, currentPoint, true);
        if (growthPlan.isPointInsufficient()) throw new TreeException(TreeErrorCode.POINT_LACK);
        if (growthPlan.isMaxExpReached()) throw new TreeException(TreeErrorCode.MAX_EXP_REACHED);
        if (growthPlan.isEmpty()) throw new TreeException(TreeErrorCode.TREE_EXP_UPDATE_FAILED);

        tree.gainExp(growthPlan.waterUnits());

        updateExp(memberId, tree.getExp());
        greenPointService.usePoint(memberId, growthPlan.waterUnits() * TreePolicy.WATER_COST);

        return buildResponse(memberId, tree.getExp());
    }

    @Override
    public MyTreeSummaryResponse getMyTreeSummary(Long memberId) {
        if(memberId==null) {
            throw new TreeException(TreeErrorCode.UNAUTHORIZED_SUMMARY_REQUEST);
        }
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfMonth(1);
        LocalDate end = today.withDayOfMonth(today.lengthOfMonth());
        int exp = treeRepository.getTreeExp(memberId);
        int ecoCount = orderItemRepository.getEcoItemPurchaseCountThisMonth(memberId, start, end);
        return new MyTreeSummaryResponse(exp, ecoCount);
    }

    private void updateExp(Long memberId, int exp) {
        int updated = treeRepository.updateExp(memberId, exp);
        if (updated != 1) {
            throw new TreeException(TreeErrorCode.TREE_EXP_UPDATE_FAILED);
        }
    }

    private GiveWaterResponse buildResponse(Long memberId, int exp) {
        int updatedPoint = greenPointService.getPoint(memberId);
        int remainingWaterUnit = updatedPoint / TreePolicy.WATER_COST;
        return GiveWaterResponse.of(exp, remainingWaterUnit);
    }

}
