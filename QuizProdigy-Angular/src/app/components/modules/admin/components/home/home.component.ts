import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent  {
  constructor(private router: Router) {}

  manageResult() {
    console.log('This is  manage result');
    this.router.navigate(['/admin/manage-result']);
  }
  manageExam() {
    console.log('This is  manage exam');
    this.router.navigate(['/admin/manage-exam']);
  }
  manageTeacher() {
    console.log('This is  manage teacher');
    this.router.navigate(['/admin/manage-teacher']);
  }
  mangeStudent() {
    console.log('This is  manage student');
    this.router.navigate(['/admin/manage-student']);
  }
}
