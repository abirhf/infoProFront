import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PieceHierarchyComponent } from './piece-hierarchy.component';

describe('PieceHierarchyComponent', () => {
  let component: PieceHierarchyComponent;
  let fixture: ComponentFixture<PieceHierarchyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PieceHierarchyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PieceHierarchyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
