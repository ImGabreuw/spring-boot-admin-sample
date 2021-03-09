package me.gabreuw.web_application.service;

import me.gabreuw.web_application.model.Cart;
import me.gabreuw.web_application.model.Item;
import me.gabreuw.web_application.model.exception.CartNotFoundException;
import me.gabreuw.web_application.model.exception.ItemIsAlreadyAssignedException;
import me.gabreuw.web_application.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ItemService itemService;

    @Autowired
    public CartService(CartRepository cartRepository, ItemService itemService) {
        this.cartRepository = cartRepository;
        this.itemService = itemService;
    }

    public Cart addCart(Cart cart){
        return cartRepository.save(cart);
    }

    public List<Cart> getCarts(){
        return StreamSupport
                .stream(cartRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Cart getCart(Long id){
        return cartRepository.findById(id).orElseThrow(() ->
                new CartNotFoundException(id));
    }

    public Cart deleteCart(Long id){
        Cart cart = getCart(id);
        cartRepository.delete(cart);
        return cart;
    }

    @Transactional
    public Cart editCart(Long id, Cart cart){
        Cart cartToEdit = getCart(id);
        cartToEdit.setName(cart.getName());
        return cartToEdit;
    }

    @Transactional
    public Cart addItemToCart(Long cartId, Long itemId){
        Cart cart = getCart(cartId);
        Item item = itemService.getItem(itemId);
        if(Objects.nonNull(item.getCart())){
            throw new ItemIsAlreadyAssignedException(itemId,
                    item.getCart().getId());
        }
        cart.addItem(item);
        item.setCart(cart);
        return cart;
    }

    @Transactional
    public Cart removeItemFromCart(Long cartId, Long itemId){
        Cart cart = getCart(cartId);
        Item item = itemService.getItem(itemId);
        cart.removeItem(item);
        return cart;
    }
}
