package game.org.openstreetmap.gui.jmapviewer;

//License: GPL. Copyright 2008 by Jan Peter Stotz

import java.awt.Color;


import game.org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

/**
 * A simple implementation of the {@link MapMarker} interface. Each map marker
 * is painted as a circle with a black border line and filled with a specified
 * color.
 *
 * @author Jan Peter Stotz
 *
 */
public class MapMarkerDot extends MapMarkerCircle {
    public enum Status { BEHIND, ONTIME, AHEAD };

    public Status status = Status.ONTIME;

    public static final int DOT_RADIUS = 5;

    public MapMarkerDot(Coordinate coord) {
        this(null, null, coord);
        this.writeClick = false;
    }
    
    public void showClick() {
    	this.writeClick = true;
    }
    
    public void hideClick() {
    	this.writeClick = false;
    }
    public MapMarkerDot(String name, Coordinate coord) {
        this(null, name, coord);
        this.writeClick = false;

    }
    public MapMarkerDot(Layer layer, Coordinate coord) {
        this(layer, null, coord);
        this.writeClick = false;

    }
    public MapMarkerDot(Layer layer, String name, Coordinate coord) {
        this(layer, name, coord, getDefaultStyle());
        this.writeClick = false;

    }
    public MapMarkerDot(Color color, double lat, double lon) {
        this(null, null, lat, lon);
        setColor(color);
        this.writeClick = false;

    }
    public MapMarkerDot(double lat, double lon) {
        this(null, null, lat, lon);
        this.writeClick = false;

    }
    public MapMarkerDot(Layer layer, double lat, double lon) {
        this(layer, null, lat, lon);
        this.writeClick = false;

    }
    public MapMarkerDot(Layer layer, String name, double lat, double lon) {
        this(layer, name, new Coordinate(lat, lon), getDefaultStyle());
        this.writeClick = false;

    }
    public MapMarkerDot(Layer layer, String name, Coordinate coord, Style style) {
        super(layer, name, coord, DOT_RADIUS, STYLE.FIXED, style);
        this.writeClick = false;

    }

    public static Style getDefaultStyle(){
        return new Style(Color.BLACK, Color.GREEN, null, getDefaultFont());
    }
    
    public void setBehind() {
        this.getStyle().setBackColor(Color.RED);
        this.status = Status.BEHIND;
    }
    
    public void setOnTime() {
        this.getStyle().setBackColor(Color.GREEN);
        this.status = Status.ONTIME;
    }
    
    public void setAhead() {
        this.getStyle().setBackColor(new Color(0x00FFAA));
        this.status = Status.AHEAD;
    }
}
