import {CylinderGeometry, Mesh, MeshBasicMaterial} from "three";
import {Shield} from "./shield";

export class Mast {
  private readonly _mastMesh: Mesh<CylinderGeometry, MeshBasicMaterial>;

  constructor(mastDiameter: number, mastLength: number) {
    const mastGeometry: CylinderGeometry = new CylinderGeometry(mastDiameter / 2, mastDiameter / 2, mastLength);
    const mastMaterial: MeshBasicMaterial = new MeshBasicMaterial({color: 'yellow'});
    this._mastMesh = new Mesh(mastGeometry, mastMaterial);
  }

  get mastMesh(): Mesh<CylinderGeometry, MeshBasicMaterial> {
    return this._mastMesh;
  }

  attachShield(shield: Shield): this {
    shield.shieldMesh.position.set(0, -0.5 * (shield.shieldMesh.geometry.parameters.height + this._mastMesh.geometry.parameters.height), 0);
    this._mastMesh.add(shield.shieldMesh);
    return this;
  }
}
