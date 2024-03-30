import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
} from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  emailCheck: boolean = true;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private loginService: LoginService
  ) {}

  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [''],
      password: [''],
    });
  }

  login() {
    const success = 'Logged In Successfully';
    const errorMessage = 'Please Try Again';

    if (!this.validateEmail(this.loginForm.value.email)) {
      Swal.fire({
        title: 'Error!',
        text: 'Invalid Email Address',
        icon: 'error',
      });
      return;
    }

    this.loginService.login(this.loginForm.value).subscribe(
      (data: any) => {
        console.log(success + data);
      },
      (error: any) => {
        console.log(errorMessage + error);
      }
    );
  }

  validateEmail(email: any) {
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z]+\.[a-z]{2,3}$/;

    if (!emailRegex.test(email)) {
      this.emailCheck = false;
    } else this.emailCheck = true;

    return this.emailCheck;
  }
}
