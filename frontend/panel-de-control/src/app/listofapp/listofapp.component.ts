import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MenudeploymentComponent } from "../menudeployment/menudeployment.component";

@Component({
  selector: 'app-listofapp',
  imports: [MenudeploymentComponent],
  templateUrl: './listofapp.component.html',
  styleUrl: './listofapp.component.scss'
})
export class ListofappComponent {

  listOfApps = [
    {
      id: 1,
      name: 'ASECLD',
      menus: ['DEPLOY', 'BUILD', 'CHECKBUILD', 'RESTART']
    },
    {
      id: 2,
      name: 'COFCLD',
      menus: ['DEPLOY', 'BUILD', 'CHECKBUILD', 'RESTART', 'UNDEPLOY']
    },
    {
      id: 3,
      name: 'SRVCLD',
      menus: ['DEPLOY', 'BUILD', 'RESTART']
    },
    {
      id: 4,
      name: 'BCKCLD',
      menus: ['BUILD', 'CHECKBUILD', 'RESTART']
    },
    {
      id: 5,
      name: 'REGSRV',
      menus: ['DEPLOY']
    }
  ];

  appSelected: string = '';

  selectApp(appName: string) {
    this.appSelected = appName;
  }

  
}
