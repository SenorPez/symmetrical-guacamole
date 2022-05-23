import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {
  AxesHelper,
  Camera,
  Color,
  CylinderGeometry,
  Line,
  LineBasicMaterial,
  Mesh,
  MeshBasicMaterial,
  PerspectiveCamera,
  Scene,
  SphereGeometry,
  WebGLRenderer,
  WireframeGeometry
} from "three";
import {OrbitControls} from "three/examples/jsm/controls/OrbitControls";

@Component({
  selector: 'app-render',
  templateUrl: './render.component.html',
  styleUrls: ['./render.component.css']
})
export class RenderComponent implements OnInit, AfterViewInit {
  @ViewChild('shiprender')
  private renderRef!: ElementRef;

  private scene!: Scene;
  private camera!: Camera;
  private controls!: OrbitControls;
  private renderer!: WebGLRenderer;

  private get canvas(): HTMLCanvasElement {
    return this.renderRef.nativeElement;
  }

  private createShip(
    hullDiameter: number = 14.94895,
    mastLength: number = 28.20816,
    mastDiameter: number = mastLength / 50,
    shieldLength: number = 2.64379,
    shieldDiameter: number = 2.02292,
    lanternDiameter: number = 10.95445
  ) {
    const hull: Mesh<SphereGeometry, MeshBasicMaterial> = RenderComponent.createHull(hullDiameter);
    const mast: Mesh<CylinderGeometry, MeshBasicMaterial> = RenderComponent.createMast(mastDiameter, mastLength);
    const shield: Mesh<CylinderGeometry, MeshBasicMaterial> = RenderComponent.createShield(shieldDiameter, shieldLength);
    const lantern: Line<WireframeGeometry<SphereGeometry>, LineBasicMaterial> = RenderComponent.createLantern(lanternDiameter);

    this.scene.add(RenderComponent.attachLantern(shield, lantern));
  }

  static attachLantern(
    shield: Mesh<CylinderGeometry, MeshBasicMaterial>,
    lantern: Line<WireframeGeometry<SphereGeometry>, LineBasicMaterial>
  ): Mesh<CylinderGeometry, MeshBasicMaterial> {
    lantern.position.set(0, -1 * (lantern.geometry.parameters.geometry.parameters.radius + shield.geometry.parameters.height / 2), 0);
    return shield.add(lantern);
  }

  // static assembleShip(
  //   hull: Mesh<SphereGeometry, MeshBasicMaterial>,
  //   mast: Mesh<CylinderGeometry, MeshBasicMaterial>,
  //   shield: Mesh<CylinderGeometry, MeshBasicMaterial>,
  //   lantern: Line<WireframeGeometry<SphereGeometry>, LineBasicMaterial>
  // ): Mesh<SphereGeometry, MeshBasicMaterial> {
  //   // Attach lantern to shield.
  //   lantern.position.set(0, -1 * )
  //
  //   return hull;
  // }

  static createHull(hullDiameter: number): Mesh<SphereGeometry, MeshBasicMaterial> {
    const hullGeometry: SphereGeometry = new SphereGeometry(hullDiameter / 2);
    const hullMaterial: MeshBasicMaterial = new MeshBasicMaterial({color: 'red'});
    return new Mesh(hullGeometry, hullMaterial);
  }

  static createMast(mastDiameter: number, mastLength: number): Mesh<CylinderGeometry, MeshBasicMaterial> {
    const mastGeometry: CylinderGeometry = new CylinderGeometry(mastDiameter / 2, mastDiameter / 2, mastLength);
    const mastMaterial: MeshBasicMaterial = new MeshBasicMaterial({color: 'yellow'});
    return new Mesh(mastGeometry, mastMaterial);
  }

  static createShield(shieldDiameter: number, shieldLength: number): Mesh<CylinderGeometry, MeshBasicMaterial> {
    const shieldGeometry: CylinderGeometry = new CylinderGeometry(shieldDiameter / 2, shieldDiameter / 2, shieldLength);
    const shieldMaterial: MeshBasicMaterial = new MeshBasicMaterial({color: 'lime'});
    return new Mesh(shieldGeometry, shieldMaterial);
  }

  static createLantern(lanternDiameter: number) {
    const lanternGeometry: SphereGeometry = new SphereGeometry(lanternDiameter / 2, 32, 8, 0, Math.PI * 2, 0, Math.PI / 2);
    const lanternWireframe: WireframeGeometry<SphereGeometry> = new WireframeGeometry<SphereGeometry>(lanternGeometry);
    const lanternMaterial = new LineBasicMaterial({color: 'cyan', linewidth: 30});
    return new Line(lanternWireframe, lanternMaterial);
  }

  private createScene() {
    this.scene = new Scene();
    this.scene.background = new Color(0x000000);

    const axesHelper = new AxesHelper(5);
    this.scene.add(axesHelper);

    this.createShip();

    let hullDiameter: number = 14.94895;
    let mastLength: number = 28.20816;
    let shieldLength: number = 2.64379;
    let lanternDiameter: number = 10.95445;

    let totalLength: number = hullDiameter + mastLength + shieldLength + lanternDiameter / 2;

    this.camera = new PerspectiveCamera(50, this.getAspectRatio());
    this.camera.position.set(totalLength, totalLength, totalLength + totalLength / 2);
  }

  private getAspectRatio() {
    return this.canvas.clientWidth / this.canvas.clientHeight;
  }

  private startRenderingLoop() {
    this.renderer = new WebGLRenderer({canvas: this.canvas});
    this.renderer.setPixelRatio(devicePixelRatio);
    this.renderer.setSize(this.canvas.clientWidth, this.canvas.clientHeight);

    this.controls = new OrbitControls(this.camera, this.renderer.domElement);
    this.controls.update();

    let component: RenderComponent = this;
    (function render() {
      requestAnimationFrame(render);
      component.controls.update();
      component.renderer.render(component.scene, component.camera);
    }());
  }

  constructor() { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.createScene();
    this.startRenderingLoop();
  }
}
