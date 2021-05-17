import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopCartDetailComponent } from './shop-cart-detail.component';

describe('ShopCartDetailComponent', () => {
  let component: ShopCartDetailComponent;
  let fixture: ComponentFixture<ShopCartDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShopCartDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopCartDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
