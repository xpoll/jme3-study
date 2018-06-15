package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;

public class JMETest15 extends SimpleApplication {

	public static void main(String[] args) {
	    AppSettings settings = new AppSettings(true);
	    settings.setTitle("title");
        settings.setResolution(600, 600);
        JMETest15 app = new JMETest15();
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
	}
	
	@Override
	public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 50);

        for (float shininess = 1, x= 0; shininess <= 128; shininess += 32f, x += 2.5f) {
            Geometry geom = addLightSphere(shininess);
            rootNode.attachChild(geom);
            geom.move(x, 0, 0);
        }
        
        addLight();
        viewPort.setBackgroundColor(new ColorRGBA(0.6f, 0.7f, 0.9f, 1));
	}
    
    /**
     * 创造一个红色的小球，应用受光材质。
     */
    private Geometry addLightSphere(float Shininess) {
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setColor("Diffuse", ColorRGBA.Red);// 在漫射光照射下反射的颜色。
        mat.setColor("Ambient", ColorRGBA.Red);// 在环境光照射下，反射的颜色。
        mat.setColor("Specular", ColorRGBA.White);// 镜面反射时，高光的颜色
        // 反光度越低，光斑越大，亮度越低。
        mat.setFloat("Shininess", Shininess);// 光泽度，取值范围1~128。
        // 使用上面设置的Diffuse、Ambient、Specular等颜色
        mat.setBoolean("UseMaterialColors", true);
        Geometry geo = new Geometry("球体", new Sphere(40, 36, 1));
        geo.setMaterial(mat);
        return geo;
    }
    
    /**
     * 添加光源
     */
    private void addLight() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3));
        
        AmbientLight ambient = new AmbientLight();
        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(0.8f));
        ambient.setColor(lightColor.mult(0.2f));
        
        rootNode.addLight(sun);
        rootNode.addLight(ambient);
    }
	
	
}
