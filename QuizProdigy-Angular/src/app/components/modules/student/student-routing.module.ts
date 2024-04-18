import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from '../student/components/dashboard/dashboard.component';
import { DoExamComponent } from './components/do-exam/do-exam.component';
import { GetexamcodeComponent } from './components/getexamcode/getexamcode.component';
import { ExamInstructionsComponent } from './components/exam-instructions/exam-instructions.component';

const routes: Routes = [
  {
    path: '', 
    component:GetexamcodeComponent,
  },
  {
    path: 'examInstruction', 
    component:DashboardComponent,
  },
  // {
  //   path: '', 
  //   component:ExamInstructionsComponent,
  // },
  {
    path: 'takeexam', 
    component:DoExamComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
