package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;

public class JMETest8 extends SimpleApplication {

	public static void main(String[] args) {
		new JMETest8().start();
	}
	
	Node shootables;
	Geometry mark;
	
	@Override
	public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 50);

        initCrossHairs();
        initKeys();
        initMake();
        
        shootables = new Node("Shootables");
        rootNode.attachChild(shootables);
        shootables.attachChild(makeCube("a Dragon", -2, 0, 1));
        shootables.attachChild(makeCube("a tin can", 1, -2, 0));
        shootables.attachChild(makeCube("the Sheriff", 0, 1, -2));
        shootables.attachChild(makeCube("the Deputy", 1, 0, -4));
        shootables.attachChild(makeFloor());
	}
	
	Geometry makeCube(String name, float x, float y, float z) {
		Box box = new Box();
		box.updateGeometry(new Vector3f(x, y, z), 1, 1, 1);
		Geometry cube = new Geometry(name, box);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.randomColor());
		cube.setMaterial(mat);
		return cube;
	}
	
	Geometry makeFloor() {
		Box box = new Box();
		box.updateGeometry(new Vector3f(0, -4, -5), 15, .2f, 15);
		Geometry floor = new Geometry("the Floor", box);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Gray);
		floor.setMaterial(mat);
		return floor;
	}
	
	private void initKeys() {
		inputManager.addMapping("Shoot", new KeyTrigger(KeyInput.KEY_SPACE),
				new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		inputManager.addListener(listener, "Shoot");
	}
	
	private ActionListener listener = new ActionListener() {
		@Override
		public void onAction(String name, boolean keyPressed, float tpf) {
			if (name.equals("Shoot") && !keyPressed) {
				CollisionResults results = new CollisionResults();
				Ray ray = new Ray(cam.getLocation(), cam.getDirection());
				shootables.collideWith(ray, results);
				
				System.out.println("--- CollisionResults " + results.size() + " ---");
				
				for (CollisionResult result : results) {
					float dist = result.getDistance();
					Vector3f pt = result.getContactPoint();
					String hit = result.getGeometry().getName();
					
					System.out.println("You shot " + hit + " at" + pt + ", " + dist + " wu away.");
				}
				if (results.size() > 0) {
					CollisionResult result = results.getClosestCollision();
					
					mark.setLocalTranslation(result.getContactPoint());
					rootNode.attachChild(mark);
					Geometry spatial = (Geometry) rootNode.getChild(results.getClosestCollision().getGeometry().getName());
					spatial.getMaterial().setColor("Color", ColorRGBA.randomColor());
				} else {
					rootNode.detachChild(mark);
				}
			}
		}
	};
	
	private void initMake() {
		Sphere sphere = new Sphere(30, 30, .2f);
		mark = new Geometry("Boom!", sphere);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Gray);
		mark.setMaterial(mat);
	}
	
	private void initCrossHairs() {
		guiNode.detachAllChildren();
		guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		BitmapText ch = new BitmapText(guiFont, false);
		ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
		ch.setText("+");
		ch.setLocalTranslation(settings.getWidth()/2 - guiFont.getCharSet().getRenderedSize()/3*2,
				settings.getHeight()/2 + ch.getLineHeight()/2, 0);
		guiNode.attachChild(ch);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
