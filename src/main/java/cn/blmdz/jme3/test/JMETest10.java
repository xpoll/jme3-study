package cn.blmdz.jme3.test;

import java.util.ArrayList;
import java.util.List;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.renderer.Camera;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

public class JMETest10 extends SimpleApplication {

	private TerrainQuad terrain;
	Material mat_terrain;
	
	public static void main(String[] args) {
		new JMETest10().start();
	}
	
	
	@Override
	public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 50);
        
        mat_terrain = new Material(assetManager, "Common/MatDefs/Terrain/Terrain.j3md");
        
        mat_terrain.setTexture("m_Alpha", assetManager.loadTexture("Textures/Terrain/splat/alphamap.png"));
        
        Texture grass = assetManager.loadTexture("Textures/Terrain/splat/grass.jpg");
        grass.setWrap(WrapMode.Repeat);
        mat_terrain.setTexture("m_Tex1", grass);
        mat_terrain.setFloat("m_Tex1Scale", 64f);
        
        Texture dirt = assetManager.loadTexture("Textures/Terrain/splat/dirt.jpg");
        dirt.setWrap(WrapMode.Repeat);
        mat_terrain.setTexture("m_Tex2", dirt);
        mat_terrain.setFloat("m_Tex2Scale", 32f);
        
        Texture rock = assetManager.loadTexture("Textures/Terrain/splat/road.jpg");
        rock.setWrap(WrapMode.Repeat);
        mat_terrain.setTexture("m_Tex3", rock);
        mat_terrain.setFloat("m_Tex3Scale", 128f);
        
        AbstractHeightMap heightmap = null;
        Texture heightMapImage = assetManager.loadTexture("Textures/Terrain/splat/mountains512.png");
//        heightmap = new ImageBasedHeightMap(ImageToAwt.convert(heightMapImage.getImage(), false, true, 0));
        heightmap = new ImageBasedHeightMap(heightMapImage.getImage());
        heightmap.load();
        
        terrain = new TerrainQuad("my terrain", 65, 513, heightmap.getHeightMap());
        
        terrain.setMaterial(mat_terrain);
        terrain.setLocalTranslation(0, -100, 0);
        terrain.setLocalScale(2f, 1f, 2f);
        rootNode.attachChild(terrain);
        
        List<Camera> cameras = new ArrayList<>();
        cameras.add(getCamera());
        TerrainLodControl control = new TerrainLodControl(terrain, cameras);
        terrain.addControl(control);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
