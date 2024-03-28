import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { ToastModule } from 'primeng/toast';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistrationComponent } from './components/registration/registration.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    // 
    ToastModule,
    HttpClientModule,
  ],
  providers: [
    provideClientHydration(),
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
