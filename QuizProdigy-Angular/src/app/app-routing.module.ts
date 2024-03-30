import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { TeacherModule } from './components/modules/teacher/teacher.module';

const routes: Routes = [
  {
    path:"",
    component:LoginComponent
  },
  {
    path:"register",
    component:RegistrationComponent
  },
  {
    path:"teacher",
    loadChildren:()=>import('./components/modules/teacher/teacher.module').then(m=>m.TeacherModule),
    
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
