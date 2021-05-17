import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Country } from 'src/app/common/country';
import { Order } from 'src/app/common/order';
import { OrderItem } from 'src/app/common/order-item';
import { Purchase } from 'src/app/common/purchase';
import { State } from 'src/app/common/state';
import { EEmallFormService } from 'src/app/services/eemall-form.service';
import { ShopCartService } from 'src/app/services/shop-cart.service';
import { ShopCheckoutService } from 'src/app/services/shop-checkout.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  LaptopsCheckoutFormGroup: FormGroup
  totalPrice: number = 0;
  totalQuantity: number = 0;

  creditCardYears: number[] = [];
  creditCardMonths: number[] = [];
  shipAddressStates: State[] = [];

  countries: Country[] = [];
  
  constructor(private formBuilder: FormBuilder, private router: Router, private shopCheckoutService :ShopCheckoutService, 
    private eeFormService :EEmallFormService, private shopCartService : ShopCartService) { 

  }

  ngOnInit(): void {

    this.checkShoppingCartDetails();
    


    

    const startm: number = new Date().getMonth() + 1;
    console.log("startMonth: " + startm);

    this.eeFormService.getCreditCardMonths(startm).subscribe((res) => {

        this.creditCardMonths = res;
    });


    this.eeFormService.getCreditCardYears().subscribe(
      data => {

        this.creditCardYears = data;
      }
    );

    //generate countries
    this.eeFormService.getCountries().subscribe((res) => {

      this.countries = res;
    });
  }


  checkShoppingCartDetails() {


    this.shopCartService.totalQuantity.subscribe((tq) => {
        this.totalQuantity = tq;
    });


    this.shopCartService.totalPrice.subscribe((tp) => {
        this.totalPrice = tp;
    });
  }


  onSubmit() {
    console.log("Handling the submit button");

    if (this.LaptopsCheckoutFormGroup.invalid) {
      this.LaptopsCheckoutFormGroup.markAllAsTouched();
      return;
    }

    
    //create an order
    let order = new Order();
    order.totalPrice = this.totalPrice;
    order.totalQuantity = this.totalQuantity;

    //get item from shopping cart
    const shopCartItems = this.shopCartService.shopCartItems;

    //create an order item
    let orderItems: OrderItem[] = [];
    for (let i=0; i < shopCartItems.length; i++) {
      orderItems[i] = new OrderItem(shopCartItems[i]);
    }



    console.log(this.LaptopsCheckoutFormGroup.get('customer').value);
    console.log("The email address is " + this.LaptopsCheckoutFormGroup.get('customer').value.email);

    console.log("The shipping address country is " + this.LaptopsCheckoutFormGroup.get('shipAddress').value.country.name);
    console.log("The shipping address state is " + this.LaptopsCheckoutFormGroup.get('shipAddress').value.state.name);

    //create a purchase
    let purchase = new Purchase();

    purchase.customer = this.LaptopsCheckoutFormGroup.controls['customer'].value;

    purchase.shipAddress = this.LaptopsCheckoutFormGroup.controls['shipAddress'].value;
    const shipState: State = JSON.parse(JSON.stringify(purchase.shipAddress.state));
    const shipCountry: Country = JSON.parse(JSON.stringify(purchase.shipAddress.country));
    purchase.shipAddress.state = shipState.name;
    purchase.shipAddress.country = shipCountry.name;




    purchase.order = order;
    purchase.orderItems = orderItems;



    this.shopCheckoutService.handleOrder(purchase).subscribe({
        next: (res) => {
          console.log(res);

          this.shopCartService.orderDone_orderTrackingNumber.next(res.orderTrackingNumber);
          console.log('This is Unit Test');



          this.router.navigateByUrl("/order-success");

        },
        error: (error) => {
          alert(`There was an error: ${error.message}`);
        }
      }
    );

  }



  selectedMonthsAndYears(){

    const creditCardFormGroup = this.LaptopsCheckoutFormGroup.get('creditCard');

    const curYear: number = new Date().getFullYear();
    const selectedYear: number = Number(creditCardFormGroup.value.expirationYear);

    let beginMonth: number;

    beginMonth = (curYear === selectedYear) ? new Date().getMonth() + 1 : 1;

    this.eeFormService.getCreditCardMonths(beginMonth).subscribe((res) => {

      this.creditCardMonths = res;
    })
  }


  getStates(formGroupName: string) {
    const formGroup = this.LaptopsCheckoutFormGroup.get(formGroupName);
    console.log(formGroup);
    const cCode = formGroup.value.country.code;
    const cName = formGroup.value.country.name;


    this.eeFormService.getStates(cCode).subscribe( (ele) => {

        if (formGroupName === 'shipAddress') {
          this.shipAddressStates = ele;
        } 
        formGroup.get('state').setValue(ele[0]);
    });
  }

  get firstName() {
    return this.LaptopsCheckoutFormGroup.get('customer.firstName');
  }
  get lastName() {
    return this.LaptopsCheckoutFormGroup.get('customer.lastName');
  }
  get email() {
    return this.LaptopsCheckoutFormGroup.get('customer.email');
  }

  get shipAddressStreet() {
    return this.LaptopsCheckoutFormGroup.get('shipAddress.street');
  }
  get shipAddressCity() {
    return this.LaptopsCheckoutFormGroup.get('shipAddress.city');
  }
  get shipAddressState() {
    return this.LaptopsCheckoutFormGroup.get('shipAddress.state');
  }
  get shipAddressZipCode() {
    return this.LaptopsCheckoutFormGroup.get('shipAddress.zipCode');
  }
  get shipAddressCountry() {
    return this.LaptopsCheckoutFormGroup.get('shipAddress.country');
  }



  get creditCardType() { 
    return this.LaptopsCheckoutFormGroup.get('creditCard.cardType'); 
  }
  get creditCardNameOnCard() { 
    return this.LaptopsCheckoutFormGroup.get('creditCard.nameOnCard'); 
  }
  get creditCardNumber() { 
    return this.LaptopsCheckoutFormGroup.get('creditCard.cardNumber'); 
  }
  get creditCardSecurityCode() { 
    return this.LaptopsCheckoutFormGroup.get('creditCard.securityCode'); 
  }
}
