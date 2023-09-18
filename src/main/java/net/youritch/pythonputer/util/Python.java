package net.youritch.pythonputer.util;

import net.youritch.pythonputer.PythonputerMod;
import org.python.util.PythonInterpreter;

public class Python {
    public static String state = "PENDING";
    private PythonInterpreter pyInter;
    public static int runners = 0;
    public Python(String name, String type) {
        try {
            this.pyInter = new PythonInterpreter();
        } catch (Error e) {
            PythonputerMod.LOGGER.error("Error during creation of an pythonInterpreter :\n"+e);
            state = "FAIL";
           return;
        }
        /*if (newPyInter == null) {
            state = "FAIL";
            return;
        }*/
        state = "RUNNING";
        PythonputerMod.pyInterpreters.put(name, this);
        //load api
    }
    public void execfile() {

    }

    public PythonInterpreter getPyInter() {
        return pyInter;
    }
}
