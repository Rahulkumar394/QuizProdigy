import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-set-paper',
  templateUrl: './set-paper.component.html',
  styleUrl: './set-paper.component.css'
})
export class SetPaperComponent {
  loginForm!: FormGroup<any>;
  doLogin() {
    throw new Error('Method not implemented.');
  }

  questions: any[] = [{}]; // Initialize with an empty question
  answers: any[][] = [[]]; // Initialize with an empty answer field for each question

  addAnswerField(questionIndex: number) {
    this.answers[questionIndex].push('');
  }

  addQuestion() {
    this.questions.push({});
    this.answers.push([]);
  }
}

