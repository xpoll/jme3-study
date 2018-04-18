package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import com.jme3.util.TangentBinormalGenerator;

public class JMETest06 extends SimpleApplication {

    public static void main(String[] args) {
        new JMETest06().start();
    }
    
    @Override
    public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 50);
		
        Box boxshape1 = new Box();
        boxshape1.updateGeometry(new Vector3f(-3f, 1.1f, 0f), 1f, 1f, 1f);
        Geometry cube = new Geometry("My Textured Box", boxshape1);
        Material mat_stl = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture tex_ml = assetManager.loadTexture("Interface/Logo/Monkey.jpg");
        mat_stl.setTexture("ColorMap", tex_ml);
        cube.setMaterial(mat_stl);
        rootNode.attachChild(cube);
        
        Box boxshape3 = new Box();
        boxshape3.updateGeometry(Vector3f.ZERO, 1f, 1f, 0.01f);
        Geometry window_frame = new Geometry("window frame", boxshape3);
        Material mat_tt = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_tt.setTexture("ColorMap", assetManager.loadTexture("Textures/ColoredTex/Monkey.png"));
        mat_tt.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        window_frame.setMaterial(mat_tt);
        rootNode.attachChild(window_frame);
        
        Box boxshape4 = new Box();
        boxshape4.updateGeometry(new Vector3f(3f, -1f, 0f), 1f, 1f, 1f);
        Geometry cube_leak = new Geometry("Leak-through color cube", boxshape4);
        Material mat_tl = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_tl.setTexture("ColorMap", assetManager.loadTexture("Textures/ColoredTex/Monkey.png"));
        mat_tl.setColor("Color", new ColorRGBA(1f, 0f, 1f, 1f));
        cube_leak.setMaterial(mat_tl);
        rootNode.attachChild(cube_leak);
        
        Sphere rock = new Sphere(32, 32, 2f);
        Geometry shiny_rock = new Geometry("Shiny rock", rock);
        rock.setTextureMode(Sphere.TextureMode.Projected);
        TangentBinormalGenerator.generate(rock);
        Material mat_lit = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat_lit.setTexture("DiffuseMap", assetManager.loadTexture("Textures/Terrain/Pond/Pond.jpg"));
        mat_lit.setTexture("NormalMap", assetManager.loadTexture("Textures/Terrain/Pond/Pond_normal.png"));
        mat_lit.setFloat("Shininess", 122f);
        shiny_rock.setMaterial(mat_lit);
        shiny_rock.setLocalTranslation(0, 2, -2);
        shiny_rock.rotate(1.6f, 0, 0);
        rootNode.attachChild(shiny_rock);
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1, 0 ,-2).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
