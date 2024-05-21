import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageEditorEC {
    /* Constants (Magic numbers) */
    private static final String PNG_FORMAT = "png";
    private static final String NON_RGB_WARNING =
            "Warning: we do not support the image you provided. \n" +
            "Please change another image and try again.";
    private static final String RGB_TEMPLATE = "(%3d, %3d, %3d) ";
    private static final int BLUE_BYTE_SHIFT = 0;
    private static final int GREEN_BYTE_SHIFT = 8;
    private static final int RED_BYTE_SHIFT = 16;
    private static final int ALPHA_BYTE_SHIFT = 24;
    private static final int BLUE_BYTE_MASK = 0xff << BLUE_BYTE_SHIFT;
    private static final int GREEN_BYTE_MASK = 0xff << GREEN_BYTE_SHIFT;
    private static final int RED_BYTE_MASK = 0xff << RED_BYTE_SHIFT;
    private static final int ALPHA_BYTE_MASK = ~(0xff << ALPHA_BYTE_SHIFT);

    /* Static variables - DO NOT add any additional static variables */
    static int[][] image;

    /**
     * Open an image from disk and return a 2D array of its pixels.
     * Use 'load' if you need to load the image into 'image' 2D array instead
     * of returning the array.
     *
     * @param pathname path and name to the file, e.g. "input.png",
     *                 "D:\\Folder\\ucsd.png" (for Windows), or
     *                 "/User/username/Desktop/my_photo.png" (for Linux/macOS).
     *                 Do NOT use "~/Desktop/xxx.png" (not supported in Java).
     * @return 2D array storing the rgb value of each pixel in the image
     * @throws IOException when file cannot be found or read
     */
    public static int[][] open(String pathname) throws IOException {
        BufferedImage data = ImageIO.read(new File(pathname));
        if (data.getType() != BufferedImage.TYPE_3BYTE_BGR &&
                data.getType() != BufferedImage.TYPE_4BYTE_ABGR) {
            System.err.println(NON_RGB_WARNING);
        }
        int[][] array = new int[data.getHeight()][data.getWidth()];

        for (int row = 0; row < data.getHeight(); row++) {
            for (int column = 0; column < data.getWidth(); column++) {
                /* Images are stored by column major
                   i.e. (2, 10) is the pixel on the column 2 and row 10
                   However, in class, arrays are in row major
                   i.e. [2][10] is the 11th element on the 2nd row
                   So we reverse the order of i and j when we load the image.
                 */
                array[row][column] = data.getRGB(column, row) & ALPHA_BYTE_MASK;
            }
        }

        return array;
    }

    /**
     * Load an image from disk to the 'image' 2D array.
     *
     * @param pathname path and name to the file, see open for examples.
     * @throws IOException when file cannot be found or read
     */
    public static void load(String pathname) throws IOException {
        image = open(pathname);
    }

    /**
     * Save the 2D image array to a PNG file on the disk.
     *
     * @param pathname path and name for the file. Should be different from
     *                 the input file. See load for examples.
     * @throws IOException when file cannot be found or written
     */
    public static void save(String pathname) throws IOException {
        BufferedImage data = new BufferedImage(
                image[0].length, image.length, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < data.getHeight(); row++) {
            for (int column = 0; column < data.getWidth(); column++) {
                // reverse it back when we write the image
                data.setRGB(column, row, image[row][column]);
            }
        }
        ImageIO.write(data, PNG_FORMAT, new File(pathname));
    }

    /**
     * Unpack red byte from a packed RGB int
     *
     * @param rgb RGB packed int
     * @return red value in that packed pixel, 0 <= red <= 255
     */
    private static int unpackRedByte(int rgb) {
        return (rgb & RED_BYTE_MASK) >> RED_BYTE_SHIFT;
    }

    /**
     * Unpack green byte from a packed RGB int
     *
     * @param rgb RGB packed int
     * @return green value in that packed pixel, 0 <= green <= 255
     */
    private static int unpackGreenByte(int rgb) {
        return (rgb & GREEN_BYTE_MASK) >> GREEN_BYTE_SHIFT;
    }

    /**
     * Unpack blue byte from a packed RGB int
     *
     * @param rgb RGB packed int
     * @return blue value in that packed pixel, 0 <= blue <= 255
     */
    private static int unpackBlueByte(int rgb) {
        return (rgb & BLUE_BYTE_MASK) >> BLUE_BYTE_SHIFT;
    }

    /**
     * Pack RGB bytes back to an int in the format of
     * [byte0: unused][byte1: red][byte2: green][byte3: blue]
     *
     * @param red   red byte, must satisfy 0 <= red <= 255
     * @param green green byte, must satisfy 0 <= green <= 255
     * @param blue  blue byte, must satisfy 0 <= blue <= 255
     * @return packed int to represent a pixel
     */
    private static int packInt(int red, int green, int blue) {
        return (red << RED_BYTE_SHIFT)
                + (green << GREEN_BYTE_SHIFT)
                + (blue << BLUE_BYTE_SHIFT);
    }

    /**
     * Print the current image 2D array in (red, green, blue) format.
     * Each line represents a row in the image.
     */
    public static void printImage() {
        for (int[] ints : image) {
            for (int pixel : ints) {
                System.out.printf(
                        RGB_TEMPLATE,
                        unpackRedByte(pixel),
                        unpackGreenByte(pixel),
                        unpackBlueByte(pixel));
            }
            
            System.out.println();
        }
    }


public static void rotate (int degree) { 

     //not evenly divisible meaning must do
     if (degree <= 0 || degree % 90 != 0) {
        return;
     }   

     //column row shi idk man disc mess me up. 
     int row = image.length;
     int k = 0;

     //checks how many rotations it must do. 
     if (degree - 90 != 0) {
        for (k = 0; degree > 90; degree -= 90) {
            k++;
        }
    }


    
    
    //if picture is a square
    if(image.length == image[0].length) {
        
        for (int f = 0; f <= k; f++) {
            //row
            int[][] temp = new int[image.length][image[0].length];
            for (int i = 0; i < row ; i++) {
                //column
                for (int j = 0; j < image[i].length ; j++) {
                    temp[j][row - 1 - i] = image[i][j];
                }
            }
            image = temp;
        }
    }

    else {
    //resize the array
        int[][] temp = new int[image[0].length][image.length];
        for (int f = 0; f <= k; f++) {
            int number_of_row_in_old = image.length;
            //row
            for (int i = 0; i < number_of_row_in_old ; i++) {
                //column
                for (int j = 0; j < image[i].length ; j++) {

                    temp[j][number_of_row_in_old - 1 - i] = image[i][j];   
                }
            }
            image = temp; // 90 degrees
            temp = new int[image[0].length][image.length]; // black image with the dimensions swapped again
        }
    }    
}


    public static void downSample(int heightScale, int widthScale) {
        //less than one than the heieght or width respectively?
        if (image.length % heightScale != 0 || image[0].length % widthScale != 0 || heightScale > image.length|| widthScale > image[0].length || heightScale < 1 || widthScale < 1 ) {
            System.out.println("failed");
            return;
         }   

        //
        int oldColumn = image[0].length;
        int oldRow = image.length;
        //
        int newColumn = oldColumn/widthScale ;
        int newRow = oldRow/heightScale;

        int[][] scaledImage = new int[newRow][newColumn]; 
        
        int redByte = 0;
        int greenByte = 0;
        int blueByte = 0;

        int redAverage = 0;
        int greenAverage = 0; 
        int blueAverage = 0;  
        
        int count = 0;
        
        for(int L = 0; L < oldRow; L += heightScale) {
            for (int k = 0; k < oldColumn; k += widthScale ) {
                //row
                for (int i = L; i < L + heightScale; i++) {
                    //column
                    for (int j = k; j < k + widthScale; j++) {
                        redByte = redByte + unpackRedByte(image[i][j]);
                        greenByte = greenByte + unpackGreenByte(image[i][j]);
                        blueByte = blueByte + unpackBlueByte(image[i][j]);
                        count++;
                    }
                }

                redAverage = redByte/count;
                greenAverage = greenByte/count;
                blueAverage = blueByte/count;


                scaledImage[L / heightScale][k / widthScale] = packInt(redAverage, greenAverage, blueAverage);


                redByte = 0;
                greenByte = 0;
                blueByte = 0;
        

                redAverage = 0;
                greenAverage = 0; 
                blueAverage = 0; 
                count = 0; 

            }
        }    
        image = scaledImage;
    }

    public static int patch ( int startRow, int startColumn, int[][] patchImage,
     int transparentRed, int transparentGreen, int transparentBlue) {

        //ending row and ending column missing still
        if (startRow < 0 || startRow > image.length || startColumn < 0 ||
         startColumn > image[0].length || patchImage.length > image.length || patchImage[0].length > image[0].length || startRow + patchImage.length > image.length || startColumn + patchImage[0].length > image[0].length) {
            return 0;

        }

        int patchedPixels = 0;

        for (int i = startRow ;  i < startRow + patchImage.length; i++) {
            //column
            for (int j = startColumn;  j < startColumn + patchImage[i - startRow].length; j++) {
                
                int toReplace =  patchImage[i - startRow][j - startColumn];
                if (unpackBlueByte(toReplace) == transparentBlue && unpackGreenByte(toReplace) ==
                 transparentGreen && unpackRedByte(toReplace) == transparentRed ) {
                    continue;

                }
                   
                image[i][j] = toReplace;
                patchedPixels += 1; 
            }
        }

        return patchedPixels;
    }

    public static void main(String[] args) throws IOException {
        load("input2.png");
        rotate(180);
        int[][] patchImage = open("input1.png");
        int patchedPixels = patch(50, 100, patchImage, 255, 255, 255);
        
        downSample(2, 3);
        save("output.png");
        
}
}
