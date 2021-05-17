import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Laptop } from 'src/app/common/laptop';
import { ShopCartItem } from 'src/app/common/shop-cart-item';
import { LaptopService } from 'src/app/services/laptop.service';
import { ShopCartService } from 'src/app/services/shop-cart.service';

@Component({
  selector: 'app-laptop-list',
  templateUrl: './laptop-list-grid.component.html',
  // templateUrl: './laptop-list.component.html',
  // templateUrl: './laptop-list-table.component.html',
  styleUrls: ['./laptop-list.component.css']
})
export class LaptopListComponent implements OnInit {

  laptops: Laptop[] = [];
  prevCategoryId: number = 1;
  curCategoryId: number = 1;
  search: boolean = false;

  pageNum: number = 1;
  elementsPerPage: number = 6;
  totalElements: number = 0;

  prevKeyWord: string = null;

  constructor(private laptopService: LaptopService, private shopCartService: ShopCartService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.showLaptops();
    })
  }

  showLaptops() {
    this.search = this.route.snapshot.paramMap.has('keyword');
    if (this.search) {
      this.handleSearchLaptops();
    } else {
      this.handleListLaptops();
    }
  }

  handleSearchLaptops() {
    const keyWord: string = this.route.snapshot.paramMap.get('keyword');


    if (this.prevKeyWord != keyWord){
      this.pageNum = 1;
    }

    this.prevKeyWord = keyWord;

    //now search for the products using keyword
    this.laptopService.searchLaptopsPaginate(
      this.pageNum - 1,
      this.elementsPerPage,
      keyWord).subscribe(
        (arrObj) => {
          this.laptops = arrObj._embedded.products;
          this.pageNum = arrObj.page.number + 1;
          this.elementsPerPage = arrObj.page.size;
          this.totalElements = arrObj.page.totalElements;
        }
      );
  }

  handleListLaptops() {

    const categoryIdFlag: boolean = this.route.snapshot.paramMap.has('id');


    this.curCategoryId = (categoryIdFlag) ? +this.route.snapshot.paramMap.get('id') : 0;


    if (this.prevCategoryId != this.curCategoryId) {
      this.pageNum = 1;      
    }

    this.prevCategoryId = this.curCategoryId;

    console.log(`currentCategoryId=${this.curCategoryId}, PageNumber=${this.pageNum}`)



    // now get the products for the given category id
    this.laptopService.getLaptopListPaginate(
                  this.pageNum - 1, 
                  this.elementsPerPage,
                  this.curCategoryId).subscribe( 
                    (arrObj) => {
                      this.laptops = arrObj._embedded.products;
                      this.pageNum = arrObj.page.number + 1;
                      this.elementsPerPage = arrObj.page.size;
                      this.totalElements = arrObj.page.totalElements;
                    }
                  );
  }


  changePageQuantity(theNumOfPages: number){
    this.elementsPerPage = theNumOfPages;
    this.pageNum = 1;
    this.showLaptops();
  }


  addToShoppingCart(templaptop: Laptop){
    console.log(`Adding to cart: ${templaptop.name}, ${templaptop.unitPrice}`)

    const tempCartItem = new ShopCartItem(templaptop);
    this.shopCartService.addToShopCart(tempCartItem);
  }

}
