import java.awt.Color;

/**
 * red channel filter - items that have a high red value for the pixel data appear lighter, with a maximum value of 255 appearing as white
 * 
 * @author Brennen Lui
 * @version 1.0
 */
public class WarholFilter extends Filter
{
    /**
     * Constructor for objects of class WarholFilter.
     * @param name The name of the filter.
     */
    public WarholFilter(String name)
    {
        super(name);
    }

    /**
     * Apply this filter to an image.
     * 
     * @param  image  The image to be changed by this filter.
     */
    public void apply(OFImage image)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        int halfHeight = height / 2;
        int halfWidth = width / 2;
        OFImage original = new OFImage(image);
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int localx;
                if (x < halfWidth){
                    localx = x;
                }
                else{
                    localx = x - halfWidth;
                }
                int localy;
                if (y < halfHeight){
                    localy = y;
                }
                else{
                    localy = y - halfHeight;
                }
                Color pix;
                if (x < halfWidth && y < halfHeight){
                    pix = original.getPixel(localx * 2, localy * 2);
                }
                else if (x >= halfWidth && y < halfHeight){
                    pix = original.getPixel(localx * 2, localy * 2);
                    pix = new Color(pix.getRed(), 0, 0);
                }
                else if (x < halfWidth && y >= halfHeight){
                    pix = original.getPixel(localx * 2, localy * 2);
                    pix = new Color(0, pix.getGreen(), 0);
                }
                else{
                    pix = original.getPixel(localx * 2, localy * 2);
                    pix = new Color(0, 0, pix.getBlue());    
                }
                image.setPixel(x, y, pix);
            }
        }
    }
}
