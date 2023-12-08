package model.image;

import model.HaarWaveletTransform;
import model.strategy.FilterStrategy;

/**
 * Represents a 2D image composed of pixels.
 * Provides methods to get pixel data, retrieve the width and
 * height of the image, and set pixel values.
 */
public class Image {
  private Pixel[][] pixels;

  /**
   * Constructs a new Image with the specified 2D array of pixels.
   *
   * @param pixels a 2D array of Pixel objects representing the image data
   */
  public Image(Pixel[][] pixels) {
    this.pixels = pixels;
  }

  /**
   * Returns the 2D array of pixels that make up this image.
   *
   * @return the 2D array of Pixel objects
   */
  public Pixel[][] getPixels() {
    return pixels;
  }

  /**
   * Retrieves the pixel at the specified (x, y) position.
   * Throws an IllegalArgumentException if the x and y coordinates are outside
   * the bounds of the image dimensions.
   *
   * @param x the x-coordinate of the desired pixel
   * @param y the y-coordinate of the desired pixel
   * @return the Pixel object at the specified position
   * @throws IllegalArgumentException if x or y coordinates are out of bounds
   */
  public Pixel getPixel(int x, int y) throws IllegalArgumentException {
    if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
      throw new IllegalArgumentException("Coordinates out of bounds!");
    }
    return pixels[y][x];
  }

  /**
   * Returns the width of this image.
   *
   * @return the width of the image in pixels
   */
  public int getWidth() {
    return pixels[0].length;
  }

  /**
   * Returns the height of this image.
   *
   * @return the height of the image in pixels
   */
  public int getHeight() {
    return pixels.length;
  }

  /**
   * Sets the pixel value at the specified (x, y) position.
   * Throws an IllegalArgumentException if the x and y
   * coordinates are outside the bounds of the image dimensions.
   *
   * @param x     the x-coordinate of the pixel to be set
   * @param y     the y-coordinate of the pixel to be set
   * @param pixel the new Pixel object to be placed at the specified position
   * @throws IllegalArgumentException if x or y coordinates are out of bounds
   */
  public void setPixel(int x, int y, Pixel pixel) {
    if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
      throw new IllegalArgumentException("Coordinates out of bounds!");
    }
    pixels[y][x] = pixel;
  }

  /**
   * Applies a specific processing command to the image, such as extracting
   * a color component or converting the image to sepia tone.
   *
   * @param command the processing command to apply
   * @return a new Image object with the specified processing applied
   */
  private Image processImage(String command) {
    int height = this.getHeight();
    int width = this.getWidth();
    Pixel[][] processedPixels = new Pixel[height][width];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel originalPixel = this.getPixel(x, y);
        switch (command) {
          case "red":
            processedPixels[y][x] = new Pixel(originalPixel.getRed(), 0, 0);
            break;
          case "green":
            processedPixels[y][x] = new Pixel(0, originalPixel.getGreen(), 0);
            break;
          case "blue":
            processedPixels[y][x] = new Pixel(0, 0, originalPixel.getBlue());
            break;
          case "value":
            int maxPixelVal = Math.max(originalPixel.getRed(),
                    Math.max(originalPixel.getGreen(), originalPixel.getBlue()));
            processedPixels[y][x] = new Pixel(maxPixelVal, maxPixelVal, maxPixelVal);
            break;
          case "luma":
            double lumaPixelVal = (0.2126 * originalPixel.getRed()
                    + 0.7152 * originalPixel.getGreen() + 0.0722 * originalPixel.getBlue());
            int roundedLumaValue = (int) Math.round(lumaPixelVal);
            processedPixels[y][x] = new Pixel(roundedLumaValue, roundedLumaValue,
                    roundedLumaValue);
            break;
          case "intensity":
            int intensityPixelVal = ((originalPixel.getRed() + originalPixel.getGreen()
                    + originalPixel.getBlue()) / 3);
            processedPixels[y][x] = new Pixel(intensityPixelVal,
                    intensityPixelVal, intensityPixelVal);
            break;
          case "sepia":
            int originalRed = originalPixel.getRed();
            int originalGreen = originalPixel.getGreen();
            int originalBlue = originalPixel.getBlue();

            int newRed = Math.min(255, (int) (0.393 * originalRed
                    + 0.769 * originalGreen + 0.189 * originalBlue));
            int newGreen = Math.min(255, (int) (0.349 * originalRed
                    + 0.686 * originalGreen + 0.168 * originalBlue));
            int newBlue = Math.min(255, (int) (0.272 * originalRed
                    + 0.534 * originalGreen + 0.131 * originalBlue));

            processedPixels[y][x] = new Pixel(newRed, newGreen, newBlue);
            break;
          default:
            // No action, incorrect command.
        }
      }
    }
    return new Image(processedPixels);
  }

  /**
   * Creates a horizontally flipped copy of this image.
   *
   * @return a new Image object that is a horizontally flipped version of this image
   */
  public Image horizontalFlip() {
    int height = this.getHeight();
    int width = this.getWidth();
    Pixel[][] hFlipPixels = new Pixel[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        hFlipPixels[y][x] = this.getPixel(width - 1 - x, y);
      }
    }
    return new Image(hFlipPixels);
  }

  /**
   * Creates a vertically flipped copy of this image.
   *
   * @return a new Image object that is a vertically flipped version of this image
   */
  public Image verticalFlip() {
    int height = this.getHeight();
    int width = this.getWidth();
    Pixel[][] vFlipPixels = new Pixel[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        vFlipPixels[y][x] = this.getPixel(x, height - 1 - y);
      }
    }
    return new Image(vFlipPixels);
  }

  /**
   * Brightens or darkens the image by a given increment.
   *
   * @param increment the amount to change the brightness by
   * @return a new Image object with the brightness adjusted
   */
  public Image brighten(int increment) {
    int height = this.getHeight();
    int width = this.getWidth();
    Pixel[][] brightenPixels = new Pixel[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel originalPixel = this.getPixel(x, y);
        int red = Math.min(Math.max(originalPixel.getRed() + increment, 0), 255);
        int green = Math.min(Math.max(originalPixel.getGreen() + increment, 0), 255);
        int blue = Math.min(Math.max(originalPixel.getBlue() + increment, 0), 255);
        brightenPixels[y][x] = new Pixel(red, green, blue);
      }
    }
    return new Image(brightenPixels);
  }

  /**
   * Applies a kernel to the image for operations like blurring or sharpening.
   *
   * @param kernel the matrix representing the kernel
   * @return a new Image object with the kernel applied
   */
  private Image applyKernel(double[][] kernel) {
    int height = this.getHeight();
    int width = this.getWidth();
    Pixel[][] newPixels = new Pixel[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double redSum = 0;
        double greenSum = 0;
        double blueSum = 0;
        int kernelRadius = kernel.length / 2;

        for (int i = -kernelRadius; i <= kernelRadius; i++) {
          for (int j = -kernelRadius; j <= kernelRadius; j++) {
            int kernelX = i + kernelRadius;
            int kernelY = j + kernelRadius;

            if (x + i >= 0 && x + i < width && y + j >= 0 && y + j < height) {
              Pixel neighboringPixel = this.getPixel(x + i, y + j);
              redSum += neighboringPixel.getRed() * kernel[kernelY][kernelX];
              greenSum += neighboringPixel.getGreen() * kernel[kernelY][kernelX];
              blueSum += neighboringPixel.getBlue() * kernel[kernelY][kernelX];
            }
          }
        }

        int newRed = Math.min(255, Math.max(0, (int) Math.round(redSum)));
        int newGreen = Math.min(255, Math.max(0, (int) Math.round(greenSum)));
        int newBlue = Math.min(255, Math.max(0, (int) Math.round(blueSum)));

        newPixels[y][x] = new Pixel(newRed, newGreen, newBlue);
      }
    }
    return new Image(newPixels);
  }

  /**
   * Blurs the image using a predefined blur kernel.
   *
   * @return a new Image object that is a blurred version of this image
   */
  public Image blur() {
    double[][] blurKernel = {
            {1.0 / 16, 1.0 / 8, 1.0 / 16},
            {1.0 / 8, 1.0 / 4, 1.0 / 8},
            {1.0 / 16, 1.0 / 8, 1.0 / 16}
    };
    return applyKernel(blurKernel);
  }

  /**
   * Sharpens the image using a predefined sharpening kernel.
   *
   * @return a new Image object that is a sharpened version of this image
   */
  public Image sharpen() {
    double[][] sharpenKernel = {
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };
    return applyKernel(sharpenKernel);
  }

  /**
   * Extracts the red color component from the image, resulting in an image
   * that only contains shades of red.
   *
   * @return a new Image object with only the red color component
   */
  public Image extractRedComponent() {
    return processImage("red");
  }

  /**
   * Extracts the green color component from the image, resulting in an image
   * that only contains shades of green.
   *
   * @return a new Image object with only the green color component
   */
  public Image extractGreenComponent() {
    return processImage("green");
  }

  /**
   * Extracts the blue color component from the image, resulting in an image
   * that only contains shades of blue.
   *
   * @return a new Image object with only the blue color component
   */
  public Image extractBlueComponent() {
    return processImage("blue");
  }

  /**
   * Converts the image to grayscale based on the value component of the pixels,
   * which is the maximum of the red, green, and blue color values.
   *
   * @return a new Image object converted to grayscale using the value component
   */
  public Image toValueComponent() {
    return processImage("value");
  }

  /**
   * Converts the image to grayscale based on the luma component, which
   * is a weighted average of the red, green, and blue color values.
   *
   * @return a new Image object converted to grayscale using the luma component
   */
  public Image toLumaComponent() {
    return processImage("luma");
  }

  /**
   * Converts the image to grayscale based on the average intensity of the
   * red, green, and blue color values.
   *
   * @return a new Image object converted to grayscale using the intensity component
   */
  public Image toIntensityComponent() {
    return processImage("intensity");
  }

  /**
   * Applies a sepia tone to the image, giving it a warm brownish color.
   *
   * @return a new Image object with a sepia tone applied
   */
  public Image toSepia() {
    return processImage("sepia");
  }

  /**
   * Combines the color channels from three separate images to create a new image.
   *
   * @param redImage   the image providing the red color channel
   * @param greenImage the image providing the green color channel
   * @param blueImage  the image providing the blue color channel
   * @return a new image representing the combination of all color channels
   * @throws IllegalArgumentException if the images do not have the same dimensions
   */
  public static Image combineColorChannels(Image redImage, Image greenImage, Image blueImage) {
    int width = redImage.getWidth();
    int height = redImage.getHeight();

    if (greenImage.getWidth() != width || greenImage.getHeight() != height
            || blueImage.getWidth() != width || blueImage.getHeight() != height) {
      throw new IllegalArgumentException("All images must have the same dimensions.");
    }

    Pixel[][] combinedPixels = new Pixel[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = redImage.getPixel(x, y).getRed();
        int green = greenImage.getPixel(x, y).getGreen();
        int blue = blueImage.getPixel(x, y).getBlue();

        combinedPixels[y][x] = new Pixel(red, green, blue);
      }
    }
    return new Image(combinedPixels);
  }

  /**
   * Calculates histograms for each color channel (red, green, and blue) of this image.
   *
   * @return a 2D array containing three histograms (one for each color channel).
   */
  public int[][] calculateHistograms() {
    int[] redHistogram = new int[256];
    int[] greenHistogram = new int[256];
    int[] blueHistogram = new int[256];

    for (int y = 0; y < getHeight(); y++) {
      for (int x = 0; x < getWidth(); x++) {
        Pixel pixel = getPixel(x, y);
        redHistogram[pixel.getRed()]++;
        greenHistogram[pixel.getGreen()]++;
        blueHistogram[pixel.getBlue()]++;
      }
    }
    return new int[][]{redHistogram, greenHistogram, blueHistogram};
  }

  /**
   * Performs color correction on this image based on the peak values of the color histograms.
   * It adjusts each color channel to have the same peak value, normalizing the color balance.
   *
   * @return a new image with color correction applied
   */
  public Image colorCorrect() {

    int[][] histograms = this.calculateHistograms();

    int[] redHistogram = histograms[0];
    int[] greenHistogram = histograms[1];
    int[] blueHistogram = histograms[2];

    int redPeak = findMeaningfulPeak(redHistogram);
    int greenPeak = findMeaningfulPeak(greenHistogram);
    int bluePeak = findMeaningfulPeak(blueHistogram);
    double averagePeak = (redPeak + greenPeak + bluePeak) / 3;

    return applyColorCorrection(redPeak, greenPeak, bluePeak, averagePeak);
  }

  /**
   * Finds the most meaningful peak in a histogram, ignoring the edges.
   * This is used to determine a significant peak in color intensity.
   *
   * @param histogram the histogram to analyze
   * @return the value of the most significant peak intensity
   */
  private int findMeaningfulPeak(int[] histogram) {
    int peak = 0;
    int peakValue = 0;
    for (int i = 10; i < 245; i++) {
      if (histogram[i] > peak) {
        peak = histogram[i];
        peakValue = i;
      }
    }
    return peakValue;
  }

  /**
   * Applies color correction to the image based on the provided peak values
   * for each color channel.
   *
   * @param redPeak     the peak value of the red channel histogram
   * @param greenPeak   the peak value of the green channel histogram
   * @param bluePeak    the peak value of the blue channel histogram
   * @param averagePeak the average of the peak values from all three histograms
   * @return a new image with the color correction applied
   */
  private Image applyColorCorrection(int redPeak, int greenPeak, int bluePeak, double averagePeak) {
    Pixel[][] correctedPixels = new Pixel[this.getHeight()][this.getWidth()];
    double redOffset = averagePeak - redPeak;
    double greenOffset = averagePeak - greenPeak;
    double blueOffset = averagePeak - bluePeak;

    for (int y = 0; y < this.getHeight(); y++) {
      for (int x = 0; x < this.getWidth(); x++) {
        Pixel pixel = this.getPixel(x, y);
        int correctedRed = clamp((int) pixel.getRed() + (int) redOffset);
        int correctedGreen = clamp(pixel.getGreen() + (int) greenOffset);
        int correctedBlue = clamp(pixel.getBlue() + (int) blueOffset);
        correctedPixels[y][x] = new Pixel(correctedRed, correctedGreen, correctedBlue);
      }
    }

    return new Image(correctedPixels);
  }

  /**
   * Clamps a given value to the range of 0 to 255.
   *
   * @param value the value to clamp
   * @return the clamped value
   */
  private int clamp(int value) {
    return Math.max(0, Math.min(255, value));
  }

  /**
   * Adjusts the levels of an image by modifying the range of values.
   * The parameters b, m, and w represent the black point, mid-point, and white point respectively.
   *
   * @param b the black point (minimum intensity value)
   * @param m the mid-point (mid-range intensity value)
   * @param w the white point (maximum intensity value)
   * @return a new image with the levels adjusted
   * @throws IllegalArgumentException if the level values are out of range or
   *                                  not in ascending order
   */
  public Image adjustLevels(int b, int m, int w) {
    if (b < 0 || b > 255 || m < 0 || m > 255 || w < 0 || w > 255) {
      throw new IllegalArgumentException("Level values must be between 0 and 255.");
    }

    if (!(b <= m && m <= w)) {
      throw new IllegalArgumentException("Level values must be in ascending order (b <= m <= w).");
    }

    Pixel[][] pixels = new Pixel[getHeight()][getWidth()];

    double[] coefficients = calculateLevelCoefficients(b, m, w);

    for (int y = 0; y < getHeight(); y++) {
      for (int x = 0; x < getWidth(); x++) {
        Pixel pixel = getPixel(x, y);

        int newRed = clamp((int) (coefficients[0] * pixel.getRed() * pixel.getRed()
                + coefficients[1] * pixel.getRed() + coefficients[2]));
        int newGreen = clamp((int) (coefficients[0] * pixel.getGreen() * pixel.getGreen()
                + coefficients[1] * pixel.getGreen() + coefficients[2]));
        int newBlue = clamp((int) (coefficients[0] * pixel.getBlue() * pixel.getBlue()
                + coefficients[1] * pixel.getBlue() + coefficients[2]));

        pixels[y][x] = new Pixel(newRed, newGreen, newBlue);
      }
    }

    return new Image(pixels);
  }

  /**
   * Calculates and returns an array of coefficients based on the given parameters.
   * The method uses a specific set of algebraic formulas to calculate the coefficients.
   *
   * @param b The base value for the calculation.
   * @param m The multiplier for the calculation.
   * @param w The weight for the calculation.
   * @return An array of double values representing the calculated coefficients.
  */
  private double[] calculateLevelCoefficients(int b, int m, int w) {
    double orgA = b * b * (m - w) - b * (m * m - w * w) + w * m * m - m * w * w;
    double aA = -b * (128 - 255) + 128 * w - 255 * m;
    double bA = b * b * (128 - 255) + 255 * m * m - 128 * w * w;
    double cA = b * b * (255 * m - 128 * w) - b * (255 * m * m - 128 * w * w);

    double a = aA / orgA;
    double bb = bA / orgA;
    double c = cA / orgA;

    return new double[]{a, bb, c};
  }

  /**
   * Compresses this image using the Haar Wavelet Transform and returns the compressed image.
   *
   * @param percentage The percentage by which to compress the image.
   * @return A new compressed Image.
   */
  public Image compress(double percentage) {
    int width = this.getWidth();
    int height = this.getHeight();

    double[][] redChannel = new double[height][width];
    double[][] greenChannel = new double[height][width];
    double[][] blueChannel = new double[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = this.getPixel(x, y);
        redChannel[y][x] = pixel.getRed();
        greenChannel[y][x] = pixel.getGreen();
        blueChannel[y][x] = pixel.getBlue();
      }
    }
    redChannel = transpose(redChannel);
    greenChannel = transpose(greenChannel);
    blueChannel = transpose(blueChannel);

    HaarWaveletTransform haarWaveletTransform = new HaarWaveletTransform();
    redChannel = haarWaveletTransform.haar(redChannel);
    greenChannel = haarWaveletTransform.haar(greenChannel);
    blueChannel = haarWaveletTransform.haar(blueChannel);

    double threshold = haarWaveletTransform.calThreshold(redChannel,
            greenChannel, blueChannel, percentage);

    redChannel = truncate(redChannel, threshold);
    greenChannel = truncate(greenChannel, threshold);
    blueChannel = truncate(blueChannel, threshold);

    redChannel = haarWaveletTransform.invHaar(redChannel, width, height);
    greenChannel = haarWaveletTransform.invHaar(greenChannel, width, height);
    blueChannel = haarWaveletTransform.invHaar(blueChannel, width, height);

    redChannel = transpose(redChannel);
    greenChannel = transpose(greenChannel);
    blueChannel = transpose(blueChannel);

    Pixel[][] compressedPixels = new Pixel[height][width];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = (int) Math.round(Math.max(0, Math.min(255, redChannel[y][x])));
        int green = (int) Math.round(Math.max(0, Math.min(255, greenChannel[y][x])));
        int blue = (int) Math.round(Math.max(0, Math.min(255, blueChannel[y][x])));
        compressedPixels[y][x] = new Pixel(red, green, blue);
      }
    }
    return new Image(compressedPixels);
  }

  /**
   * Truncates values in a 2D array that are below a specified threshold.
   * This is used to apply the calculated threshold and zero out small coefficients.
   *
   * @param channel   The 2D array of doubles to be truncated.
   * @param threshold The threshold below which values will be set to zero.
   * @return The truncated 2D array.
   */
  private double[][] truncate(double[][] channel, double threshold) {
    int width = channel.length;
    int height = channel[0].length;
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (Math.abs(channel[i][j]) < threshold) {
          channel[i][j] = 0.0;
        }
      }
    }
    return channel;
  }

  /**
   * Transposes a given 2D matrix.
   *
   * @param matrix The 2D matrix to be transposed.
   * @return The transposed matrix.
   */
  private double[][] transpose(double[][] matrix) {
    int originalHeight = matrix.length;
    int originalWidth = matrix[0].length;
    double[][] transposedMatrix = new double[originalWidth][originalHeight];

    for (int i = 0; i < originalHeight; i++) {
      for (int j = 0; j < originalWidth; j++) {
        transposedMatrix[j][i] = matrix[i][j];
      }
    }
    return transposedMatrix;
  }

  /**
   * Applies a specified filter strategy to this image.
   *
   * @param filterStrategy the filter strategy to apply to the image
   * @return the image after the filter strategy has been applied
   */
  public Image applyFilter(FilterStrategy filterStrategy) {
    return filterStrategy.apply(this);
  }

}
