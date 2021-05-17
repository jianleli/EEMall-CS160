import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Laptop} from 'src/app/common/laptop';
import { ShopCartItem } from 'src/app/common/shop-cart-item';
import { LaptopService } from 'src/app/services/laptop.service';
import { ShopCartService } from 'src/app/services/shop-cart.service';

@Component({
  selector: 'app-laptop-details',
  templateUrl: './laptop-details.component.html',
  styleUrls: ['./laptop-details.component.css']
})
export class LaptopDetailsComponent implements OnInit {

  laptop: Laptop = new Laptop();

  constructor(private LaptopService: LaptopService, private route: ActivatedRoute, private cartService: ShopCartService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{


      const laptopId: number = +this.route.snapshot.paramMap.get('id');

      this.LaptopService.getLaptop(laptopId).subscribe(
        (res) => {
          this.laptop = res;
        }
      );
    })
  }


  addToLaptopDetailCart() {
    console.log(`Adding to cart: ${this.laptop.name}, ${this.laptop.unitPrice}`);
    const cartItem = new ShopCartItem(this.laptop);
    this.cartService.addToShopCart(cartItem);
  }

}
