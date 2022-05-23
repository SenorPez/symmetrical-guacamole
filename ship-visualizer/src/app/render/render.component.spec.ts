import {ComponentFixture, TestBed} from '@angular/core/testing';
import {RenderComponent} from './render.component';
import {MeshBasicMaterial, SphereGeometry} from "three";

describe('RenderComponent', () => {
  let component: RenderComponent;
  let fixture: ComponentFixture<RenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        RenderComponent
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a 500x500 canvas', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('canvas')?.width).toEqual(500);
    expect(compiled.querySelector('canvas')?.height).toEqual(500);
  });

  describe('Hull', () => {
    describe('Hull Geometry', () => {
      const diameter: number = 50;
      const hullGeometry: SphereGeometry = RenderComponent.createHull(diameter).geometry;

      it('should have radius half the hull diameter', () => {
        const expectedValue: number = diameter / 2;
        expect(hullGeometry.parameters.radius).toEqual(expectedValue);
      });

      it('should have a full range phi value', () => {
        expect(hullGeometry.parameters.phiStart).toEqual(0);
        expect(hullGeometry.parameters.phiLength).toEqual(Math.PI * 2);
      });

      it('should have a full range theta value', () => {
        expect(hullGeometry.parameters.thetaStart).toEqual(0);
        expect(hullGeometry.parameters.thetaLength).toEqual(Math.PI);
      });
    });

    describe('Hull Material', () => {
      const diameter: number = 50;
      const hullMaterial: MeshBasicMaterial = RenderComponent.createHull(diameter).material;

      it('should be colored red', () => {
        expect(hullMaterial.color.r).toEqual(1);
        expect(hullMaterial.color.g).toEqual(0);
        expect(hullMaterial.color.b).toEqual(0);
      });
    });
  });
});
