package com.futurenet.cotree.member.service;

import com.futurenet.cotree.member.dto.request.OAuthSignupRequest;
import com.futurenet.cotree.shoppingbasket.service.ShoppingBasketService;
import com.futurenet.cotree.tree.service.TreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupFacadeServiceImpl implements SignupFacadeService {

    private final MemberAuthService memberAuthService;
    private final TreeService treeService;
    private final ShoppingBasketService shoppingBasketService;

    @Override
    @Transactional
    public Long signup(OAuthSignupRequest oAuthSignupRequest) {
        Long memberId = memberAuthService.registerMember(oAuthSignupRequest);
        treeService.registerTree(memberId);
        shoppingBasketService.registerShoppingBasket(memberId);

        return memberId;
    }
}
