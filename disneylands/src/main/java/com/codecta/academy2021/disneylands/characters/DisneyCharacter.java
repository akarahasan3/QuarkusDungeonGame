package com.codecta.academy2021.disneylands.characters;

import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.image.rasterize.ShapeRasterizer;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.Raster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.PictureWidget;
import org.alcibiade.asciiart.widget.TextWidget;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class DisneyCharacter {
    protected String imageFilename;
    public abstract String getName();
    public String welcomes() {
        return "Hello!";
    }
    public String picture()
    {
        if(imageFilename == null) {
            return "Sorry, no picture available";
        }
        ClassLoader classLoader = DisneyCharacter.class.getClassLoader();

        InputStream resourceAsStream = classLoader.getResourceAsStream(imageFilename);
        try {
            BufferedImage image = ImageIO.read(resourceAsStream);
            TextWidget widget = new PictureWidget(new TextBoxSize(80, 30),
                    image, new ShapeRasterizer());
            Raster raster = new ExtensibleCharacterRaster();

            widget.render(new RasterContext(raster));
            return raster.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "Error in converting";
        }
    }
}
