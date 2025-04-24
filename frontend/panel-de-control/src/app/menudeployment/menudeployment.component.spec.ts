import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenudeploymentComponent } from './menudeployment.component';

describe('MenudeploymentComponent', () => {
  let component: MenudeploymentComponent;
  let fixture: ComponentFixture<MenudeploymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MenudeploymentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenudeploymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
