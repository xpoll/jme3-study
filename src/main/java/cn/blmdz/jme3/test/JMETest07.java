package cn.blmdz.jme3.test;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.debug.SkeletonDebugger;

public class JMETest07 extends SimpleApplication implements AnimEventListener {

	public static void main(String[] args) {
		new JMETest07().start();
	}
	
	private AnimChannel channel;
	private AnimControl control;
	
	Node player = null;

	@Override
	public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 50);
        initKeys();
		
        viewPort.setBackgroundColor(ColorRGBA.LightGray);
        
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
        rootNode.addLight(dl);
        
        player = (Node) assetManager.loadModel("Models/Oto/Oto.mesh.xml");
        player.setLocalScale(0.5f);
        rootNode.attachChild(player);
        
        control = player.getControl(AnimControl.class);
        control.addListener(this);
        channel = control.createChannel();
        channel.setAnim("stand");
        
        SkeletonDebugger debug = new SkeletonDebugger("skeletion", control.getSkeleton());
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        mat.getAdditionalRenderState().setDepthTest(false);
        debug.setMaterial(mat);
        player.attachChild(debug);
        
	}

	@Override
	public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
		System.out.println(animName);
		if (animName.equals("Walk")) {
			channel.setAnim("push", 0.5f);
			channel.setLoopMode(LoopMode.DontLoop);
			channel.setSpeed(1f);
		} else if (animName.equals("push")) {
			channel.setAnim("pull", 0.5f);
			channel.setLoopMode(LoopMode.DontLoop);
			channel.setSpeed(1f);
		} else if (animName.equals("pull")) {
			channel.setAnim("Dodge", 0.5f);
			channel.setLoopMode(LoopMode.DontLoop);
			channel.setSpeed(1f);
		} else if (animName.equals("Dodge")) {
			channel.setAnim("Walk", 0.5f);
			channel.setLoopMode(LoopMode.DontLoop);
			channel.setSpeed(1f);
		}
	}

	@Override
	public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
		// TODO Auto-generated method stub
		
	}
	
	private void initKeys() {
		inputManager.addMapping("Walk", new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addListener(actionListener, "Walk");
	}
	
	private ActionListener actionListener = new ActionListener() {
		
		@Override
		public void onAction(String name, boolean keypressed, float tpf) {
			if (name.equals("Walk") && !keypressed) {
//				if (!channel.getAnimationName().equals("Walk")) {
					channel.setAnim("Walk", 0.5f);
					channel.setLoopMode(LoopMode.Loop);
//				}
			}
		}
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
