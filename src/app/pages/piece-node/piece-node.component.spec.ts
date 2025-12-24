import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PieceNodeComponent } from './piece-node.component';

describe('PieceNodeComponent', () => {
  let component: PieceNodeComponent;
  let fixture: ComponentFixture<PieceNodeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PieceNodeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PieceNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
