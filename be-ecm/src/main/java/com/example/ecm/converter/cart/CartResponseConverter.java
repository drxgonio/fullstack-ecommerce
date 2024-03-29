package com.example.ecm.converter.cart;

import com.example.ecm.model.dto.CartItemDTO;
import com.example.ecm.model.dto.ColorDTO;
import com.example.ecm.model.dto.DiscountDTO;
import com.example.ecm.model.entity.Cart;
import com.example.ecm.model.response.cart.CartResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CartResponseConverter implements Function<Cart, CartResponse> {

    @Override
    public CartResponse apply(Cart cart) {
        CartResponse cartResponse = new CartResponse();

        cartResponse.setCartItems(cart.getCartItemList()
                .stream()
                .map(cartItem -> CartItemDTO
                        .builder()
                        .id(cartItem.getId())
                        .url(cartItem.getProductVariant().getProduct().getUrl())
                        .name(cartItem.getProductVariant().getProduct().getName())
                        .price(cartItem.getProductVariant().getPrice())
                        .amount(cartItem.getAmount())
                        .thumb(cartItem.getProductVariant().getThumb())
                        .stock(cartItem.getProductVariant().getStock())
                        .color(ColorDTO.builder()
                                .name(cartItem.getProductVariant().getColor().getName())
                                .hex(cartItem.getProductVariant().getColor().getHex())
                                .build())
                        .build())
                .collect(Collectors.toList()));

        if (Objects.nonNull(cart.getDiscount())) {
            cartResponse.setDiscount(DiscountDTO
                    .builder()
                    .discountPercent(cart.getDiscount().getDiscountPercent())
                    .status(cart.getDiscount().getStatus())
                    .build()
            );
        }

        cartResponse.setTotalCartPrice(cart.getTotalCartPrice());
        cartResponse.setTotalCargoPrice(cart.getTotalCargoPrice());
        cartResponse.setTotalPrice(cart.getTotalPrice());
        return cartResponse;
    }
}
