import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  onSignUp(event) {
    event.preventDefault();
    const target = event.target;
    const email = target.querySelector("#email").value;
    const name = target.querySelector("#name").value;
    const address = target.querySelector("#address").value;
    const phone = target.querySelector("#phone").value;
    const password = target.querySelector("#password").value;
    console.log(email, name, address, password, phone)
    this.auth.signUpUser(email, name, password, address, phone)
    .subscribe(data => {
      alert("Registered Successfully")
      this.router.navigate(["login"])
    });
  
  }

}

interface SignUpRequest {
  email: string,
  password: string,
  phone: string,
  name: string,
  address: string
}
