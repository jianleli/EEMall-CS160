import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { LaptopListComponent } from './components/laptop-list/laptop-list.component';
import {HttpClientModule} from '@angular/common/http';
import { LaptopService } from './services/laptop.service';
import {Routes, RouterModule} from '@angular/router';
import { LaptopCategoryMenuComponent } from './components/laptop-category-menu/laptop-category-menu.component';
import { SearchComponent } from './components/search/search.component';
import { LaptopDetailsComponent } from './components/laptop-details/laptop-details.component';
import { ShopCartStatusComponent } from './components/shop-cart-status/shop-cart-status.component';
import { ShopCartDetailComponent } from './components/shop-cart-detail/shop-cart-detail.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { ReactiveFormsModule } from '@angular/forms';
import { OrderSuccessComponent } from './components/order-success/order-success.component';
import { LoginComponent } from './components/login/login.component';
import { LoginStatusComponent } from './components/login-status/login-status.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { AuthGuard } from './auth.guard';
import { ProfileComponent } from './components/profile/profile.component';

const routes: Routes=[
  {path: 'signup', component: SignUpComponent},
  {path: 'login', component: LoginComponent},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'order-success', component: OrderSuccessComponent},
  {path: 'shop-cart-detail', component: ShopCartDetailComponent, canActivate: [AuthGuard]},
  {path: 'checkout', component: CheckoutComponent, canActivate: [AuthGuard]},
  {path: 'product/:id', component: LaptopDetailsComponent},
  {path: 'search/:keyword', component: LaptopListComponent},
  {path: 'categories/:id', component: LaptopListComponent},
  {path: 'category', component: LaptopListComponent},
  {path: 'product', component: LaptopListComponent},
  {path: '', redirectTo: '/product', pathMatch: 'full'},
  {path: '**', redirectTo: '/product', pathMatch: 'full'},
];

@NgModule({
  declarations: [
    AppComponent,
    LaptopListComponent,
    LaptopCategoryMenuComponent,
    SearchComponent,
    LaptopDetailsComponent,
    ShopCartStatusComponent,
    ShopCartDetailComponent,
    CheckoutComponent,
    OrderSuccessComponent,
    LoginComponent,
    LoginStatusComponent,
    SignUpComponent,
    ProfileComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [LaptopService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
