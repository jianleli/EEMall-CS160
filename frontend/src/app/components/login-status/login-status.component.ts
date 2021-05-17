import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
import { Subscription } from 'rxjs'

@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['./login-status.component.css']
})
export class LoginStatusComponent implements OnInit {

  isAuthenticated: boolean = false;
  customerFullName: string;
  private authListenerSubs: Subscription;  

  constructor(private router: Router, private auth: AuthService) { }

  ngOnInit(): void {
    this.authListenerSubs = this.auth.authStatusListener
    .subscribe(isAuthenticated => this.isAuthenticated = isAuthenticated)
  }

  ngOnDestroy(){  
    this.authListenerSubs.unsubscribe();  
  } 

  getUserDetails() {
    // if (this.isAuthenticated) {

    // }
  }

  onSubmit() {
    this.router.navigateByUrl("/login");
  }

  onLogout() {
    this.auth.logout();
    this.auth.setUserEmail("");
    this.router.navigate(["login"]);
  }

  linkToSignUp() {
    this.router.navigateByUrl("/signup");
  }

  linkToProfile() {
    this.router.navigate(["profile"])
  }

}
