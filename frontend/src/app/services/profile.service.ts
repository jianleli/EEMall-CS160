import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth.service';
import { Observable } from 'rxjs';
import { Address } from '../common/address';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private profileBaseUrl = 'http://localhost:8080/profile';

  private ordersBaseUrl = 'http://localhost:8080/orders';

  constructor(private http: HttpClient, private auth: AuthService) { }

  getUserProfile(): Observable<UserResponse> {
    const profileUrl = `${this.profileBaseUrl}/${this.auth.userEmail}`;
    return this.http.get<UserResponse>(profileUrl);
  }

  getOrderRecords(): Observable<OrderRecord[]> {
    const orderRecordUrl = `${this.ordersBaseUrl}/${this.auth.userEmail}`;
    return this.http.get<OrderRecord[]>(orderRecordUrl);
  }
}

interface UserResponse {
  email: string,
  name: string,
  phone: string,
  address: string,
}

interface OrderRecord {
  orderTrackingNumber: string,
  totalQuantity: number,
  totalPrice: number,
  dateCreated: string,
  lastUpdated: string,
  shippingAddress: Address  
}
