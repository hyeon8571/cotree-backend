package com.futurenet.cotree.greenpoint.service;

import com.futurenet.cotree.greenpoint.repository.GreenPointRepository;
import com.futurenet.cotree.greenpoint.service.exception.GreenPointInsertFailedException;
import com.futurenet.cotree.greenpoint.service.exception.NotEnoughPointException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GreenPointServiceImpl implements GreenPointService {
    private final GreenPointRepository greenPointRepository;

    @Override
    public int getPoint(Long memberId) {
        return greenPointRepository.getPoint(memberId);
    }

    @Override
    @Transactional
    public void usePoint(Long memberId, int amount) {
        int currentPoint = getPoint(memberId);
        if (currentPoint < amount) {
            throw new NotEnoughPointException();
        }

        int result = greenPointRepository.savePointUsageLog(memberId, -amount);
        if (result != 1) {
            throw new GreenPointInsertFailedException();
        }
    }
}
