import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { ShopCartItem } from '../common/shop-cart-item';

@Injectable({
  providedIn: 'root'
})
export class ShopCartService {

  shopCartItems: ShopCartItem[] = [];

  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);
  orderDone_orderTrackingNumber: Subject<string> = new BehaviorSubject<string>("");
  
  constructor() { }

  addToShopCart(cartItem: ShopCartItem){
    // check if we already have the laptop in our shopping cart
    let theItemIsInShopCart: boolean = false;
    let existingShopCartProduct: ShopCartItem = null;

    if (this.shopCartItems.length > 0){
      //find out the laptop in the cart based on its id

      for (let tempItem of this.shopCartItems) {
        if (tempItem.id === cartItem.id) {
          existingShopCartProduct = tempItem;
          break;
        }
      }


      if (existingShopCartProduct != null) {
        theItemIsInShopCart = true;
      } else {
        theItemIsInShopCart = false;
      }

    }

    if (theItemIsInShopCart) {
      // add item into the quantity
      existingShopCartProduct.quantity++;
    } else {
      //push my item into the item array
      this.shopCartItems.push(cartItem);
    }


    this.countCartTotals();
  }



  countCartTotals(){
    let tempTotalPrice: number = 0;
    let tempTotalQuantity: number = 0;


    this.shopCartItems.forEach((curItem) => {
      tempTotalPrice = tempTotalPrice + curItem.quantity * curItem.unitPrice;
      tempTotalQuantity = tempTotalQuantity + curItem.quantity;
    });



    this.totalPrice.next(tempTotalPrice);
    this.totalQuantity.next(tempTotalQuantity);


    this.printCartData(tempTotalPrice, tempTotalQuantity);
  }

  printCartData(tempTotalPrice: number, tempTotalQuantity: number) {

    for (let tempItem of this.shopCartItems) {
      const subTotalPrice = tempItem.quantity * tempItem.unitPrice;

    }

  }

  decreaseQuantity(reducedCartItem: ShopCartItem) {

    reducedCartItem.quantity = reducedCartItem.quantity - 1;

    if (reducedCartItem.quantity === 0) {
      this.delete(reducedCartItem);
    } else {
      this.countCartTotals();
    } 
  }

  delete(tempItem: ShopCartItem) {
    const idx = this.shopCartItems.findIndex(
      (ele) => (ele.id === tempItem.id)
    );

    if (idx > -1){
      this.shopCartItems.splice(idx, 1);

      this.countCartTotals();
    }
  }
}
