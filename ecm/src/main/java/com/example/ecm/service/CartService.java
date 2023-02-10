package com.example.ecm.service;

import com.example.ecm.model.entity.Cart;
import com.example.ecm.model.request.cart.ConfirmCartRequest;
import com.example.ecm.model.response.cart.CartResponse;


public interface CartService {
    CartResponse addToCart(Long id, Integer amount);

    CartResponse incrementCartItem(Long cartItemId, Integer amount);

    CartResponse decrementCartItem(Long cartItemId, Integer amount);

    CartResponse fetchCart();

    CartResponse removeFromCart(Long id);

    boolean confirmCart(ConfirmCartRequest confirmCartRequest);

    Cart getCart();

    void saveCart(Cart cart);

    void emptyCart();

    Cart calculatePrice(Cart cart);

}
