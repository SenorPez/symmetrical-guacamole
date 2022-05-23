import {ComponentFixture, TestBed} from '@angular/core/testing';
import {RenderComponent} from './render.component';
import {CylinderGeometry, MeshBasicMaterial, SphereGeometry} from "three";

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

  describe('Mast', () => {
    describe('Mast Geometry', () => {
      const mastLength: number = 50;
      const mastDiameter: number = 2;
      const mastGeometry: CylinderGeometry = RenderComponent.createMast(mastDiameter, mastLength).geometry;

      it('should have radius half the mast diameter', () => {
        const expectedValue: number = mastDiameter / 2;
        expect(mastGeometry.parameters.radiusTop).toEqual(expectedValue);
        expect(mastGeometry.parameters.radiusBottom).toEqual(expectedValue);
      });

      it('should have height equal to the mast length', () => {
        expect(mastGeometry.parameters.height).toEqual(mastLength);
      });

      it('should have a full range theta value', () => {
        expect(mastGeometry.parameters.thetaStart).toEqual(0);
        expect(mastGeometry.parameters.thetaLength).toEqual(Math.PI * 2);
      });
    });

    describe('Mast Material', () => {
      const mastLength: number = 50;
      const mastDiameter: number = 2;
      const mastMaterial: MeshBasicMaterial = RenderComponent.createMast(mastDiameter, mastLength).material;

      it('should be colored yellow', () => {
        expect(mastMaterial.color.r).toEqual(1);
        expect(mastMaterial.color.g).toEqual(1);
        expect(mastMaterial.color.b).toEqual(0);
      });
    });
  });

  describe('Shield', () => {
    describe('Shield Geometry', () => {
      const shieldLength: number = 50;
      const shieldDiameter: number = 25;
      const shieldGeometry: CylinderGeometry = RenderComponent.createShield(shieldDiameter, shieldLength).geometry;

      it('should have radius half the shield diameter', () => {
        const expectedValue: number = shieldDiameter / 2;
        expect(shieldGeometry.parameters.radiusTop).toEqual(expectedValue);
        expect(shieldGeometry.parameters.radiusBottom).toEqual(expectedValue);
      });

      it('should have height equal to the shield length', () => {
        expect(shieldGeometry.parameters.height).toEqual(shieldLength);
      });

      it('should have a full range theta value', () => {
        expect(shieldGeometry.parameters.thetaStart).toEqual(0);
        expect(shieldGeometry.parameters.thetaLength).toEqual(Math.PI * 2);
      });
    });

    describe('Shield Material', () => {
      const shieldLength: number = 50;
      const shieldDiameter: number = 25;
      const shieldMaterial: MeshBasicMaterial = RenderComponent.createShield(shieldDiameter, shieldLength).material;
      console.log(shieldMaterial);

      it('should be color lime', () => {
        expect(shieldMaterial.color.r).toEqual(0);
        expect(shieldMaterial.color.g).toEqual(1);
        expect(shieldMaterial.color.b).toEqual(0);
      })
    });
  });
});
