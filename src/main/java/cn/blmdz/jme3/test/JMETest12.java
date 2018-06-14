package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.system.AppSettings;
import com.jme3.util.BufferUtils;

public class JMETest12 extends SimpleApplication {

	public static void main(String[] args) {
	    AppSettings settings = new AppSettings(true);
	    settings.setTitle("title");
        settings.setResolution(600, 600);
        JMETest12 app = new JMETest12();
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
	}
	
	@Override
	public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 50);
        
        float[] vertex = {
                2.5f, 4f, 0,
                1f, 3.26f, 0f,
                1f, 1.74f, 0f,
                2.5f, 1f, 0f,
                4f, 1.74f, 0f,
                4f, 3.26f, 0f
        };
        
        int[] indices = {
                0, 1, 2,
                2, 3, 4,
                4, 5, 0,
                0, 2, 4
        };
        
        Mesh mesh = new Mesh();

        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertex));
        mesh.setBuffer(Type.Index, 1, BufferUtils.createIntBuffer(indices));
        
        mesh.updateBound();
        mesh.setStatic();
        
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Geometry geom = new Geometry("六边形");
        geom.move(-2.5f, -2.5f, 0);
        geom.setMesh(mesh);
        geom.setMaterial(mat);
        
        rootNode.attachChild(geom);
        
	}
	
}
