package cn.blmdz.jme3.test;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class JMETest05 extends SimpleApplication {

    public static void main(String[] args) {
        new JMETest05().start();
    }
    
    protected Geometry player;
    protected Boolean isRunning = true;
    protected BitmapText text;

    @Override
    public void simpleInitApp() {
        JMETestCommon.init(flyCam, assetManager, rootNode, 100);
		
        Box b = new Box();
        b.updateGeometry(Vector3f.ZERO, 1, 1, 1);
        player = new Geometry("Player", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        player.setMaterial(mat);
        rootNode.attachChild(player);
        initKeys();
        
        guiNode.detachAllChildren();
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        text = new BitmapText(guiFont);
        text.setSize(guiFont.getCharSet().getRenderedSize());
        text.setLocalTranslation(300, text.getLineHeight(), 0);
        text.setText("Press P to pause.");
        guiNode.attachChild(text);
	}
    
    private void initKeys() {
    	inputManager.addMapping(String.valueOf(KeyInput.KEY_P), new KeyTrigger(KeyInput.KEY_P));
    	inputManager.addMapping(String.valueOf(KeyInput.KEY_J), new KeyTrigger(KeyInput.KEY_J));
    	inputManager.addMapping(String.valueOf(KeyInput.KEY_K), new KeyTrigger(KeyInput.KEY_K));
    	inputManager.addMapping(String.valueOf(KeyInput.KEY_H), new KeyTrigger(KeyInput.KEY_H),
    			new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));
    	inputManager.addMapping(String.valueOf(KeyInput.KEY_L), new KeyTrigger(KeyInput.KEY_L),
    			new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));
    	inputManager.addMapping(String.valueOf(KeyInput.KEY_SPACE), new KeyTrigger(KeyInput.KEY_SPACE),
    			new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    	
    	inputManager.addListener(actionListener, new String[] {String.valueOf(KeyInput.KEY_P)});
    	inputManager.addListener(analogListener, new String[] {
    			String.valueOf(KeyInput.KEY_J),
    			String.valueOf(KeyInput.KEY_K),
    			String.valueOf(KeyInput.KEY_H),
    			String.valueOf(KeyInput.KEY_L),
    			String.valueOf(KeyInput.KEY_SPACE)
    			});
    }
    
    private ActionListener actionListener = new ActionListener() {
		@Override
		public void onAction(String name, boolean keyPressed, float tpf) {
			if (name.equals(String.valueOf(KeyInput.KEY_P)) && !keyPressed) isRunning = !isRunning;
			if (isRunning) text.setText("Press P to pause.");
			else text.setText("Press P to unpause.");
		}
	};
	
	private AnalogListener analogListener = new AnalogListener() {
		@Override
		public void onAnalog(String name, float value, float tpf) {
			if (isRunning) {
				value = value * 5;
				if (name.equals(String.valueOf(KeyInput.KEY_SPACE))) player.rotate(0, value * speed, 0);
				else if (name.equals(String.valueOf(KeyInput.KEY_K))) {
					Vector3f v = player.getLocalTranslation();
					player.setLocalTranslation(v.x + value * speed, v.y, v.z);
				}else if (name.equals(String.valueOf(KeyInput.KEY_J))) {
					Vector3f v = player.getLocalTranslation();
					player.setLocalTranslation(v.x - value * speed, v.y, v.z);
				}else if (name.equals(String.valueOf(KeyInput.KEY_H))) {
					Vector3f v = player.getLocalTranslation();
					player.setLocalTranslation(v.x, v.y + value * speed, v.z);
				}else if (name.equals(String.valueOf(KeyInput.KEY_L))) {
					Vector3f v = player.getLocalTranslation();
					player.setLocalTranslation(v.x, v.y - value * speed, v.z);
				}
			} else {
				System.out.println("Press P to unpause.");
			}
		}
	};

}
