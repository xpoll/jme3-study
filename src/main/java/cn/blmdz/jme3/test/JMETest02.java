package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class JMETest02 extends SimpleApplication {

	public static void main(String[] args) {
		new JMETest02().start();
	}
	
	Node pivot = null;

	@Override
	public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 100);
		
		Box box1 = new Box();
		box1.updateGeometry(new Vector3f(1, -1, 1), 1, 1, 1);
		Geometry geom1 = new Geometry("box", box1);
		Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat1.setColor("Color", ColorRGBA.Blue);
		geom1.setMaterial(mat1);

		
		Box box2 = new Box();
		box2.updateGeometry(new Vector3f(1, 3, 1), 1, 1, 1);
		Geometry geom2 = new Geometry("box", box2);
		Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat2.setColor("Color", ColorRGBA.Red);
		geom2.setMaterial(mat2);
		
		pivot = new Node("pivot");
		rootNode.attachChild(pivot);
		
		pivot.attachChild(geom1);
		pivot.attachChild(geom2);

        pivot.rotate(FastMath.PI/8, FastMath.PI/8, FastMath.PI/8);
	}

}
