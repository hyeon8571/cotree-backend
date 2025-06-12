package com.futurenet.cotree.tree.service.growth;

import com.futurenet.cotree.tree.constants.TreePolicy;
import com.futurenet.cotree.tree.domain.Tree;
import com.futurenet.cotree.tree.service.exception.InvalidActionException;
import com.futurenet.cotree.tree.service.exception.MaxExpReachedException;
import com.futurenet.cotree.tree.service.exception.PointLackException;
import org.springframework.stereotype.Component;

@Component
public class GrowthValidator {

    public void validateCanGain(int point, Tree tree) {
        if(point < TreePolicy.WATER_COST) {
            throw new PointLackException();
        }

        if(!tree.canGainExp()) {
            throw new MaxExpReachedException();
        }
    }

    public void validateAction(String action, String expected) {
        if(!expected.equalsIgnoreCase(action)) {
            throw new InvalidActionException();
        }
    }
}
