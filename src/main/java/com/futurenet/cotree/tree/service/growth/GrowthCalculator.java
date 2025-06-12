package com.futurenet.cotree.tree.service.growth;

import com.futurenet.cotree.tree.constants.TreePolicy;
import com.futurenet.cotree.tree.domain.Tree;
import org.springframework.stereotype.Component;

@Component
public class GrowthCalculator {

    public GrowthPlan calculate(Tree tree, int currentPoint, boolean useAll) {
        int maxUnitsByGreenPoint = currentPoint / TreePolicy.WATER_COST;
        int maxUnitsByExp = tree.getRemainingGainableUnits();
        int usableUnits = useAll ? Math.min(maxUnitsByGreenPoint, maxUnitsByExp) : 1;
        int expToGain = usableUnits * TreePolicy.EXP_PER_WATER;
        int pointToUse = usableUnits * TreePolicy.WATER_COST;

        return new GrowthPlan(expToGain, pointToUse, usableUnits);
    }
}
