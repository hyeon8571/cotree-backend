package com.futurenet.cotree.tree.domain;

import com.futurenet.cotree.tree.constants.TreePolicy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Tree {
    private int exp;

    public boolean canGainExp() {
        return exp < TreePolicy.MAX_EXP;
    }

    public int getRemainingGainableUnits() {
        return (TreePolicy.MAX_EXP - exp) / TreePolicy.EXP_PER_WATER;
    }

    public void gainExp(int units) {
        int gainedExp = units * TreePolicy.EXP_PER_WATER;
        this.exp = Math.min(this.exp + gainedExp, TreePolicy.MAX_EXP);
    }


}
