import { Component, OnInit } from '@angular/core';
import { ShopCartItem } from 'src/app/common/shop-cart-item';
import { ShopCartService } from 'src/app/services/shop-cart.service';

@Component({
  selector: 'app-shop-cart-detail',
  templateUrl: './shop-cart-detail.component.html',
  styleUrls: ['./shop-cart-detail.component.css']
})
export class ShopCartDetailComponent implements OnInit {

  shoppingCartItems: ShopCartItem[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;

  constructor(private shoppingCartService: ShopCartService) { }

  ngOnInit(): void {
    this.displayShopCartDetails();
  }

  displayShopCartDetails(){

    this.shoppingCartItems = this.shoppingCartService.shopCartItems;

    // subscribe to the cart totalPrice
    this.shoppingCartService.totalPrice.subscribe(
      (res) => {
        this.totalPrice = res
      }
    );


    this.shoppingCartService.totalQuantity.subscribe(
      (res) => {
        this.totalQuantity = res
      }
    );


    this.shoppingCartService.countCartTotals();
  }

  addQuantity(addedCartItem: ShopCartItem){
    this.shoppingCartService.addToShopCart(addedCartItem);
  }

  reduceQuantity(reducedCartItem: ShopCartItem) {
    this.shoppingCartService.decreaseQuantity(reducedCartItem)
  }

  deleteFromCart(dItem: ShopCartItem){
    this.shoppingCartService.delete(dItem);
  }
}
