import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';

import { LaptopDetailsComponent } from './laptop-details.component';

describe('LaptopDetailsComponent', () => {
  let component: LaptopDetailsComponent;
  let fixture: ComponentFixture<LaptopDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LaptopDetailsComponent ],
      imports:[
        HttpClientTestingModule,
        RouterTestingModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LaptopDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should be a add to cart button', ()=>{
    const bt=fixture.debugElement.queryAll(By.css('button'));
    const btname =bt[0].nativeElement;
    expect(btname.textContent).toBe('Add to cart');
  })
});
