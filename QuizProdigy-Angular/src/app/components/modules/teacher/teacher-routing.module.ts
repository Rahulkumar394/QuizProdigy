import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SetPaperComponent } from './components/set-paper/set-paper.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SetExamComponent } from './components/set-exam/set-exam.component';

const routes: Routes = [
  {
    path:'',
    component:DashboardComponent,
    children:[
      {
        path:"setExam",
        component:SetExamComponent
      },
      {
        path:"setPaper",
        component:SetPaperComponent
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule { }
