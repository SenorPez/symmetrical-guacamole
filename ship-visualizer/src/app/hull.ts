import {Mesh, MeshBasicMaterial, SphereGeometry} from "three";
import {Mast} from "./mast";

export class Hull {
  private readonly _hullMesh: Mesh<SphereGeometry, MeshBasicMaterial>;

  constructor(hullDiameter: number) {
    const hullGeometry: SphereGeometry = new SphereGeometry(hullDiameter / 2);
    const hullMaterial: MeshBasicMaterial = new MeshBasicMaterial({color: 'red'});
    this._hullMesh = new Mesh(hullGeometry, hullMaterial);
  }

  get hullMesh(): Mesh<SphereGeometry, MeshBasicMaterial> {
    return this._hullMesh;
  }

  attachMast(mast: Mast): Mast {
    mast.mastMesh.position.set(0, -1 * (mast.mastMesh.geometry.parameters.height / 2 + this._hullMesh.geometry.parameters.radius), 0);
    this._hullMesh.add(mast.mastMesh);
    return mast;
  }
}
