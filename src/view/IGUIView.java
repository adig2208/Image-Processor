package view;

import java.awt.image.BufferedImage;
import java.util.Optional;

import controller.commands.Features;

/**
 * The `IGUIView` interface defines the contract for a graphical user interface (GUI) view
 * in an image processing application. It provides methods for interacting with the GUI,
 * including attaching image processing features, showing/hiding operation controls, and
 * prompting the user for various inputs.
 */
public interface IGUIView extends IView {

  /**
   * Attaches image processing features to the GUI view.
   *
   * @param features The `Features` object providing access to image processing functionality.
   */
  void addFeatures(Features features);

  /**
   * Shows or hides the operation control buttons on the GUI.
   *
   * @param show `true` to show the buttons, `false` to hide them.
   */
  void showOperationControls(boolean show);

  /**
   * Prompts the user to enter a split percentage for previewing an image effect.
   *
   * @return An `Optional` containing the split percentage if entered, or empty if not entered.
   * @throws Exception if the input is invalid or the user cancels the prompt.
   */
  Optional<Double> promptForSplitPercentage() throws Exception;

  /**
   * Opens a file dialog for loading an image and returns the selected file's absolute path.
   *
   * @return The absolute path of the selected image file or `null` if canceled.
   */
  String loadImage();

  /**
   * Opens a file dialog for saving an image and returns the selected file's absolute path.
   *
   * @return The absolute path of the file to save or `null` if canceled.
   */
  String saveImage();

  /**
   * Prompts the user to enter bright, mid, and white values for image processing.
   *
   * @return An array of three integers containing bright, mid, and white values.
   * @throws Exception if the input is invalid or the user cancels the prompt.
   */
  int[] promptForLevels() throws Exception;

  /**
   * Prompts the user to enter a compression percentage.
   *
   * @return The compression percentage as a `Double`.
   * @throws Exception if the input is invalid or the user cancels the prompt.
   */
  Double promptForCompressionRatio() throws Exception;

  /**
   * Asks the user to confirm discarding unsaved changes when loading a new image.
   *
   * @return `true` if the user confirms, `false` otherwise.
   */
  boolean confirmDiscardChanges();

  /**
   * Sets the displayed image in the GUI.
   *
   * @param image The `BufferedImage` to be displayed.
   */
  void setImage(BufferedImage image);

  /**
   * Sets the displayed histogram in the GUI.
   *
   * @param histogram The `BufferedImage` representing the histogram to be displayed.
   */
  void setHistogram(BufferedImage histogram);
}