import { Component } from '@angular/core';

@Component({
  selector: 'app-deploy',
  imports: [],
  templateUrl: './deploy.component.html',
  styleUrl: './deploy.component.scss'
})
export class DeployComponent {

  componentNames = [
    { id: 1, name: 'Componente 1' },
    { id: 2, name: 'Componente 2' },
    { id: 3, name: 'Componente 3' }
  ];
  
  environmentNames = [
    { id: 1, name: 'Entorno 1' },
    { id: 2, name: 'Entorno 2' },
    { id: 3, name: 'Entorno 3' }
  ];

  environmentSelected = null;
  componentSelected = null;
}
