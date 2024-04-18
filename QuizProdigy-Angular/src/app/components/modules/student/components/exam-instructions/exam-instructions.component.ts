import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-exam-instructions',
  templateUrl: './exam-instructions.component.html',
  styleUrl: './exam-instructions.component.css'
})
export class ExamInstructionsComponent  implements OnInit{


  examTitle = 'Exam Instructions';
  instructionsTitle = 'Instructions:';
  // totalQuestions = 20;
  // totalTime:any=

    
  examId:any=localStorage.getItem('examId')
  instructions = [
    'Read each question carefully before answering.',
    'This exam consists of multiple-choice questions.',
    'Each question has only one correct answer.',
    'Do not spend too much time on any single question. If you are unsure, mark the best answer and move on.',
    'Review your answers before submitting the exam.',
    'You will have ' + this.formatTime(localStorage.getItem('totalTime')) + ' to complete the exam.', // Using a function for dynamic time formatting
    `Total number of questions: ${localStorage.getItem('totalQuestion')}`
  ];
  rulesTitle = 'Exam Rules:';
  rules = [
    'Ensure that you are in a quiet and distraction-free environment during the exam.',
    'Do not use any unauthorized materials, websites, or external assistance during the exam.',
    'Do not communicate or collaborate with other students or individuals during the exam.',
    'Do not attempt to access any other applications or browser tabs except for the exam platform.',
    'Make sure your internet connection is stable to avoid interruptions during the exam.',
    'Follow the instructions provided by the exam proctor or invigilator, if applicable.',
    'Do not attempt to cheat or engage in any form of academic dishonesty; all exams are monitored for integrity.',
    'If you encounter any technical issues during the exam, notify the exam support team immediately for assistance.',
    'Ensure that you submit your answers within the specified time limit; late submissions may not be accepted.'
  ];
  
  goodLuckMessage = 'Good luck with your exam!';

  // Function to format time in minutes and seconds
  formatTime(minutes: any): string {
    const hours = Math.floor(minutes / 60);
    const remainingMinutes = minutes % 60;
    return `${hours > 0 ? hours + ' hours ' : ''}${remainingMinutes} minutes`;
  }

  ngOnInit(): void {
    
  }

  


}
