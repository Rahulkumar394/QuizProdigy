import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent {

  toggleTeacher: boolean = false;
  toggleStudent: boolean = false;
  toggleExam: boolean = false;
  toggleResult: boolean = false;

  selectStudent() {
    if (this.toggleStudent === false) {
      this.toggleStudent = true;
      this.toggleTeacher = false;
      this.toggleExam = false;
      this.toggleResult = false;
    } else {
      this.toggleStudent = false;
      this.toggleTeacher = false;
      this.toggleExam = false;
      this.toggleResult = false;
    }
  }

  selectTeacher() {
    if (this.toggleTeacher === false) {
      this.toggleTeacher = true;
      this.toggleStudent = false;
      this.toggleExam = false;
      this.toggleResult = false;
    } else {
      this.toggleStudent = false;
      this.toggleTeacher = false;
      this.toggleExam = false;
      this.toggleResult = false;
    }
  }

  selectExam() {
    if (this.toggleExam === false) {
      this.toggleExam = true;
      this.toggleStudent = false;
      this.toggleTeacher = false;
      this.toggleResult = false;
    } else {
      this.toggleStudent = false;
      this.toggleTeacher = false;
      this.toggleExam = false;
      this.toggleResult = false;
    }
  }

  selectResult() {
    if (this.toggleExam === false) {
      this.toggleResult = true;
      this.toggleExam = false;
      this.toggleStudent = false;
      this.toggleTeacher = false;
    } else {
      this.toggleStudent = false;
      this.toggleTeacher = false;
      this.toggleExam = false;
      this.toggleResult = false;
    }
    }
}
