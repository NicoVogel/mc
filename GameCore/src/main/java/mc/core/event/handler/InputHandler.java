package mc.core.event.handler;

import mc.core.engine.Engine;
import mc.core.engine.InputManager;
import mc.core.event.interfaces.Handler;

public class InputHandler implements Handler {


    private static InputHandler instance = null;
    private InputManager im = null;

    public static InputHandler Instance(Engine Engine) {
        if (InputHandler.instance == null) {
            InputHandler.instance = new InputHandler(engine);
        }
        return InputHandler.instance;
    }

    private InputHandler(Engine engine)
        this.im = InputManager.Instance(engine);

    }

    /**
     * Below all ingame actions will be registered to listen for the configured key
     * TODO implement a way to depend the action on the config file
     */


    private static void actionBuildBlock(){
        
    }


    /**
     * Impl of Handler methods
     */
    @Override
    public void registerObject(Object o, EventRegister s) {

    }

    @Override
    public void removeObjectFromAction(Object o, EventRegister s) {

    }

    @Override
    public void removeObjectFromAllActions(Object o) {

    }


}