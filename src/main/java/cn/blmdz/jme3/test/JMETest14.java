package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import com.jme3.util.TangentBinormalGenerator;

public class JMETest14 extends SimpleApplication {

	public static void main(String[] args) {
	    AppSettings settings = new AppSettings(true);
	    settings.setTitle("title");
        settings.setResolution(600, 600);
        JMETest14 app = new JMETest14();
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
	}
	
	@Override
	public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 50);

        addUnshadedSphere();
        addLightSphere();
        addUnshadedBox();
        addLightBox();
        
        addLight();
        
        viewPort.setBackgroundColor(new ColorRGBA(0.6f, 0.7f, 0.9f, 1));
	}
    
    /**
     * 创造一个红色的小球，应用无光材质。
     */
    private void addUnshadedSphere() {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        Geometry geo = new Geometry("普通球体", new Sphere(20, 40, 1));
        geo.setMaterial(mat);
        geo.move(4, 3, 0);
        
        rootNode.attachChild(geo);
    }
    
    /**
     * 创造一个红色的小球，应用受光材质。
     */
    private void addLightSphere() {
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setColor("Diffuse", ColorRGBA.Red);// 在漫射光照射下反射的颜色。
        mat.setColor("Ambient", ColorRGBA.Red);// 在环境光照射下，反射的颜色。
        mat.setColor("Specular", ColorRGBA.White);// 镜面反射时，高光的颜色
        // 反光度越低，光斑越大，亮度越低。
        mat.setFloat("Shininess", 32);// 反光度
        // 使用上面设置的Diffuse、Ambient、Specular等颜色
        mat.setBoolean("UseMaterialColors", true);
        Geometry geo = new Geometry("文艺球体", new Sphere(20, 40, 1));
        geo.setMaterial(mat);
        geo.move(0, 3, 0);
        
        rootNode.attachChild(geo);
    }
    
    /**
     * 创造一个方块，应用无光材质。
     */
    private void addUnshadedBox() {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        Geometry geo = new Geometry("普通方块", new Box(1, 1, 1));
        geo.setMaterial(mat);
        geo.move(4, 0, 0);
        
        rootNode.attachChild(geo);
    }
    
    /**
     * 创造一个方块，应用受光材质。
     */
    private void addLightBox() {
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));// 漫反射贴图
        mat.setTexture("NormalMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall_normal.jpg"));// 法线贴图
        mat.setTexture("ParallaxMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall_height.jpg"));// 视差贴图
        mat.setFloat("Shininess", 122);
//        mat.setColor("GlowColor",ColorRGBA.White);
        Box box = new Box(1, 1, 1);
        TangentBinormalGenerator.generate(box);
        Geometry geo = new Geometry("文艺方块", box);
        geo.setMaterial(mat);
        
        rootNode.attachChild(geo);
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
        
//        rootNode.addLight(sun);
//        rootNode.addLight(ambient);
        
        FilterPostProcessor fpp=new FilterPostProcessor(assetManager);
        BloomFilter bloom = new BloomFilter(BloomFilter.GlowMode.Objects);
        fpp.addFilter(bloom);
        viewPort.addProcessor(fpp);
    }
	
	
}
