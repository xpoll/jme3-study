package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class JMETest01 extends SimpleApplication{

    public static void main(String[] args) {
        new JMETest01().start();
    }

    @Override
    public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 100);
        
		Box b = new Box(1, 1, 1);
		Geometry geo = new Geometry("Box", b);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Blue);
		geo.setMaterial(mat);
		rootNode.attachChild(geo);
	}
}
