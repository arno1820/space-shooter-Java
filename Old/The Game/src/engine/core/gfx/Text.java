package engine.core.gfx;

import project_Beta.Position;

import java.awt.*;

/**
 * Created by arno1820 on 7/10/16.
 */
public class Text {

    private Position position;
    private String string;

    public Text(String sstring, Position ppos){

        this.position = ppos;
        this.string = sstring;

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
