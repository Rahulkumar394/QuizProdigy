import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';

import { SetquestionComponent } from './components/setquestion/setquestion.component';
import { UpdateQuestionComponent } from './components/update-question/update-question.component';

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
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule { }
