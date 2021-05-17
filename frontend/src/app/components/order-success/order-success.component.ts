import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from 'src/app/common/order';
import { ShopCartItem } from 'src/app/common/shop-cart-item';
import { ShopCartService } from 'src/app/services/shop-cart.service';
import { ShopCheckoutService } from 'src/app/services/shop-checkout.service';
import { CheckoutComponent } from '../checkout/checkout.component';

@Component({
  selector: 'app-order-success',
  templateUrl: './order-success.component.html',
  styleUrls: ['./order-success.component.css']
})
export class OrderSuccessComponent implements OnInit {

    orderDoneTrackingNumber: string = null;
    orderNum: Order = null;
    orderDoneCartItems: ShopCartItem[] = [];
    orderDoneTotalPrice: number = 0;
    orderDoneTotalQuantity: number = 0;

    constructor(private shoppingCartService: ShopCartService, private checkoutService :ShopCheckoutService, private router: Router) { }

    ngOnInit(): void {
      this.displayShopCartDetails();
    }

    displayShopCartDetails(){
      // get a handle to the cart items
      this.orderDoneCartItems = this.shoppingCartService.shopCartItems;

      // subscribe to the cart totalPrice
      this.shoppingCartService.totalPrice.subscribe(
        (res) => {
          this.orderDoneTotalPrice = res
        }
      );

      //subscribe to the cart totalQuantity
      this.shoppingCartService.totalQuantity.subscribe(
        (res) => {
          this.orderDoneTotalQuantity = res
        }
      );

      this.shoppingCartService.orderDone_orderTrackingNumber.subscribe(
        (res) => {
          this.orderDoneTrackingNumber = res;
        }
      )

      // compute cart total price and quantity
      this.shoppingCartService.countCartTotals();
    }

    fireSubmit(){
      console.log("onSubmit => fire!")
      
      // reset my shopping cart data
      this.shoppingCartService.shopCartItems = [];
      this.shoppingCartService.totalPrice.next(0);
      this.shoppingCartService.totalQuantity.next(0);
      // redirect back to the products page
      this.router.navigateByUrl("/product");
    }
}