import { TestBed } from '@angular/core/testing';

import { ShopCartService } from './shop-cart.service';

describe('ShopCartService', () => {
  let service: ShopCartService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShopCartService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  // it('testing function',()=>{
  //   cartitems=
  //   expect(service.decreaseQuantity()).toBe()
  // }
  // )
});
