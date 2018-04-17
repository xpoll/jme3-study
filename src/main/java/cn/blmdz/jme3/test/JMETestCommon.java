package cn.blmdz.jme3.test;

import com.jme3.asset.AssetManager;
import com.jme3.input.FlyByCamera;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Line;

public class JMETestCommon {

    /**
     * + X: Red;
     * + Y: Yellow;
     * + Z: Blue;
     * - XYZ: Write;
     * 
     * Speed: 100;
     */
    public static void init(FlyByCamera flyCam, AssetManager assetManager, Node rootNode, float moveSpeed) {
        flyCam.setMoveSpeed(moveSpeed);

        Line lineX = new Line(new Vector3f(-100, 0, 0), new Vector3f(0, 0, 0));
        Line lineY = new Line(new Vector3f(0, -100, 0), new Vector3f(0, 0, 0));
        Line lineZ = new Line(new Vector3f(0, 0, -100), new Vector3f(0, 0, 0));
        Line lineXX = new Line(new Vector3f(0, 0, 0), new Vector3f(100, 0, 0));
        Line lineYY = new Line(new Vector3f(0, 0, 0), new Vector3f(0, 100, 0));
        Line lineZZ = new Line(new Vector3f(0, 0, 0), new Vector3f(0, 0, 100));
        Geometry x = new Geometry("line", lineX);
        Geometry y = new Geometry("line", lineY);
        Geometry z = new Geometry("line", lineZ);
        Material mxyz = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mxyz.setColor("Color", ColorRGBA.White);
        x.setMaterial(mxyz);
        y.setMaterial(mxyz);
        z.setMaterial(mxyz);
        Node node = new Node();
        node.attachChild(x);
        node.attachChild(y);
        node.attachChild(z);

        Geometry xx = new Geometry("line", lineXX);
        Material mx = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mx.setColor("Color", ColorRGBA.Red);
        xx.setMaterial(mx);
        Geometry yy = new Geometry("line", lineYY);
        Material my = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        my.setColor("Color", ColorRGBA.Yellow);
        yy.setMaterial(my);
        Geometry zz = new Geometry("line", lineZZ);
        Material mz = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mz.setColor("Color", ColorRGBA.Blue);
        zz.setMaterial(mz);
        node.attachChild(xx);
        node.attachChild(yy);
        node.attachChild(zz);
        
        rootNode.attachChild(node);
        
    }
}
