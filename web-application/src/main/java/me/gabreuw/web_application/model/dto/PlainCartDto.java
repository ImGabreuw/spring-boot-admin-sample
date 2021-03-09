package me.gabreuw.web_application.model.dto;

import lombok.Data;
import me.gabreuw.web_application.model.Cart;

@Data
public class PlainCartDto {
    private Long id;
    private String name;

    public static PlainCartDto from(Cart cart){
        PlainCartDto plainCartDto = new PlainCartDto();
        plainCartDto.setId(cart.getId());
        plainCartDto.setName(cart.getName());
        return plainCartDto;
    }
}
