import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from '../student/components/dashboard/dashboard.component';
import { DoExamComponent } from './components/do-exam/do-exam.component';

const routes: Routes = [
  {
    path: '', 
    component:DashboardComponent,
  },
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
