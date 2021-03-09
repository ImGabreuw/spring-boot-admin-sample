package me.gabreuw.web_application.model.dto;

import lombok.Data;
import me.gabreuw.web_application.model.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CartDto {
    private Long id;
    private String name;
    private List<ItemDto> itemsDto = new ArrayList<>();

    public static CartDto from(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setName(cart.getName());
        cartDto.setItemsDto(cart.getItems().stream().map(ItemDto::from).collect(Collectors.toList()));
        return cartDto;
    }
}
