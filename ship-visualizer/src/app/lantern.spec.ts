import { Lantern } from './lantern';

describe('Lantern', () => {
  const lanternDiameter: number = 20;
  let instance: Lantern;

  beforeEach(() => {
    instance = new Lantern(lanternDiameter);
  });

  it('should create an instance', () => {
    expect(instance).toBeTruthy();
  });

  describe('Lantern Wireframe', () => {
    it('should have radius half the lantern diameter', () => {
      const expectedValue = lanternDiameter / 2;
      expect(instance.lanternMesh.geometry.parameters.geometry.parameters.radius).toEqual(expectedValue);
    });

    it('should have a full range phi value', () => {
      expect(instance.lanternMesh.geometry.parameters.geometry.parameters.phiStart).toEqual(0);
      expect(instance.lanternMesh.geometry.parameters.geometry.parameters.phiLength).toEqual(Math.PI * 2);
    });

    it('should have a half range theta value', () => {
      expect(instance.lanternMesh.geometry.parameters.geometry.parameters.thetaStart).toEqual(0);
      expect(instance.lanternMesh.geometry.parameters.geometry.parameters.thetaLength).toEqual(Math.PI / 2);
    });
  });

  describe('Lantern Material', () => {
    it('should be color cyan', () => {
      expect(instance.lanternMesh.material.color.r).toEqual(0);
      expect(instance.lanternMesh.material.color.g).toEqual(1);
      expect(instance.lanternMesh.material.color.b).toEqual(1);
    });

    it('should have line with of 30, even if it doesn\'t render right', () => {
      expect(instance.lanternMesh.material.linewidth).toEqual(30);
    });
  });
});
