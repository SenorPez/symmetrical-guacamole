import {Line, LineBasicMaterial, SphereGeometry, WireframeGeometry} from "three";

export class Lantern {
  private readonly _lanternMesh: Line<WireframeGeometry<SphereGeometry>, LineBasicMaterial>;

  constructor(lanternDiameter: number) {
    const lanternGeometry: SphereGeometry = new SphereGeometry(lanternDiameter / 2, 32, 8, 0, Math.PI * 2, 0, Math.PI / 2);
    const lanternWireframe: WireframeGeometry<SphereGeometry> = new WireframeGeometry<SphereGeometry>(lanternGeometry);
    const lanternMaterial: LineBasicMaterial = new LineBasicMaterial({color: 'cyan', linewidth: 30});
    this._lanternMesh = new Line(lanternWireframe, lanternMaterial);
  }

  get lanternMesh(): Line<WireframeGeometry<SphereGeometry>, LineBasicMaterial> {
    return this._lanternMesh;
  }
}
