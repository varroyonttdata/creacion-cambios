import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { ListofappComponent } from './listofapp/listofapp.component';
import { MenudeploymentComponent } from './menudeployment/menudeployment.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, ListofappComponent, MenudeploymentComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'panel-de-control';
}
