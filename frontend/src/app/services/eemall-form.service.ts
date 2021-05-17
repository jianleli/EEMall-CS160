import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Country } from '../common/country';
import { State } from '../common/state';


@Injectable({
  providedIn: 'root'
})
export class EEmallFormService {

  private countryUrl = 'http://localhost:8080/api/countries';
  private stateUrl = 'http://localhost:8080/api/states';

  constructor(private httpClient: HttpClient) { }

  getCreditCardMonths(beginMonth: number): Observable<number[]> {
    let months: number[] = [];
    // create an array for Months dropdown list
    for (let tempMonth = beginMonth; tempMonth <= 12; tempMonth++) {
      months.push(tempMonth);
    }
    return of(months);
  }


  getCreditCardYears(): Observable<number[]> {

    let years: number[] = [];
    // Create an array for Years downlist list
    const beginYear: number = new Date().getFullYear();
    const endYear: number = beginYear + 10;

    for (let tempYear = beginYear; tempYear <= endYear; tempYear++) {
      years.push(tempYear);
    }
    return of(years);
  }

  getCountries(): Observable<Country[]> {
    return this.httpClient.get<GetResponseCountries>(this.countryUrl).pipe(
      map(res => res._embedded.countries)
    );
  }


  getStates(couCode: string): Observable<State[]> {

    // search url
    const searchStatesUrl = `${this.stateUrl}/search/findByCountryCode?code=${couCode}`;

    return this.httpClient.get<GetResponseStates>(searchStatesUrl).pipe(
      map(response => response._embedded.states)
    );
  }
}




interface GetResponseCountries {
  _embedded: {
    countries: Country[];
  }
}

interface GetResponseStates {
  _embedded: {
    states: State[];
  }
}