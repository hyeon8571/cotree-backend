package com.futurenet.cotree.tree.service.growth;

import com.futurenet.cotree.tree.constants.TreePolicy;
import com.futurenet.cotree.tree.domain.Tree;
import com.futurenet.cotree.tree.service.exception.*;
import org.springframework.stereotype.Component;

@Component
public class GrowthValidator {

    public void validateCanGain(int point, Tree tree) {
        if(point < TreePolicy.WATER_COST) {
            throw new TreeException(TreeErrorCode.POINT_LACK);
        }

        if(!tree.canGainExp()) {
            throw new TreeException(TreeErrorCode.MAX_EXP_REACHED);
        }
    }

    public void validateAction(String action, String expected) {
        if(!expected.equalsIgnoreCase(action)) {
            throw new TreeException(TreeErrorCode.INVALID_ACTION);
        }
    }
}
