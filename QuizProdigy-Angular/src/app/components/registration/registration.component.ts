import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { RegistrationService } from '../../services/registration/registration.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css',
})
export class RegistrationComponent implements OnInit {
  constructor(
    private formBuilder: FormBuilder,
    private registerService: RegistrationService,
    private router: Router
  ) {}

  student: boolean = true;
  teacher: boolean = false;

  toggleTeacher() {
    this.teacher = true;
    this.student = false;
  }
  toggleStudent() {
    this.student = true;
    this.teacher = false;
  }

  registerForm = new FormGroup({
    name: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    role: new FormControl(''),
    instituteName: new FormControl(''),
    contactNumber: new FormControl('', [
      Validators.required,
      Validators.pattern('[0-9]*'),
      Validators.maxLength(10),
    ]),
    enrollment: new FormControl(''),
    department: new FormControl(''),
    employeeNo: new FormControl(''),
    password: new FormControl('', Validators.required),
    cnfPassword: new FormControl('', Validators.required),
  });

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      name: [''],
      email: [''],
      role: ['Student'], // Set the default value for role
      instituteName: [''],
      contactNumber: [''],
      enrollment: [''],
      department: [''],
      employeeNo: [''],
      password: [''],
      cnfPassword: [''],
    });
  }

  //Validate the data of the form and send the data to the service
  register() {
    console.log('This is Regitration Data ', this.registerForm.value);

      // validate name
      let message = this.validateName(this.registerForm.value.name);

      if (message != '') {
        Swal.fire({
          title: 'Error!',
          text: message,
          icon: 'error',
        });
        return;
      }

      //Check email
      message = this.validateEmail(this.registerForm.value.email);

      if (message != '') {
        Swal.fire({
          title: 'Error!',
          text: message,
          icon: 'error',
        });
        return;
      }

      //check contact number
      message = this.validateContactNumber(
        this.registerForm.value.contactNumber
      );

      if (message != '') {
        Swal.fire({
          title: 'Error!',
          text: message,
          icon: 'error',
        });
        return;
      }

      message = this.checkPassword(
        this.registerForm.value.password,
        this.registerForm.value.cnfPassword
      );

      if (message != '') {
        Swal.fire({
          title: 'Error!',
          text: message,
          icon: 'error',
        });
        return;
      }

      //if every vlaidation pass  then call the service method
      this.registerService.registerUser(this.registerForm.value, this.registerForm.value.role).subscribe(
        (response) => {
          console.log('Response from server : ', response);
          Swal.fire({
            title: 'Register Successfully!!',
            text: 'You can login now',
            icon: 'success',
          });

          //and Navigate to the login page
          this.router.navigate(['/']);
        },
        // if any  error occured while registering user
        (error) => {
          console.log("Error aa raha hai ",error);
          Swal.fire({
            title: 'Server Error',
            text: 'There is something wrong please try again',
            icon: 'error',
          });
        }
      );
      //Reset the form after successful registration
      this.registerForm.reset();
      // Set default role back to Student
      this.registerForm.patchValue({ role: 'Student' });
  }

  markFormGroupTouched(formGroup: FormGroup) {
    Object.values(formGroup.controls).forEach((control) => {
      control.markAsTouched();

      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }

  //===========Validtion code===============================

  //Validate the name fields
  validateName(name: any): string {
    let message = '';

    // Check if either of the fields is empty
    if (!name) {
      message = 'Please enter the your name';
      return message;
    }

    // Check if the first and last names contain at least two characters
    if (name.length < 2) {
      message = 'Name must contain at least two characters.';
      return message;
    }
    // If all validations pass
    return message; // Return an empty string indicating success
  }

  //validate the email
  validateEmail(email: any): string {
    let message = '';

    if (!email) {
      message = 'Please Enter the email';
      return message;
    }

    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z]+\.[a-z]{2,3}$/;
    if (!emailRegex.test(email)) {
      message = 'Please enter a valid email address.';
      return message;
    }

    // If all validations pass
    return message; // Return an empty string indicating success
  }

  validateContactNumber(contactNumber: any): string {
    let message = '';

    //check if the contactNumber is empty or not
    if (!contactNumber) {
      message = 'Please Enter the Contact Number';
      return message;
    }

    // Check if the contact number is valid
    const contactNumberRegex = /^\d{10}$/; // assuming a 10-digit number
    if (!contactNumberRegex.test(contactNumber)) {
      message = 'Please enter a valid contact number.';
      return message;
    }

    // If all validations pass
    return message; // Return an empty string indicating success
  }

  checkPassword(password: any, confirmPassword: any): string {
    let message = '';

    //check the password empty or not
    if (!password || !confirmPassword) {
      message = 'Both fields are required.';
      return message;
    }

    // Check if the password and retype password match
    if (password !== confirmPassword) {
      message = 'Passwords do not match.';
      return message;
    }

    // Check if the password is between 8 to 16 characters
    if (password.length < 6 || confirmPassword.length > 16) {
      message = 'Password must be between 6 to 16 characters.';
      return message;
    }

    // If all validations pass
    return message; // Return an empty string indicating success
  }
}
