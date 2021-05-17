import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  loggedInStatus = false;

  authStatusListener = new Subject<boolean>(); 

  userEmail = "";

  constructor(private http: HttpClient) { }

  getAuthStatusListener() {
    return this.authStatusListener.asObservable
  }

  setLoggedIn(value: boolean) {
    this.loggedInStatus = value
  }

  get isLoggedIn() {
    return this.loggedInStatus
  }

  setUserEmail(email: string) {
    this.userEmail = email;
  }

  signUpUser(email, name, password, address, phone): Observable<SignUpResponse> {
    let body = JSON.stringify({
      'email':email,
      'name':name,
      'password':password,
      'address':address,
      'phone':phone
    })
    console.log(body)
    let options = {
      headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('withCredentials', 'true')
      .set('Access-Control-Allow-Credentials', 'true')
      .set('Access-Control-Allow-Origin', '*')
    };
    return this.http.post<SignUpResponse>('http://localhost:8080/register',body, options)
  }

  getUserDetails(username, password): Observable<LoginResponse> {
    let body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);

    let options = {
      headers: new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded')
      .set('withCredentials', 'true')
      .set('Access-Control-Allow-Credentials', 'true')
      .set('Access-Control-Allow-Origin', '*')
    };

    return this.http
    .post<LoginResponse>('http://localhost:8080/login', body.toString(), options)
  }

  logout() {
    this.loggedInStatus = false;
    this.authStatusListener.next(false);
  }

}

interface LoginResponse {
  code: number,
  message: string
}

interface SignUpResponse {
  id: number,
  email: string,
  password: string,
  name: string,
  phone: string,
  address: string,
  role: string
}
