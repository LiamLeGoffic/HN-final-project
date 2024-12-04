import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeUserFormComponent } from './type-user-form.component';

describe('TypeUserFormComponent', () => {
  let component: TypeUserFormComponent;
  let fixture: ComponentFixture<TypeUserFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TypeUserFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TypeUserFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
