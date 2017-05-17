package graphics;

public class Render {
	public int width;
    public int height;
    public int[] pixels;

    public Render(int width, int height)
    {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    /* Draws pixels to screen */
    public void draw(Render render, int xOffset, int yOffset)
    {
        for(int y = 0; y < render.height; y++)
        {
            int yPixels = y + yOffset;
            if(yPixels < 0 || yPixels >= this.height)
            {
                continue;
            }

            for(int x = 0; x < render.width; x++)
            {
                int xPixels = x + xOffset;
                if(xPixels < 0 || xPixels > this.width)
                {
                    continue;
                }

                /* enables alpha support (allows pixels with no data) */
                int alpha = render.pixels[x + y * render.width];
                if(alpha > 0)
                {
                    pixels[xPixels + yPixels * width] = alpha;
                }
            }
        }
    }
}
