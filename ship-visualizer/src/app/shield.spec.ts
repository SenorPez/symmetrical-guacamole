import {Shield} from './shield';
import {Vector3} from "three";
import {Lantern} from "./lantern";

describe('Shield', () => {
  const shieldMaxDiameter = 35;
  const shieldMinDiameter = 25;
  const shieldThickness = 10;
  let instance: Shield;

  beforeEach(() => {
    instance = new Shield(shieldMaxDiameter / 2, shieldMinDiameter / 2, shieldThickness);
  });

  it('should create an instance', () => {
    expect(instance).toBeTruthy();
  });

  describe('Shield Geometry', () => {
    it('should have radiuii half the shield diameter', () => {
      expect(instance.shieldMesh.geometry.parameters.radiusTop).toEqual(shieldMaxDiameter / 2);
      expect(instance.shieldMesh.geometry.parameters.radiusBottom).toEqual(shieldMinDiameter / 2);
    });

    it('should have height equal to the shield thickness', () => {
      expect(instance.shieldMesh.geometry.parameters.height).toEqual(shieldThickness);
    });

    it('should have a full range theta value', () => {
      expect(instance.shieldMesh.geometry.parameters.thetaStart).toEqual(0);
      expect(instance.shieldMesh.geometry.parameters.thetaLength).toEqual(Math.PI * 2);
    });
  });

  describe('Shield Material', () => {
    it('should be color lime', () => {
      expect(instance.shieldMesh.material.color.r).toEqual(0);
      expect(instance.shieldMesh.material.color.g).toEqual(1);
      expect(instance.shieldMesh.material.color.b).toEqual(0);
    });
  });

  describe('Lantern Attachment', () => {
    // TODO: Mock lantern, but I can't figure out Jasmine.
    const lanternDiameter: number = 12;
    const lantern: Lantern = new Lantern(lanternDiameter);

    it('should have a child object', () => {
      instance.attachLantern(lantern);
      expect(instance.shieldMesh.children.length).toBeTruthy();
    });

    it('should have a lantern with a local position offset', () => {
      let expectedValue = new Vector3(0, -11, 0);
      instance.attachLantern(lantern)
      expect(instance.shieldMesh.children.pop()?.position).toEqual(expectedValue);
    });
  });
});
