package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class JMETest04 extends SimpleApplication {

    public static void main(String[] args) {
        new JMETest04().start();
    }

    protected Geometry player1;
    protected Geometry player2;
    static int i = 0;

    @Override
    public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 100);
        
        Box box1 = new Box(1f, 1f, 1f);
        box1.updateGeometry(Vector3f.ZERO, 1f, 1f, 1f);
        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Blue);
        player1 = new Geometry("box", box1);
        player1.setMaterial(mat1);
        rootNode.attachChild(player1);
        
        Box box2 = new Box();
        box2.updateGeometry(new Vector3f(3f, 0f, 0f), 1f, 1f, 1f);
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Red);
        player2 = new Geometry("box", box2);
        player2.setMaterial(mat2);
        rootNode.attachChild(player2);
    }
    
    @Override
    public void simpleUpdate(float tpf) {
//        player1.rotate(0, FastMath.PI * tpf / 3, 0);
        System.out.println(i);
        if (i >= 0) {
            i = -1;
            player1.scale(i);
        } else {
            i = 1;
            player1.scale(i);
        }

        player2.rotate(3 * FastMath.PI * tpf / 3, 0, 0);
        player2.move(new Vector3f(0, 0, tpf));
        
        
    }

}
