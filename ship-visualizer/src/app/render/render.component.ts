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

  private static createShip(scene: Scene) {
    let hullDiameter: number = 14.94895;
    let mastLength: number = 28.20816;
    let mastDiameter: number = mastLength / 50;
    let shieldLength: number = 2.64379;
    let shieldDiameter: number = 2.02292;
    let lanternDiameter: number = 10.95445;

    let hull: Mesh<SphereGeometry, MeshBasicMaterial> = RenderComponent.createHull(scene, hullDiameter);
    let mast: Mesh<CylinderGeometry, MeshBasicMaterial> = RenderComponent.createMast(hull, mastDiameter, mastLength);
    let shield: Mesh<CylinderGeometry, MeshBasicMaterial> = RenderComponent.createShield(mast, shieldDiameter, shieldLength);
    RenderComponent.createLantern(shield, lanternDiameter);
  }

  private static createHull(scene: Scene, hullDiameter: number): Mesh<SphereGeometry, MeshBasicMaterial> {
    let hullGeometry: SphereGeometry = new SphereGeometry(hullDiameter / 2);
    let hullMaterial: MeshBasicMaterial = new MeshBasicMaterial({color: 'red'});
    let hull: Mesh<SphereGeometry, MeshBasicMaterial> = new Mesh(hullGeometry, hullMaterial);
    hull.position.set(0, 0, 0);

    scene.add(hull);
    return hull;
  }

  private static createMast(hull: Mesh<SphereGeometry, MeshBasicMaterial>, mastDiameter: number, mastLength: number): Mesh<CylinderGeometry, MeshBasicMaterial> {
    let mastGeometry: CylinderGeometry = new CylinderGeometry(mastDiameter / 2, mastDiameter / 2, mastLength);
    let mastMaterial: MeshBasicMaterial = new MeshBasicMaterial({color: 'orange'});
    let mast: Mesh<CylinderGeometry, MeshBasicMaterial> = new Mesh(mastGeometry, mastMaterial);
    hull.add(mast);
    mast.rotateX(Math.PI / 2);
    mast.position.set(0, 0, hull.geometry.parameters.radius + mast.geometry.parameters.height / 2);

    return mast;
  }

  private static createShield(mast: Mesh<CylinderGeometry, MeshBasicMaterial>, shieldDiameter: number, shieldLength: number): Mesh<CylinderGeometry, MeshBasicMaterial> {
    let shieldGeometry: CylinderGeometry = new CylinderGeometry(shieldDiameter / 2, shieldDiameter / 2, shieldLength);
    let shieldMaterial: MeshBasicMaterial = new MeshBasicMaterial({color: 'yellow'});
    let shield: Mesh<CylinderGeometry, MeshBasicMaterial> = new Mesh(shieldGeometry, shieldMaterial);
    mast.add(shield);
    shield.position.set(0, mast.geometry.parameters.height / 2 + shield.geometry.parameters.height / 2, 0);

    return shield;
  }

  private static createLantern(shield: Mesh<CylinderGeometry, MeshBasicMaterial>, lanternDiameter: number): void {
    let lanternGeometry: SphereGeometry = new SphereGeometry(lanternDiameter / 2, 8, 6, 0, Math.PI);
    let lanternWireframe: WireframeGeometry<SphereGeometry> = new WireframeGeometry(lanternGeometry);
    let lantern = new Line(lanternWireframe, new LineBasicMaterial({color: 'green', linewidth: 30}));
    shield.add(lantern);
    lantern.rotateX(Math.PI / 2);
    lantern.position.set(0, shield.geometry.parameters.height / 2 + lanternGeometry.parameters.radius, 0);
  }

  private createScene() {
    this.scene = new Scene();
    this.scene.background = new Color(0x000000);

    const axesHelper = new AxesHelper(5);
    this.scene.add(axesHelper);

    RenderComponent.createShip(this.scene);

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
