import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ManageStudentsComponent } from './components/manage-students/manage-students.component';
import { ManageTeachersComponent } from './components/manage-teachers/manage-teachers.component';
import { ManageExamComponent } from './components/manage-exam/manage-exam.component';
import { ManageResultComponent } from './components/manage-result/manage-result.component';
import { HomeComponent } from './components/home/home.component';
import { TeacherInformationComponent } from './components/teacher-information/teacher-information.component';

const routes: Routes = [
  {
    path: '', component:DashboardComponent,
    children:[
      {path:'manage-student',component:ManageStudentsComponent},
      {path:'manage-teacher',component:ManageTeachersComponent},
      {path:'manage-exam',component:ManageExamComponent},
      {path:'manage-result',component:ManageResultComponent},
      {path:'teacher-information',component:TeacherInformationComponent},
      {path:'home',component:HomeComponent},
      {path:'',component:HomeComponent}
      
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
