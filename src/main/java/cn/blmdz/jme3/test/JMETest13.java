package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;

public class JMETest13 extends SimpleApplication {

	public static void main(String[] args) {
	    AppSettings settings = new AppSettings(true);
	    settings.setTitle("title");
        settings.setResolution(600, 600);
        JMETest13 app = new JMETest13();
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
	}
	
	@Override
	public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 50);
        Geometry geom = new Geometry("球体", new Sphere(10, 16, 2));//创建一个有10根纬线、16根经线、半径为2的球体
        
        // 创建材质，并显示网格线
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.getAdditionalRenderState().setWireframe(true);
        geom.setMaterial(mat);
//        rootNode.attachChild(geom);
	}
	
	
}
