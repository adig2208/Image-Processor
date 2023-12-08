package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;

import controller.commands.CommandBuilder;
import controller.commands.CommandPair;
import controller.commands.Features;
import controller.commands.HistogramCommand;
import controller.commands.ICommand;
import model.IImageModel;
import model.image.Image;
import model.image.Pixel;
import view.IGUIView;

/**
 * The GUIController class is responsible for handling user interactions in the GUI
 * and executing commands for image processing. It connects the model and the view,
 * and implements the features defined in the Features interface.
 */
public class GUIController implements IController, Features {
  private final IImageModel model;
  private final CommandBuilder commandBuilder;
  private final IGUIView view;
  private String previewImageName;
  private String currentImageName;
  private CommandPair currentCommandPair;

  /**
   * Constructs a GUIController with the specified image model and GUI view.
   *
   * @param model the image model
   * @param view  the GUI view
   */
  public GUIController(IImageModel model, IGUIView view) {
    this.model = model;
    this.view = view;
    commandBuilder = new CommandBuilder(model, view);
  }

  /**
   * Converts an Image object from the model to a BufferedImage for display in the GUI.
   *
   * @param image The Image object to convert.
   * @return A BufferedImage representation of the given Image.
   */
  private static BufferedImage convertImageToBufferedImage(Image image) {
    Pixel[][] pixels = image.getPixels();
    int height = pixels.length;
    int width = pixels[0].length;

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = pixels[y][x];
        int rgb = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue()).getRGB();
        bufferedImage.setRGB(x, y, rgb);
      }
    }
    return bufferedImage;
  }

  /**
   * Initializes the controller and sets up the application for use.
   * It defines the image names and adds the controller as a listener for GUI events.
   */
  @Override
  public void execute() {
    previewImageName = "previewImage";
    currentImageName = "image";
    view.addFeatures(this);
  }

  /**
   * Executes the command corresponding to the given action command. It manages the creation
   * of command pairs and their execution, including updating the view preview or final results.
   *
   * @param actionCommand The action command that determines the operation to be executed.
   */
  private void executeCommand(String actionCommand) {
    try {
      currentCommandPair = commandBuilder.createCommand(actionCommand);
      if (currentCommandPair != null && currentCommandPair.hasPreview()) {
        boolean previewSuccess = currentCommandPair.getPreviewCommand().execute();
        if (previewSuccess) {
          updateViewWithImage(previewImageName);
          view.showOperationControls(true);
        }
      } else if (currentCommandPair != null && currentCommandPair.hasApply()) {
        currentCommandPair.getApplyCommand().execute();
        updateViewWithImage(currentImageName);
        setHistogram();
      }
    } catch (Exception ex) {
      view.showError("Error: " + ex.getMessage());
    }
  }

  /**
   * Applies the operation represented by the current command pair. It executes the apply command
   * and updates the view with the final image state, including setting the histogram.
   */
  private void applyOperation() {
    try {
      if (currentCommandPair != null && currentCommandPair.hasApply()) {
        currentCommandPair.getApplyCommand().execute();
        updateViewWithImage(currentImageName);
        setHistogram();
        view.showOperationControls(false);
      }
    } catch (Exception ex) {
      view.showError("Error: " + ex.getMessage());
    }
  }

  /**
   * Cancels the current operation, reverting any previews and restoring the original image state.
   */
  private void cancelOperation() {
    try {
      updateViewWithImage(currentImageName);
      view.showOperationControls(false);
    } catch (Exception ex) {
      view.showError("Error: " + ex.getMessage());
    }
  }

  /**
   * Updates the view with the specified image. It converts the image model to a BufferedImage
   * for display in the GUI.
   *
   * @param imageName The name of the image to display.
   * @throws IOException If there is an error in fetching or displaying the image.
   */
  private void updateViewWithImage(String imageName) throws IOException {
    BufferedImage image = convertImageToBufferedImage(model.getImage(imageName));
    view.setImage(image);
  }

  /**
   * Sets the histogram in the view based on the current image.
   *
   * @throws Exception If there is an error in generating or displaying the histogram.
   */
  private void setHistogram() throws Exception {
    ICommand newCommand = new HistogramCommand(currentImageName, "imageHist", model);
    newCommand.execute();
    view.setHistogram(convertImageToBufferedImage(model.getImage("imageHist")));
  }

  /**
   * Loads an image into the application.
   */
  @Override
  public void loadImage() {
    executeCommand("load");
  }

  /**
   * Saves the current image in the application.
   */
  @Override
  public void saveImage() {
    executeCommand("save");
  }

  /**
   * Applies a red color component filter to the image.
   */
  @Override
  public void redComponent() {
    executeCommand("red");
  }

  /**
   * Applies a green color component filter to the image.
   */
  @Override
  public void greenComponent() {
    executeCommand("green");
  }

  /**
   * Applies a blue color component filter to the image.
   */
  @Override
  public void blueComponent() {
    executeCommand("blue");
  }

  /**
   * Applies a vertical flip to the image.
   */
  @Override
  public void verticalFlip() {
    executeCommand("verticalFlip");
  }

  /**
   * Applies a horizontal flip to the image.
   */
  @Override
  public void horizontalFlip() {
    executeCommand("horizontalFlip");
  }

  /**
   * Applies a blur effect to the image.
   */
  @Override
  public void blur() {
    executeCommand("blur");
  }

  /**
   * Applies a sharpening effect to the image.
   */
  @Override
  public void sharpen() {
    executeCommand("sharpen");
  }

  /**
   * Applies a luma effect to the image.
   */
  @Override
  public void luma() {
    executeCommand("luma");
  }

  /**
   * Applies a sepia effect to the image.
   */
  @Override
  public void sepia() {
    executeCommand("sepia");
  }

  /**
   * Applies compression to the image.
   */
  @Override
  public void compress() {
    executeCommand("compress");
  }

  /**
   * Applies a color correct effect to the image.
   */
  @Override
  public void colorCorrect() {
    executeCommand("colorCorrect");
  }

  /**
   * Adjusts the levels of the image.
   */
  @Override
  public void adjustLevels() {
    executeCommand("adjustLevels");
  }

  /**
   * Confirms the current operation or selection.
   */
  @Override
  public void confirm() {
    applyOperation();
  }

  /**
   * Cancels the current operation or selection.
   */
  @Override
  public void cancel() {
    cancelOperation();
  }
}
