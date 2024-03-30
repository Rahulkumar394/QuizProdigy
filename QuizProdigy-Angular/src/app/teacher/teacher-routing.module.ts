import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeacherDashboardComponent } from './teacher-dashboard/teacher-dashboard.component';
import { SetQuestionPaperComponent } from './components/set-question-paper/set-question-paper.component';

const routes: Routes = [
  {
    path:'',
    component:TeacherDashboardComponent,
    children:[
      {
        path:"setPaper",
        component:SetQuestionPaperComponent
      }
    ]
  },
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule { }
