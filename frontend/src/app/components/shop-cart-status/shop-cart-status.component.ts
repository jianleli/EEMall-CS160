import { Component, OnInit } from '@angular/core';
import { ShopCartService } from 'src/app/services/shop-cart.service';

@Component({
  selector: 'app-shop-cart-status',
  templateUrl: './shop-cart-status.component.html',
  styleUrls: ['./shop-cart-status.component.css']
})
export class ShopCartStatusComponent implements OnInit {

  totalItemPrice: number = 0.00;
  totalItemQuantity: number = 0;

  constructor(private shopCartService: ShopCartService) { }
  
  ngOnInit(){
      // subscribe to the shop cart totalPrice
      this.shopCartService.totalPrice.subscribe(
        (res) => this.totalItemPrice = res
      );
    
      // subscribe to the shop cart totalQuantity
      this.shopCartService.totalQuantity.subscribe(
        (res) => this.totalItemQuantity = res
      );
  }
}
