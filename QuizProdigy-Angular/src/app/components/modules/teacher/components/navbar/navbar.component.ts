import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
toggleStudent: boolean=false;
studentToggle() {
  if(this.toggleStudent==false)
    {
      this.toggleStudent=true;
    }
    else{
      this.toggleStudent=false;
    }
}

}
