package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

public class JMETest11 extends SimpleApplication {

	public static void main(String[] args) {
	    AppSettings settings = new AppSettings(true);
	    settings.setTitle("title");
        settings.setResolution(600, 600);
        JMETest11 app = new JMETest11();
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
	}
	Geometry geo;
	
	@Override
	public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 50);
        
        Box box = new Box(1, 1, 1);
        
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        
        geo = new Geometry("Box", box);
        geo.setMaterial(mat);
        
        DirectionalLight sun = new DirectionalLight(new Vector3f(-1, -2, -3));
        
        rootNode.addLight(sun);
        rootNode.attachChild(geo);
        
	}
	
	@Override
	public void simpleUpdate(float deltaTime) {
	    System.out.println(deltaTime);
	    // 旋转速度：每秒360°
        float speed = FastMath.TWO_PI;
        // 让方块匀速旋转
        geo.rotate(0, deltaTime * speed, 0);
	}
	
}
