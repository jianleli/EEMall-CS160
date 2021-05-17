import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Component } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Location } from '@angular/common'
import { LaptopCategoryMenuComponent } from './laptop-category-menu.component';

describe('LaptopCategoryMenuComponent', () => {
  let component: LaptopCategoryMenuComponent;
  let fixture: ComponentFixture<LaptopCategoryMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LaptopCategoryMenuComponent ],
      imports:[
        HttpClientTestingModule,
        RouterTestingModule.withRoutes(
          [
            {path:'categories/{{ laptopCategory.id }}',component:dComponent}
          ]
        )
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LaptopCategoryMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate to / before click on categories', ()=>{
    const location=TestBed.get(Location);
    expect(location.path()).toBe('')
  })
});














@Component({template:''})
class dComponent{}
