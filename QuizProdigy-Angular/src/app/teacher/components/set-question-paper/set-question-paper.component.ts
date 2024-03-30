import { Component } from '@angular/core';
import { FormGroup,FormControl,FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-set-question-paper',
  templateUrl: './set-question-paper.component.html',
  styleUrl: './set-question-paper.component.css'
})
export class SetQuestionPaperComponent {
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
