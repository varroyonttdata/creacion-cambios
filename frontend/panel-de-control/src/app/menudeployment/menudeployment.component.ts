import { Component, Input, Output } from '@angular/core';
import { CheckbuildComponent } from '../checkbuild/checkbuild.component';
import { DeployComponent } from '../deploy/deploy.component';
import { RestartComponent } from '../restart/restart.component';
import { UndeployComponent } from '../undeploy/undeploy.component';
import { BuildComponent } from '../build/build.component';

@Component({
  selector: 'app-menudeployment',
  imports: [CheckbuildComponent, DeployComponent, RestartComponent, UndeployComponent, BuildComponent],
  templateUrl: './menudeployment.component.html',
  styleUrl: './menudeployment.component.scss'
})
export class MenudeploymentComponent {

  @Input() listOfApps: any[] = [];
  @Input() appSelected: string = '';

  currentMenu: string = '';

  showMenu(menu : string) {
    this.currentMenu = menu;
    }
}
