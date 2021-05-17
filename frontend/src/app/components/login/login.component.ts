import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor(private Auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  loginUser(event) {
    event.preventDefault();
    const target = event.target;
    const username = target.querySelector("#username").value;
    const password = target.querySelector("#password").value;

    this.Auth.getUserDetails(username, password).subscribe(data => {
      console.log(data)
      if (data.code == 1) {
        window.alert("Wrong username or password!")
        this.Auth.setLoggedIn(false)
        this.Auth.setUserEmail("")
      } else {
        console.log("Login success")
        this.Auth.setLoggedIn(true)
        this.Auth.setUserEmail(username)
        this.Auth.authStatusListener.next(true)
        this.router.navigate(['product'])
      }
    })

  }

}
