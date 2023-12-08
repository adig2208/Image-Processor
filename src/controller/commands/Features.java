package controller.commands;

/**
 * The Features interface represents a collection of methods that correspond to
 * different actions or features in an image processing application.
 * Implementations of this interface are expected to define the behavior for each of these actions.
 */
public interface Features {

  /**
   * Loads an image into the application.
   */
  void loadImage();

  /**
   * Saves the current image in the application.
   */
  void saveImage();

  /**
   * Applies a red color component filter to the image.
   */
  void redComponent();

  /**
   * Applies a green color component filter to the image.
   */
  void greenComponent();

  /**
   * Applies a blue color component filter to the image.
   */
  void blueComponent();

  /**
   * Applies a vertical flip to the image.
   */
  void verticalFlip();

  /**
   * Applies a horizontal flip to the image.
   */
  void horizontalFlip();

  /**
   * Applies a blur effect to the image.
   */
  void blur();

  /**
   * Applies a sharpening effect to the image.
   */
  void sharpen();

  /**
   * Applies a luma effect to the image.
   */
  void luma();

  /**
   * Applies a sepia effect to the image.
   */
  void sepia();

  /**
   * Compresses the image.
   */
  void compress();

  /**
   * Corrects the color of the image.
   */
  void colorCorrect();

  /**
   * Adjusts the levels of the image.
   */
  void adjustLevels();

  /**
   * Confirms the current operation or selection.
   */
  void confirm();

  /**
   * Cancels the current operation or selection.
   */
  void cancel();
}
