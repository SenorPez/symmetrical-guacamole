import {CylinderGeometry, Mesh, MeshBasicMaterial} from "three";
import {Lantern} from "./lantern";

export class Shield {
  private readonly _shieldMesh: Mesh<CylinderGeometry, MeshBasicMaterial>;

  constructor(shieldMaxDiameter: number, shieldMinDiameter: number, shieldThickness: number) {
    const shieldGeometry: CylinderGeometry = new CylinderGeometry(shieldMaxDiameter, shieldMinDiameter, shieldThickness);
    const shieldMaterial: MeshBasicMaterial = new MeshBasicMaterial({color: 'lime'});
    this._shieldMesh = new Mesh(shieldGeometry, shieldMaterial);
  }

  get shieldMesh(): Mesh<CylinderGeometry, MeshBasicMaterial> {
    return this._shieldMesh;
  }

  attachLantern(lantern: Lantern): this {
    lantern.lanternMesh.position.set(0, -1 * (lantern.lanternMesh.geometry.parameters.geometry.parameters.radius + this._shieldMesh.geometry.parameters.height / 2), 0);
    this._shieldMesh.add(lantern.lanternMesh);
    return this;
  }
}
