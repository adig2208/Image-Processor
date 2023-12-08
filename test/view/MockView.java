package view;

import java.awt.image.BufferedImage;
import java.util.Optional;

import controller.commands.Features;

/**
 * The MockView class is a mock implementation of the IGUIView interface.
 * It is used for testing purposes, logging the invocation of various methods
 * to a StringBuilder. This allows for the verification of interactions without
 * the need for a graphical user interface.
 */
public class MockView implements IGUIView {
  private static final StringBuilder log = new StringBuilder();

  /**
   * Logs the invocation of adding features to the view.
   *
   * @param features The features to be added.
   */
  @Override
  public void addFeatures(Features features) {
    log.append("Add features method invoked.\n");
  }

  /**
   * Logs the invocation of showing or hiding operation controls.
   *
   * @param show A boolean indicating whether to show or hide operation controls.
   */
  @Override
  public void showOperationControls(boolean show) {
    log.append("Show operations method invoked.\n");
  }

  /**
   * Logs the invocation of prompting for a split percentage and returns an empty Optional.
   *
   * @return An empty Optional Double.
   * @throws Exception if an error occurs during the prompt.
   */
  @Override
  public Optional<Double> promptForSplitPercentage() throws Exception {
    log.append("Prompt for split percentage method invoked.\n");
    return Optional.empty();
  }

  /**
   * Logs the invocation of the image loading prompt and returns an empty string.
   *
   * @return An empty string.
   */
  @Override
  public String loadImage() {
    log.append("Load image window prompt method invoked.\n");
    return "";
  }

  /**
   * Logs the invocation of the image saving prompt and returns an empty string.
   *
   * @return An empty string.
   */
  @Override
  public String saveImage() {
    log.append("Save image window prompt method invoked.\n");
    return "";
  }

  /**
   * Logs the invocation of prompting for image levels and returns an array of zeros.
   *
   * @return An int array with zero values.
   * @throws Exception if an error occurs during the prompt.
   */
  @Override
  public int[] promptForLevels() throws Exception {
    log.append("Prompt for levels method invoked.\n");
    return new int[]{0, 0, 0};
  }

  /**
   * Logs the invocation of prompting for a compression ratio and returns zero.
   *
   * @return A Double value of zero.
   * @throws Exception if an error occurs during the prompt.
   */
  @Override
  public Double promptForCompressionRatio() throws Exception {
    log.append("Prompt for compression ratio method invoked.\n");
    return (double) 0;
  }

  /**
   * Logs the invocation of confirming or discarding changes and returns false.
   *
   * @return false.
   */
  @Override
  public boolean confirmDiscardChanges() {
    log.append("Confirm/discard changes method invoked.\n");
    return false;
  }

  /**
   * Logs the invocation of setting an image in the view.
   *
   * @param image The BufferedImage to set.
   */
  @Override
  public void setImage(BufferedImage image) {
    log.append("Set image method invoked.\n");
  }

  /**
   * Logs the invocation of setting a histogram in the view.
   *
   * @param histogram The BufferedImage representing the histogram.
   */
  @Override
  public void setHistogram(BufferedImage histogram) {
    log.append("Set histogram method invoked.\n");
  }

  /**
   * Logs the invocation of showing a message in the view.
   *
   * @param message The message to be shown.
   */
  @Override
  public void showMessage(String message) {
    log.append("Show message method invoked.\n");
  }

  /**
   * Logs the invocation of getting input from the view and returns null.
   *
   * @return null.
   */
  @Override
  public String getInput() {
    log.append("Get input method invoked.\n");
    return null;
  }

  /**
   * Logs the invocation of showing an error message.
   *
   * @param errorMessage The error message to be shown.
   */
  @Override
  public void showError(String errorMessage) {
    log.append("Show error method invoked.\n");
  }

  /**
   * Retrieves the log of all method invocations.
   *
   * @return A string representing the log of invocations.
   */
  public static String getLog() {
    return log.toString();
  }

  /**
   * Clears the log of method invocations.
   */
  public static void clearLog() {
    log.setLength(0);
  }
}
