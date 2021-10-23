package nustracker.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_HEIGHT = 600;
    private static final double DEFAULT_WIDTH = 740;
    private static final String DEFAULT_GLOW_HEX_CODE = "#E9AFFF";

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;
    private final boolean isLightMode;
    private final String glowHexCode;

    /**
     * Constructs a {@code GuiSettings} with the default height, width and position.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        glowHexCode = DEFAULT_GLOW_HEX_CODE;
        isLightMode = true;
        windowCoordinates = null; // null represent no coordinates
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width and position.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition, boolean isLightMode,
                       String glowHexCode) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.glowHexCode = glowHexCode;
        this.isLightMode = isLightMode;
        windowCoordinates = new Point(xPosition, yPosition);
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public Point getWindowCoordinates() {
        return windowCoordinates != null ? new Point(windowCoordinates) : null;
    }

    public String getGlowHexCode() {
        return glowHexCode;
    }

    public boolean getIsLightMode() {
        return isLightMode;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GuiSettings)) { //this handles null as well.
            return false;
        }

        GuiSettings o = (GuiSettings) other;

        return windowWidth == o.windowWidth
                && windowHeight == o.windowHeight
                && isLightMode == o.getIsLightMode()
                && Objects.equals(glowHexCode, o.getGlowHexCode())
                && Objects.equals(windowCoordinates, o.windowCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates, glowHexCode, isLightMode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Width : ").append(windowWidth).append("\n");
        sb.append("Height : ").append(windowHeight).append("\n");
        sb.append("Position : ").append(windowCoordinates).append("\n");
        sb.append("Using light mode?: ").append(isLightMode).append("\n");
        sb.append("Color: ").append(glowHexCode);
        return sb.toString();
    }
}
