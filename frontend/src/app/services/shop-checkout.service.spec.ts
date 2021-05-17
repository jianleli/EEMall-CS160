import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { ShopCheckoutService } from './shop-checkout.service';

const catcheditems={
  id: 1,
  name: "13.3-inch MacBook Air - 8GB Memory, 256GB SSD - Silver",
  imageUrl: "assets/images/products/Mac-Silver.png",
  unitPrice: 899,
  quantity: 1,
}
const catchedprice=899

const catchedcart=[
  {
    id: 1,
    name: "13.3-inch MacBook Air - 8GB Memory, 256GB SSD - Silver",
    imageUrl: "assets/images/products/Mac-Silver.png",
    unitPrice: 899,
    quantity: 1,
  },
  {
    id: 2,
    name: "13.3-inch MacBook Air - 16GB Memory, 256GB SSD - Silver",
    imageUrl: "assets/images/products/Mac-Silver.png",
    unitPrice: 1079,
    quantity: 1,
  },
]
const catchedtotalprice=1978
describe('ShopCheckoutService', () => {
  let service: ShopCheckoutService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientTestingModule,
        RouterTestingModule
      ]
    });
    service = TestBed.inject(ShopCheckoutService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('total price should be equal to the item price in the cart', ()=>{
    expect(catcheditems.unitPrice).toBe(catchedprice);
  });
  it('total price should be equal to the sum of the item price in the cart', ()=>{
    expect(catchedcart[0].unitPrice+catchedcart[1].unitPrice).toBe(catchedtotalprice);
  });

});
