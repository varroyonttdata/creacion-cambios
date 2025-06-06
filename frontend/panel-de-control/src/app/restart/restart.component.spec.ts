import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestartComponent } from './restart.component';

describe('RestartComponent', () => {
  let component: RestartComponent;
  let fixture: ComponentFixture<RestartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestartComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RestartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
