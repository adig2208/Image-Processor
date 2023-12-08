package controller.commands;

import java.util.Optional;

import model.IImageModel;
import view.IGUIView;

/**
 * The CommandBuilder class is responsible for creating and handling different
 * types of commands for image manipulation.
 * It maintains the state of the application and interacts with the model and view components.
 */
public class CommandBuilder implements ICommandBuilder {
  private final IImageModel model;
  private final IGUIView view;
  private AppState currentState = AppState.NO_IMAGE_LOADED;

  /**
   * Constructs a CommandBuilder with the specified model and view.
   *
   * @param model the image model for image processing
   * @param view  the GUI view for user interaction
   */
  public CommandBuilder(IImageModel model, IGUIView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Creates and returns a CommandPair based on the specified action command.
   * This method delegates to specific handlers based on the type of command.
   *
   * @param actionCommand the command string indicating the action to be performed
   * @return a CommandPair containing the preview and apply commands, or null
   */
  @Override
  public CommandPair createCommand(String actionCommand) {
    try {
      switch (actionCommand) {
        case "load":
          return handleLoad();
        case "red":
          return handleColorCommand(new RedComponentCommand("image",
                          "previewImage", model),
                  new RedComponentCommand("image", "image", model));
        case "green":
          return handleColorCommand(new GreenComponentCommand("image",
                          "previewImage", model),
                  new GreenComponentCommand("image", "image", model));
        case "blue":
          return handleColorCommand(new BlueComponentCommand("image",
                          "previewImage", model),
                  new BlueComponentCommand("image", "image", model));
        case "verticalFlip":
          return handleFlipCommand(new VerticalFlipCommand("image",
                          "previewImage", model),
                  new VerticalFlipCommand("image", "image", model));
        case "horizontalFlip":
          return handleFlipCommand(new HorizontalFlipCommand("image",
                          "previewImage", model),
                  new HorizontalFlipCommand("image", "image", model));
        case "blur":
        case "sharpen":
        case "luma":
        case "sepia":
        case "colorCorrect":
          return handleFilterCommand(actionCommand);
        case "compress":
          return handleCompression();
        case "adjustLevels":
          return handleAdjustLevels();
        case "save":
          return handleSave();
        default:
          throw new IllegalArgumentException("Unknown command " + actionCommand);
      }
    } catch (Exception e) {
      view.showError(e.getMessage());
      return null;
    }
  }

  /**
   * Handles the logic for loading an image. It checks if the current image has been modified
   * and prompts the user to confirm discarding changes.
   *
   * @return a CommandPair containing null as the preview command and the load command
   *         as the apply command, or null if loading is cancelled
   */
  private CommandPair handleLoad() {
    if (currentState == AppState.IMAGE_LOADED_MODIFIED && !view.confirmDiscardChanges()) {
      return null;
    }
    String loadPath = view.loadImage();
    if (loadPath != null) {
      currentState = AppState.IMAGE_LOADED_UNMODIFIED;
      ICommand loadCommand = new LoadCommand(loadPath, "image", model);
      return new CommandPair(null, loadCommand);
    }
    return null;
  }

  /**
   * Creates and returns a CommandPair for color manipulation commands. It updates the application
   * state to indicate that the image has been modified.
   *
   * @param previewCommand the command to apply to the preview image
   * @param applyCommand   the command to apply to the actual image
   * @return a CommandPair containing the specified preview and apply commands, or null
   */
  private CommandPair handleColorCommand(ICommand previewCommand, ICommand applyCommand) {
    if (checkImageLoaded()) {
      currentState = AppState.IMAGE_LOADED_MODIFIED;
      return new CommandPair(previewCommand, applyCommand);
    }
    return null;
  }

  /**
   * Creates and returns a CommandPair for flip commands (vertical or horizontal).
   * It updates the application state to indicate that the image has been modified.
   *
   * @param previewCommand the command to apply to the preview image
   * @param applyCommand   the command to apply to the actual image
   * @return a CommandPair containing the specified preview and apply commands, or null
   */
  private CommandPair handleFlipCommand(ICommand previewCommand, ICommand applyCommand) {
    if (checkImageLoaded()) {
      currentState = AppState.IMAGE_LOADED_MODIFIED;
      return new CommandPair(previewCommand, applyCommand);
    }
    return null;
  }

  /**
   * Handles filter commands such as blur, sharpen, luma, sepia, and color correction.
   * It prompts the user for the percentage of the filter to apply and creates
   * the corresponding commands.
   *
   * @param commandType the type of filter command to handle
   * @return a CommandPair containing the preview and apply filter commands, or null
   * @throws Exception if an invalid command type is provided
   */
  private CommandPair handleFilterCommand(String commandType) throws Exception {
    if (checkImageLoaded()) {
      Optional<Double> percentage = view.promptForSplitPercentage();
      if (percentage != null) {
        currentState = AppState.IMAGE_LOADED_MODIFIED;
        ICommand previewCommand = null;
        ICommand applyCommand = null;

        switch (commandType) {
          case "blur":
            previewCommand = new BlurCommand("image", "previewImage",
                    model, percentage);
            applyCommand = new BlurCommand("image", "image",
                    model, Optional.of(100.0));
            break;
          case "sharpen":
            previewCommand = new SharpenCommand("image", "previewImage",
                    model, percentage);
            applyCommand = new SharpenCommand("image", "image",
                    model, Optional.of(100.0));
            break;
          case "luma":
            previewCommand = new LumaComponentCommand("image",
                    "previewImage", model, percentage);
            applyCommand = new LumaComponentCommand("image", "image",
                    model, Optional.of(100.0));
            break;
          case "sepia":
            previewCommand = new SepiaCommand("image", "previewImage",
                    model, percentage);
            applyCommand = new SepiaCommand("image", "image",
                    model, Optional.of(100.0));
            break;
          case "colorCorrect":
            previewCommand = new ColorCorrectCommand("image",
                    "previewImage", model, percentage);
            applyCommand = new ColorCorrectCommand("image", "image",
                    model, Optional.of(100.0));
            break;
          default:
            view.showError("Invalid filter command type.");
            return null;
        }
        return new CommandPair(previewCommand, applyCommand);
      }
    }
    return null;
  }

  /**
   * Handles the compression command. It prompts the user for the compression ratio and creates the
   * corresponding commands.
   *
   * @return a CommandPair containing the preview and apply compression commands, or null
   * @throws Exception if an error occurs during the compression process
   */
  private CommandPair handleCompression() throws Exception {
    if (checkImageLoaded()) {
      Double compressionRatio = view.promptForCompressionRatio();
      if (compressionRatio != null) {
        currentState = AppState.IMAGE_LOADED_MODIFIED;
        ICommand compressPreviewCommand = new CompressCommand(compressionRatio, "image",
                "previewImage", model);
        ICommand compressCommand = new CompressCommand(compressionRatio, "image",
                "image", model);
        return new CommandPair(compressPreviewCommand, compressCommand);
      }
    }
    return null;
  }

  /**
   * Handles the adjust levels command. It prompts the user for the levels to adjust and creates the
   * corresponding commands.
   *
   * @return a CommandPair containing the preview and apply, adjust levels commands, or null
   * @throws Exception if an error occurs during level adjustment
   */
  private CommandPair handleAdjustLevels() throws Exception {
    if (checkImageLoaded()) {
      int[] levels = view.promptForLevels();
      if (levels != null) {
        Optional<Double> percentage = view.promptForSplitPercentage();
        if (percentage != null) {
          currentState = AppState.IMAGE_LOADED_MODIFIED;
          ICommand adjustLevelsPreviewCommand = new AdjustLevelsCommand(levels[0], levels[1],
                  levels[2], "image", "previewImage", model, percentage);
          ICommand adjustLevelsCommand = new AdjustLevelsCommand(levels[0], levels[1], levels[2],
                  "image", "image", model, Optional.of(100.0));
          return new CommandPair(adjustLevelsPreviewCommand, adjustLevelsCommand);
        }
      }
    }
    return null;
  }

  /**
   * Handles the save command. It prompts the user for the save path and creates the save command.
   *
   * @return a CommandPair containing null as the preview command and the save command or null
   */
  private CommandPair handleSave() {
    if (checkImageLoaded()) {
      String savePath = view.saveImage();
      if (savePath != null) {
        currentState = AppState.IMAGE_LOADED_UNMODIFIED;
        ICommand saveCommand = new SaveCommand(savePath, "image", model);
        return new CommandPair(null, saveCommand);
      }
    }
    return null;
  }

  /**
   * Checks if an image is currently loaded. If not, it displays an error message.
   *
   * @return true if an image is loaded, false otherwise
   */
  private boolean checkImageLoaded() {
    if (currentState == AppState.NO_IMAGE_LOADED) {
      view.showError("No image loaded.");
      return false;
    }
    return true;
  }
}
