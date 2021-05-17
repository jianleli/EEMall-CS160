import { Component, OnInit } from '@angular/core';
import { ProfileService } from 'src/app/services/profile.service';
import { Address } from '../../common/address';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  email: string = "";
  name: string = "";
  phone: string = "";
  address: string = "";
  orderList: OrderRecord[] = [];


  constructor(private profileService: ProfileService) { }

  ngOnInit(): void {
    this.displayUserProfile()
    this.didsplayOrderList()
  }

  displayUserProfile() {
    this.profileService.getUserProfile().subscribe(data => {
      this.email = data.email;
      this.name = data.name;
      this.address = data.address;
      this.phone = data.phone;
    })
  }

  didsplayOrderList() {
    this.profileService.getOrderRecords().subscribe(arrObj => {
      this.orderList = arrObj;
    })
  }

}

interface OrderRecord {
  orderTrackingNumber: string,
  totalQuantity: number,
  totalPrice: number,
  dateCreated: string,
  lastUpdated: string,
  shippingAddress: Address  
}
