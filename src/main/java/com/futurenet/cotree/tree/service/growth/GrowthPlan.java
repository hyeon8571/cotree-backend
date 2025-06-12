package com.futurenet.cotree.tree.service.growth;

public record GrowthPlan(int expToGain, int pointToUse, int waterUnits) {
    public boolean isPointInsufficient() {
        return pointToUse <= 0;
    }

    public boolean isMaxExpReached() {
        return expToGain <= 0;
    }

    public boolean isEmpty() {
        return isPointInsufficient() || isMaxExpReached() || waterUnits <= 0;
    }
}
