package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class JMETest01 extends SimpleApplication{

	@Override
	public void simpleInitApp() {
		Box b = new Box(1, 1, 1);
		Geometry geo = new Geometry("Box", b);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Blue);
		geo.setMaterial(mat);
		

		Box b1 = new Box();
		b1.updateGeometry(new Vector3f(5, 5, 5), 1, 1, 1);
//		new Vector3f(5, 5, 5);
		Geometry geo1 = new Geometry("Box", b1);
		Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat1.setColor("Color", ColorRGBA.Red);
		geo1.setMaterial(mat1);

		rootNode.attachChild(geo);
		rootNode.attachChild(geo1);
		
	}

	public static void main(String[] args) {
		new JMETest01().start();
	}

}
