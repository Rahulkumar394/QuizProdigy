import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {

  registrationForm = new FormGroup({
    firstName:new FormControl(''),
    lastName:new FormControl(''),
    contactNumber:new FormControl(''),
    email:new FormControl(''),
    password:new FormControl(''),
    cnfPassword:new FormControl(''),


  })

  register(){
    
  }

}
