import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';

import { ShopCartStatusComponent } from './shop-cart-status.component';

describe('ShopCartStatusComponent', () => {
  let component: ShopCartStatusComponent;
  let fixture: ComponentFixture<ShopCartStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShopCartStatusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopCartStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component.totalItemPrice=+5
    component.totalItemQuantity+=3
  });
  afterEach(()=>{
    component.totalItemPrice=0
    component.totalItemQuantity=0
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('test initial',()=>{
    expect(component.totalItemPrice).toBe(5)
    expect(component.totalItemQuantity).toBe(3)
  });

  it('should contain tag', ()=>{
    const ele= fixture.debugElement.query(By.css('span'));
    expect(ele.componentInstance).toBeInstanceOf(ShopCartStatusComponent)
  })
});
