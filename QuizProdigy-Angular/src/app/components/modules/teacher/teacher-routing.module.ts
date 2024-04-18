import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';

import { SetquestionComponent } from './components/setquestion/setquestion.component';
import { UpdateQuestionComponent } from './components/update-question/update-question.component';
import { ViewQuestionComponent } from './components/view-question/view-question.component';
import { ManageStudentComponent } from './components/manage-student/manage-student.component';
import { StudentInfoComponent } from './components/student-info/student-info.component';
import { StudentRequestComponent } from './components/student-request/student-request.component';

const routes: Routes = [
  {
    path:'',
    component:DashboardComponent,
    children:[
      {
        path:"setQuestion",
        component:SetquestionComponent
      },
      {
        path:"updateQuestion",
        component:UpdateQuestionComponent
      },
      {
        path:"viewQuestion",
        component:ViewQuestionComponent
      },
      {
        path:"manage-student",
        component:ManageStudentComponent
      },
      {
        path:"student-info",
        component:StudentInfoComponent
      },
      {
        path:"student-request",
        component:StudentRequestComponent
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule { }
