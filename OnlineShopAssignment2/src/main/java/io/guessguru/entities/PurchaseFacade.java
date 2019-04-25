package io.guessguru.entities;

import java.util.ArrayList;

public interface PurchaseFacade{

boolean placeOrder(ArrayList<CartItems> cart_items);
}