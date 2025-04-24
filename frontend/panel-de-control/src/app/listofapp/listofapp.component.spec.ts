import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListofappComponent } from './listofapp.component';

describe('ListofappComponent', () => {
  let component: ListofappComponent;
  let fixture: ComponentFixture<ListofappComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListofappComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListofappComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
