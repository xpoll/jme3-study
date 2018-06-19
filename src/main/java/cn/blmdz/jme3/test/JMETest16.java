package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.util.TangentBinormalGenerator;

public class JMETest16 extends SimpleApplication {

	public static void main(String[] args) {
	    AppSettings settings = new AppSettings(true);
	    settings.setTitle("title");
        settings.setResolution(600, 600);
        JMETest16 app = new JMETest16();
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
	}
	
	@Override
	public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 50);

        Material mat = mat1();
        mat = mat2();
        Quad quad = new Quad(8, 8);
        TangentBinormalGenerator.generate(quad);
        Geometry geom = new Geometry("BrickWall", quad);
        geom.setMaterial(mat);
        
        geom.center();
        rootNode.attachChild(geom);
       
        // 添加一个定向光
        DirectionalLight sun = new DirectionalLight(new Vector3f(0, 0, -1));
        rootNode.addLight(sun);
        
        viewPort.setBackgroundColor(new ColorRGBA(0.6f, 0.7f, 0.9f, 1));
	}
    
    private Material mat1() {
        return assetManager.loadMaterial("BrickWall.j3m");
    }
    
    private Material mat2() {
        
        // 加载一个受光材质
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        // 漫反射贴图
        Texture tex = assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg");
        mat.setTexture("DiffuseMap", tex);
        // 法线贴图
        tex = assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall_normal.jpg");
        mat.setTexture("NormalMap", tex);
        // 视差贴图
        tex = assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall_height.jpg");
        mat.setTexture("ParallaxMap", tex);

        mat.setBoolean("UseMaterialColors", true);
        mat.setColor("Diffuse", new ColorRGBA(1, 1, 1, 1));
        mat.setColor("Ambient", new ColorRGBA(0, 0, 0, 1));
        mat.setColor("Specular", new ColorRGBA(0, 0, 0, 1));
        mat.setFloat("Shininess", 2.0f);

        mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        mat.getAdditionalRenderState().setFaceCullMode(FaceCullMode.Off);
        
        return mat;
    }
	
}
