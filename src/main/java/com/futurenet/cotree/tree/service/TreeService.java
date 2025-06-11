package com.futurenet.cotree.tree.service;

public interface TreeService {
    void registerTree(Long memberId);
    Integer getMyTree(Long memberId);
}
