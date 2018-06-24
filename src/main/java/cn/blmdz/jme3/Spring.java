package cn.blmdz.jme3;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

public class Spring {
    private PointMass end1;
    private PointMass end2;
    private float targetLength;
    private float stiffness;
    private float damping;
    
    public Spring(PointMass end1, PointMass end2, float stiffness, float damping, Node gridNode, boolean visible, Geometry defaultLine) {
        this.end1 = end1;
        this.end2 = end2;
        this.stiffness = stiffness;
        this.damping = damping;
        targetLength = end1.getPosition().distance(end2.getPosition()) * 0.95f;
        
        if (visible) {
            defaultLine.addControl(new LineControl(end1,end2));
            gridNode.attachChild(defaultLine);
        }
    }
    
    public void update(float tpf) {
        Vector3f x = end1.getPosition().subtract(end2.getPosition());
        
        float length = x.length();
        if (length > targetLength) {
            x.normalizeLocal();
            x.multLocal(length - targetLength);
            
            Vector3f dv = end2.getVelocity().subtract(end1.getVelocity());
            Vector3f force = x.mult(stiffness);
            force.subtract(dv.mult(damping/10f));
            
            end1.applyForce(force.negate());
            end2.applyForce(force);
        }
    }
}
