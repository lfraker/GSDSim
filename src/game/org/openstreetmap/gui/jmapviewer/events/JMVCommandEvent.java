package game.org.openstreetmap.gui.jmapviewer.events;

//License: GPL.

import java.util.EventObject;

/**
 * Used for passing events between UI components and other
 * objects that register as a JMapViewerEventListener
 *
 * @author Jason Huntley
 *
 */
public class JMVCommandEvent extends EventObject {
    public static enum COMMAND {
        MOVE,
        ZOOM
    }

    private COMMAND command;
    /**
     *
     */
    private static final long serialVersionUID = 8701544867914969620L;

    public JMVCommandEvent(COMMAND cmd, Object source) {
        super(source);

        setCommand(cmd);
    }

    public JMVCommandEvent(Object source) {
        super(source);
    }

    /**
     * @return the command
     */
    public COMMAND getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(COMMAND command) {
        this.command = command;
    }
}
