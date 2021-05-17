import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Laptop } from '../common/laptop';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { LaptopCategory } from '../common/laptop-category';

@Injectable({
  providedIn: 'root'
})
export class LaptopService {

  private baseUrl = 'http://localhost:8080/product';


  private categoryUrl = 'http://localhost:8080/categories';

  constructor(private httpClient: HttpClient) { }

  getLaptopList(categoryId: number): Observable<Laptop[]>{


    const searchUrl = `${this.baseUrl}/search/findByCategoryId?id=${categoryId}`;

    return this.getLaptops(searchUrl);
  }

  searchLaptops(key: string): Observable<Laptop[]> {


    const searchUrl = `${this.baseUrl}/search/findByNameContaining?name=${key}`;

    return this.getLaptops(searchUrl);
  }

  private getLaptops(searchUrl: string): Observable<Laptop[]> {
    return this.httpClient.get<GetResponseProducts>(searchUrl).pipe(

      map(response => response._embedded.products)
    );
  }

  getLaptopCategories(): Observable<LaptopCategory[]> {
    return this.httpClient.get<GetResponseProductCategory>(this.categoryUrl).pipe(
      //this.httpClient.get<any>
      map(response => response._embedded.productCategories)
    );
  }

  getLaptop(laptopId: number): Observable<Laptop> {
    //create a based URL on product id
    const laptopUrl = `${this.baseUrl}/${laptopId}`;
    return this.httpClient.get<Laptop>(laptopUrl);
  }

  getLaptopListPaginate(thePage: number, thePageSize: number, categoryId: number): Observable<GetResponseProducts>{

    //need to build URL based on category id, page and size
    const searchUrl = categoryId == 0 ? `${this.baseUrl}?page=${thePage}&size=${thePageSize}` 
    : `${this.categoryUrl}/${categoryId}?page=${thePage}&size=${thePageSize}`;

    return this.httpClient.get<GetResponseProducts>(searchUrl);
  }

  searchLaptopsPaginate(pageNum: number, pageSize: number, keyword: string): Observable<GetResponseProducts> {

    // need to build URL based on keyword, page and size 
    const searchUrl = `${this.baseUrl}/search?keyword=${keyword}` + `&page=${pageNum}&size=${pageSize}`;

    return this.httpClient.get<GetResponseProducts>(searchUrl);
  }

}

interface GetResponseProducts {
  _embedded: {
    products: Laptop[];
  },
  page: {
    size: number,
    totalElements:number,
    totalPages:number,
    number: number
  }
}

interface GetResponseProductCategory {
  _embedded: {
    productCategories: LaptopCategory[];
  }
}
