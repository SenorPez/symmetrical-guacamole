import {Hull} from './hull';
import {Vector3} from "three";
import {Mast} from "./mast";

describe('Hull', () => {
  const hullDiameter: number = 64;
  let instance: Hull;

  beforeEach(() => {
    instance = new Hull(hullDiameter);
  });

  it('should create an instance', () => {
    expect(instance).toBeTruthy();
  });

  describe('Hull Geometry', () => {
    it('should have radius half the hull diameter', () => {
      const expectedValue: number = hullDiameter / 2;
      expect(instance.hullMesh.geometry.parameters.radius).toEqual(expectedValue);
    });

    it('should have a full range phi value', () => {
      expect(instance.hullMesh.geometry.parameters.phiStart).toEqual(0);
      expect(instance.hullMesh.geometry.parameters.phiLength).toEqual(Math.PI * 2);
    });

    it('should have a full range theta value', () => {
      expect(instance.hullMesh.geometry.parameters.thetaStart).toEqual(0);
      expect(instance.hullMesh.geometry.parameters.thetaLength).toEqual(Math.PI);
    });
  });

  describe('Hull Material', () => {
    it('should be colored red', () => {
      expect(instance.hullMesh.material.color.r).toEqual(1);
      expect(instance.hullMesh.material.color.g).toEqual(0);
      expect(instance.hullMesh.material.color.b).toEqual(0);
    });
  });

  describe('Mast Attachment', () => {
    // TODO: Mock mast, but I can't figure out Jasmine.
    const mastDiameter: number = 5;
    const mastLength: number = 74;
    const mast: Mast = new Mast(mastDiameter, mastLength);

    it('should have a child object', () => {
      instance.attachMast(mast);
      expect(instance.hullMesh.children.length).toBeTruthy();
    });

    it('should have a mast with a local position offset', () => {
      let expectedValue = new Vector3(0, -69, 0);
      instance.attachMast(mast);
      expect(instance.hullMesh.children.pop()?.position).toEqual(expectedValue);
    });
  });
});
