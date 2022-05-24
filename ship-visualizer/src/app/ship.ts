import {Hull} from "./hull";
import {Mast} from "./mast";
import {Lantern} from "./lantern";
import {Shield} from "./shield";
import {Mesh, MeshBasicMaterial, SphereGeometry} from "three";

export class Ship {
  private _hullDiameter: number;
  private _mastLength: number;
  private _mastDiameter: number;
  private _shieldThickness: number;
  private _shieldMaxDiameter: number;
  private _shieldMinDiameter: number;
  private _lanternDiameter: number;

  private _hull!: Hull;
  private _mast!: Mast;
  private _shield!: Shield;
  private _lantern!: Lantern;

  constructor(hullDiameter: number, mastLength: number, mastDiameter: number, shieldThickness: number, shieldMaxDiameter: number, shieldMinDiameter: number, lanternDiameter: number) {
    this._hullDiameter = hullDiameter;
    this._mastLength = mastLength;
    this._mastDiameter = mastDiameter;
    this._shieldThickness = shieldThickness;
    this._shieldMaxDiameter = shieldMaxDiameter;
    this._shieldMinDiameter = shieldMinDiameter;
    this._lanternDiameter = lanternDiameter;

    this.createMeshes();
  }

  createMeshes() {
    this._hull = new Hull(this.hullDiameter);
    this._mast = new Mast(this.mastDiameter, this.mastLength);
    this._shield = new Shield(this.shieldMaxDiameter, this.shieldMinDiameter, this.shieldThickness);
    this._lantern = new Lantern(this.lanternDiameter);

    this._hull
      .attachMast(this.mast)
      .attachShield(this.shield)
      .attachLantern(this.lantern);
  }

  get hullDiameter(): number {
    return this._hullDiameter;
  }

  set hullDiameter(value: number) {
    this._hullDiameter = value;
  }

  get mastLength(): number {
    return this._mastLength;
  }

  set mastLength(value: number) {
    this._mastLength = value;
  }

  get mastDiameter(): number {
    return this._mastDiameter;
  }

  set mastDiameter(value: number) {
    this._mastDiameter = value;
  }

  get shieldThickness(): number {
    return this._shieldThickness;
  }

  set shieldThickness(value: number) {
    this._shieldThickness = value;
  }

  get shieldMaxDiameter(): number {
    return this._shieldMaxDiameter;
  }

  set shieldMaxDiameter(value: number) {
    this._shieldMaxDiameter = value;
  }

  get shieldMinDiameter(): number {
    return this._shieldMinDiameter;
  }

  set shieldMinDiameter(value: number) {
    this._shieldMinDiameter = value;
  }

  get lanternDiameter(): number {
    return this._lanternDiameter;
  }

  set lanternDiameter(value: number) {
    this._lanternDiameter = value;
  }

  get hull(): Hull {
    return this._hull;
  }

  set hull(value: Hull) {
    this._hull = value;
  }

  get mast(): Mast {
    return this._mast;
  }

  set mast(value: Mast) {
    this._mast = value;
  }

  get shield(): Shield {
    return this._shield;
  }

  set shield(value: Shield) {
    this._shield = value;
  }

  get lantern(): Lantern {
    return this._lantern;
  }

  set lantern(value: Lantern) {
    this._lantern = value;
  }

  get shipMesh(): Mesh<SphereGeometry, MeshBasicMaterial> {
    // Same as hullMush, provided for convenience.
    return this._hull.hullMesh;
  }
}
