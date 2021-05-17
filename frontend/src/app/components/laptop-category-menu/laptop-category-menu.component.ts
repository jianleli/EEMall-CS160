import { Component, OnInit } from '@angular/core';
import { LaptopCategory } from 'src/app/common/laptop-category';
import { LaptopService } from 'src/app/services/laptop.service';

@Component({
  selector: 'app-laptop-category-menu',
  templateUrl: './laptop-category-menu.component.html',
  styleUrls: ['./laptop-category-menu.component.css']
})
export class LaptopCategoryMenuComponent implements OnInit {

  laptopCategories: LaptopCategory[];

  constructor(private laptopService: LaptopService) { }

  ngOnInit(): void {
    this.laptopService.getLaptopCategories().subscribe(
      arrData => {
        console.log('Product Categories=' + JSON.stringify(arrData));
        this.laptopCategories = arrData;
      }
    );
  }
}
