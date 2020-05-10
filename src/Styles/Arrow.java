package Styles;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.shape.Line;

/**
 * class to draw my arrow as an edge
 * @author 201613026
 *
 */
public class Arrow extends Group {

    private final Line line;

    /**
     * Default class constructor
     */
    public Arrow() {
        this(new Line(), new Line(), new Line());
    }

    private static final double arrowLength = 20;
    private static final double arrowWidth = 7;

    /**
     * 
     * @param line
     * @param arrow1
     * @param arrow2
     */
    private Arrow(Line line, Line arrow1, Line arrow2) {
        super(line, arrow1, arrow2);
        this.line = line;
        InvalidationListener updater = o -> {
            double ex = getEndX();
            double ey = getEndY();
            double sx = getStartX();
            double sy = getStartY();

            arrow1.setEndX(ex);
            arrow1.setEndY(ey);
            arrow2.setEndX(ex);
            arrow2.setEndY(ey);

            if (ex == sx && ey == sy) {
                // arrow parts of length 0
                arrow1.setStartX(ex);
                arrow1.setStartY(ey);
                arrow2.setStartX(ex);
                arrow2.setStartY(ey);
            } else {
                double factor = arrowLength / Math.hypot(sx-ex, sy-ey);
                double factorO = arrowWidth / Math.hypot(sx-ex, sy-ey);

                // part in direction of main line
                double dx = (sx - ex) * factor;
                double dy = (sy - ey) * factor;

                
                // part orthogonal to main line
                double ox = (sx - ex) * factorO;
                double oy = (sy - ey) * factorO;

                arrow1.setStartX(ex + dx - oy);
                arrow1.setStartY(ey + dy + ox);
                arrow2.setStartX(ex + dx + oy);
                arrow2.setStartY(ey + dy - ox);
            }
        };

        // add updater to properties
        startXProperty().addListener(updater);
        startYProperty().addListener(updater);
        endXProperty().addListener(updater);
        endYProperty().addListener(updater);
        updater.invalidated(null);
    }

    // start/end properties

    /**
     * function to setStartX
     * @param value
     */
    public final void setStartX(double value) {
        line.setStartX(value);
    }

    /**
     * function to getStartX
     * @return
     */
    public final double getStartX() {
        return line.getStartX();
    }

    /**
     * function to return startXProperty
     * @return
     */
    public final DoubleProperty startXProperty() {
        return line.startXProperty();
    }

    /**
     * function to setStartY
     * @param value
     */
    public final void setStartY(double value) {
        line.setStartY(value);
    }

    /**
     * function to getStartY
     * @return
     */
    public final double getStartY() {
        return line.getStartY();
    }

    /**
     * function to return startYProperty
     * @return
     */
    public final DoubleProperty startYProperty() {
        return line.startYProperty();
    }

    /**
     * function to setEndX
     * @param value
     */
    public final void setEndX(double value) {
        line.setEndX(value);
    }

    /**
     * function to getEndX
     * @return
     */
    public final double getEndX() {
        return line.getEndX();
    }

    /**
     * function to return endXProperty
     * @return
     */
    public final DoubleProperty endXProperty() {
        return line.endXProperty();
    }

    /**
     * function to setEndY
     * @param value
     */
    public final void setEndY(double value) {
        line.setEndY(value);
    }

    /**
     * function to getEndY
     * @return
     */
    public final double getEndY() {
        return line.getEndY();
    }

    /**
     * function to return endYProperty
     * @return
     */
    public final DoubleProperty endYProperty() {
        return line.endYProperty();
    }

}