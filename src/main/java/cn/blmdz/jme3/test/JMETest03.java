package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

public class JMETest03 extends SimpleApplication {

    public static void main(String[] args) {
        new JMETest03().start();
    }

    @Override
    public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 100);
        
        Spatial teapot = assetManager.loadModel("Models/Teapot/Teapot.obj");
        Material mat_default = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        teapot.setMaterial(mat_default);
        rootNode.attachChild(teapot);
        
        Box box = new Box();
        box.updateGeometry(Vector3f.ZERO, 2.5f, 2.5f, 1f);
        Spatial wall = new Geometry("Box", box);
        Material mat_brick = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_brick.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        wall.setMaterial(mat_brick);
        wall.setLocalTranslation(2.0f, -2.5f, 0f);
        rootNode.attachChild(wall);
        
        guiNode.detachAllChildren();
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText helloText = new BitmapText(guiFont, false);
        helloText.setSize(guiFont.getCharSet().getRenderedSize());
        helloText.setText("hello world");
        helloText.setLocalTranslation(300, helloText.getLineHeight(), 0);
        guiNode.attachChild(helloText);
        
        
        Spatial ninja = assetManager.loadModel("Models/Ninja/Ninja.mesh.xml");
        ninja.scale(0.05f, 0.05f, 0.05f);
        ninja.rotate(0f, -3f, 0f);
        ninja.setLocalTranslation(0f, -5f, -2f);
        rootNode.attachChild(ninja);
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
        
        assetManager.registerLocator(this.getClass().getResource("/").getPath() + "town.zip", ZipLocator.class);
        
        Spatial gameLevel = assetManager.loadModel("main.scene");
        gameLevel.setLocalTranslation(0f, -5.2f, 0f);
        gameLevel.setLocalScale(2);
        rootNode.attachChild(gameLevel);
        
    }
    
}
