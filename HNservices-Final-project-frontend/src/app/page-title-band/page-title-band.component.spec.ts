import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageTitleBandComponent } from './page-title-band.component';

describe('PageTitleBandComponent', () => {
  let component: PageTitleBandComponent;
  let fixture: ComponentFixture<PageTitleBandComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PageTitleBandComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PageTitleBandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
