
/* This code alters and changes a PNG image by rotating it 90 degrees clockwise only direction however many loops. It also downscales an image by some multiple factor of the original image's dimension. finally, it is able to "patch" a second iamge onto the first in any allowable area within the parameters of the first image. 
 */




/*this portion here illustrates the imports needed to complete this assignment */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/* This class here is the main body of the code. This class allows for the user to manipulate any inputed image and allow the user to downscale, patch another image, or simply rotate about a 90 degree cw direction.  */

public class ImageEditor {
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

    /**
     * this method "patches" a second image over the first if the given 
     * parameters are met
     * @param degree degrees wanted to rotate
     * @return no return
     */ 
    public static void rotate (int degree) { 

     //not evenly divisible or if degree is less or equal to 0 
     if (degree <= 0 || degree % 90 != 0) {
        return;
     }   

     //local variables needed for completion
     int row = image.length;
     int rotates = 0;

     //checks how many rotations it must do. 
     if (degree - 90 != 0) {
        for (rotates = 0; degree > 90; degree -= 90) {
            rotates++;
        }
    }


    
    
    //if picture is a square
    if(image.length == image[0].length) {
        //rotate x many times
        for (int check = 0; check <= rotates; check++) {
            //row
            int[][] temp = new int[image.length][image[0].length];
            for (int countRow = 0; countRow < row ; countRow++) {
                //column
                for (int countColumn = 0; countColumn < image[countRow].length ; countColumn++) {
                    //reasign rgb values from one array to temp array
                    temp[countColumn][row - 1 - countRow] = image[countRow][countColumn];
                }
            }
            //reassign temp array back to main image
            image = temp;
        }
    }

    else {
    //if image is a rectiangle
    //resize a temp array
        int[][] temp = new int[image[0].length][image.length];
        //go through how many loops needed
        for (int check = 0; check <= rotates; check++) {
            //redefine old rows
            int number_of_row_in_old = image.length;
            //row
            for (int countRow = 0; countRow < number_of_row_in_old ; countRow++) {
                //column
                for (int countColumn = 0; countColumn < image[countRow].length ; countColumn++) {

                    //reassign pixels to new temp array
                    temp[countColumn][number_of_row_in_old - 1 - countRow] = image[countRow][countColumn];   
                }
            }
            // rotated image degrees
            image = temp; 
            // black image with the dimensions swapped again
            temp = new int[image[0].length][image.length]; 
        }
    }    
}

    /**
     * this method "patches" a second image over the first if the given 
     * parameters are met
     * @param heightScale downscale factor for height
     * @param widthScale downscale factor for width
     * @return no return
     */ 
    public static void downSample(int heightScale, int widthScale) {
        //edgecases
        if (image.length % heightScale != 0 || image[0].length % widthScale != 0 || heightScale > image.length|| widthScale > image[0].length || heightScale < 1 || widthScale < 1 ) {
            return;
         }   

        //local variables defined all here
        int oldColumn = image[0].length;
        int oldRow = image.length;
        
        int newColumn = oldColumn/widthScale ;
        int newRow = oldRow/heightScale;

        //final scaledImage array creation with proper dimensions
        int[][] scaledImage = new int[newRow][newColumn]; 
        
        //more local variables
        int redByte = 0;
        int greenByte = 0;
        int blueByte = 0;
        //averages for calculations later 
        int redAverage = 0;
        int greenAverage = 0; 
        int blueAverage = 0;  
        
        //counter for how many runs
        int count = 0;
        
        //this set of 2 for loops are the starts of each block i need to capture
        for(int startBlockRow = 0; startBlockRow < oldRow; startBlockRow += heightScale) {
            for (int startBlockColumn = 0; startBlockColumn < oldColumn; startBlockColumn += widthScale ) {
                //row of index
                for (int indexRow = startBlockRow; indexRow < startBlockRow + heightScale; indexRow++) {
                    //column of index
                    for (int indexColumn = startBlockColumn; indexColumn < startBlockColumn + widthScale; indexColumn++) {
                        //grabs rgb values of red
                        redByte = redByte + unpackRedByte(image[indexRow][indexColumn]);
                        //grabs rgb values of green
                        greenByte = greenByte + unpackGreenByte(image[indexRow][indexColumn]);
                        //grabs rgb values of blue
                        blueByte = blueByte + unpackBlueByte(image[indexRow][indexColumn]);
                       //updated counter for total runs
                        count++;
                    }
                }

                //average calculations for each block
                redAverage = redByte/count;
                greenAverage = greenByte/count;
                blueAverage = blueByte/count;

                //asigns the packints to the scaled image for each block its at
                scaledImage[startBlockRow / heightScale][startBlockColumn / widthScale] = packInt(redAverage, greenAverage, blueAverage);

                //reset values so theres no carry over
                redByte = 0;
                greenByte = 0;
                blueByte = 0;
        
                //reset values so theres no carry over
                redAverage = 0;
                greenAverage = 0; 
                blueAverage = 0; 
                count = 0; 

            }
        }    
        //sets scaledImage back to original image
        image = scaledImage;
    }

   

     /**
     * this method "patches" a second image over the first if the given 
     * parameters are met
     * @param startRow start row 
     * @param startColumn start column
     * @param patchImage image being patched
     * @param transparentRed values of red
     * @param transparentGreen values of green
     * @param transparentBlue values of blue
     * @return returns number of patched pixels.
     */
    public static int patch ( int startRow, int startColumn, int[][] patchImage,
     int transparentRed, int transparentGreen, int transparentBlue) {

        //edges cases
        if (startRow < 0 || startRow > image.length || startColumn < 0 ||
         startColumn > image[0].length || patchImage.length > image.length || patchImage[0].length > image[0].length || startRow + patchImage.length > image.length || startColumn + patchImage[0].length > image[0].length) {
            return 0;

        }

        //localvariable defined
        int patchedPixels = 0;

        //indexes for every row
        for (int indexRow = startRow ;  indexRow < startRow + patchImage.length; indexRow++) {
            //indexs for every column
            for (int indexColumn = startColumn;  indexColumn < startColumn + patchImage[indexRow - startRow].length; indexColumn++) {
                //values in indexes that are meant to be replaced
                int toReplace =  patchImage[indexRow - startRow][indexColumn - startColumn];
                //if true, dont replace
                if (unpackBlueByte(toReplace) == transparentBlue && unpackGreenByte(toReplace) ==
                 transparentGreen && unpackRedByte(toReplace) == transparentRed ) {
                    //go to next iteration
                    continue;

                }
                
                //replace if values in indexes dont match 
                image[indexRow][indexColumn] = toReplace;
                patchedPixels += 1; 
            }
        }

        //return how many pixels were patched
        return patchedPixels;
    }
}
