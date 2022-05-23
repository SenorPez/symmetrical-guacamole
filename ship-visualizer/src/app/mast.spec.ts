import {Mast} from './mast';
import {Vector3} from "three";
import {Shield} from "./shield";

describe('Mast', () => {
  const mastDiameter: number = 5;
  const mastLength: number = 74;
  let instance: Mast;

  beforeEach(() => {
    instance = new Mast(mastDiameter, mastLength);
  });

  it('should create an instance', () => {
    expect(instance).toBeTruthy();
  });

  describe('Mast Geometry', () => {
    it('should have radius half the mast diameter', () => {
      const expectedValue: number = mastDiameter / 2;
      expect(instance.mastMesh.geometry.parameters.radiusTop).toEqual(expectedValue);
      expect(instance.mastMesh.geometry.parameters.radiusBottom).toEqual(expectedValue);
    });

    it('should have height equal to the mast length', () => {
      expect(instance.mastMesh.geometry.parameters.height).toEqual(mastLength);
    });

    it('should have a full range theta value', () => {
      expect(instance.mastMesh.geometry.parameters.thetaStart).toEqual(0);
      expect(instance.mastMesh.geometry.parameters.thetaLength).toEqual(Math.PI * 2);
    });
  });

  describe('Mast Material', () => {
    it('should be colored yellow', () => {
      expect(instance.mastMesh.material.color.r).toEqual(1);
      expect(instance.mastMesh.material.color.g).toEqual(1);
      expect(instance.mastMesh.material.color.b).toEqual(0);
    });
  });

  describe('Shield Attachment', () => {
    // TODO: Mock shield, but I can't figure out Jasmine.
    const shieldDiameter: number = 25;
    const shieldThickness: number = 10;
    const shield: Shield = new Shield(shieldDiameter, shieldDiameter, shieldThickness);

    it('should have a child object', () => {
      instance.attachShield(shield);
      expect(instance.mastMesh.children.length).toBeTruthy();
    });

    it('should have a shield with a local position offset', () => {
      let expectedValue = new Vector3(0, -42, 0);
      instance.attachShield(shield);
      expect(instance.mastMesh.children.pop()?.position).toEqual(expectedValue);
    });
  });
});
