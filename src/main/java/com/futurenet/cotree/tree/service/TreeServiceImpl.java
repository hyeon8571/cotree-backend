package com.futurenet.cotree.tree.service;

import com.futurenet.cotree.tree.repository.TreeRepository;
import com.futurenet.cotree.tree.service.exception.TreeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;

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
}
