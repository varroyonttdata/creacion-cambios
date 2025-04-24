import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckbuildComponent } from './checkbuild.component';

describe('CheckbuildComponent', () => {
  let component: CheckbuildComponent;
  let fixture: ComponentFixture<CheckbuildComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CheckbuildComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CheckbuildComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
