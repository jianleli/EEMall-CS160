import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NodeWithI18n } from '@angular/compiler';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { Laptop } from 'src/app/common/laptop';

import { LaptopListComponent } from './laptop-list.component';

describe('LaptopListComponent', () => {
  let component: LaptopListComponent;
  let fixture: ComponentFixture<LaptopListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LaptopListComponent ],
      imports:[
        HttpClientTestingModule,
        RouterTestingModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LaptopListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show 100 list item when I have 100 product', ()=>{
    const products: Laptop[]=[];
    for(let i=0; i<100; i++){
      let date1 = new Date(1478708162000);
      let date2 = new Date(1478708162222);
      products.push(
        {
          id:'abc'+1, sku:'pc-101', name:'item1', description:'111', unitPrice:100, imageUrl:'', active:true, unitsInStock:1, dateCreated:date1, lastUpdated:date2 
        }
      );
    }
    component.laptops=products;
    fixture.detectChanges();
    const listItem=fixture.debugElement.queryAll(By.css('p'));
    expect(products.length).toBe(100);
  })
});
