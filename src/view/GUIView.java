package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.commands.Features;

/**
 * The `GUIView` class represents the graphical user interface for an image processing application.
 * It extends the `JFrame` class and implements the `IGUIView` interface.
 * The GUI provides image display, histogram display, and various image processing operations.
 * Users can load, save, adjust image components, apply filters, and more through this interface.
 */
public class GUIView extends JFrame implements IGUIView {
  private JLabel imageDisplay;
  private JLabel histogramDisplay;
  private JButton loadButton;
  private JButton redButton;
  private JButton greenButton;
  private JButton blueButton;
  private JButton verticalFlipButton;
  private JButton horizontalFlipButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton lumaButton;
  private JButton sepiaButton;
  private JButton compressButton;
  private JButton colorCorrectButton;
  private JButton adjustLevelsButton;
  private JButton saveButton;
  private JButton confirmButton;
  private JButton cancelButton;

  public GUIView() {
    initializeUI();
  }

  /**
   * Initializes the GUI view by setting up the user interface components.
   */
  private void initializeUI() {
    setTitle("Image Processor");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    JPanel displayPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    JPanel imagePanel = new JPanel(new BorderLayout());
    JLabel imageHeading = new JLabel("Image Display");
    imageHeading.setHorizontalAlignment(JLabel.CENTER);
    imageDisplay = new JLabel();
    imageDisplay.setHorizontalAlignment(JLabel.CENTER);
    imageDisplay.setPreferredSize(new Dimension(700, 500));
    JScrollPane imageScrollPane = new JScrollPane(imageDisplay);
    imagePanel.add(imageHeading, BorderLayout.NORTH);
    imagePanel.add(imageScrollPane, BorderLayout.CENTER);

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 0.75;
    c.weighty = 1.0;
    c.fill = GridBagConstraints.BOTH;
    displayPanel.add(imagePanel, c);

    JPanel histogramPanel = new JPanel(new BorderLayout());
    JLabel histogramHeading = new JLabel("Histogram");
    histogramHeading.setHorizontalAlignment(JLabel.CENTER);
    histogramDisplay = new JLabel();
    histogramDisplay.setHorizontalAlignment(JLabel.CENTER);
    histogramDisplay.setPreferredSize(new Dimension(256, 256));
    JScrollPane histogramScrollPane = new JScrollPane(histogramDisplay);
    histogramPanel.add(histogramHeading, BorderLayout.NORTH);
    histogramPanel.add(histogramScrollPane, BorderLayout.CENTER);

    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 0.25;
    c.weighty = 1.0;
    c.fill = GridBagConstraints.BOTH;
    displayPanel.add(histogramPanel, c);

    displayPanel.setPreferredSize(new Dimension(956, 500));
    add(displayPanel, BorderLayout.CENTER);

    createButtons();

    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /**
   * Creates and configures buttons for various image processing operations.
   */
  private void createButtons() {
    JPanel buttonPanel = new JPanel(new FlowLayout());
    JPanel confirmCancelPanel = new JPanel(new FlowLayout());

    loadButton = createButton("Load Image", "load", buttonPanel);
    redButton = createButton("Red Component", "red", buttonPanel);
    greenButton = createButton("Green Component", "green", buttonPanel);
    blueButton = createButton("Blue Component", "blue", buttonPanel);
    verticalFlipButton = createButton("Vertical Flip", "verticalFlip",
            buttonPanel);
    horizontalFlipButton = createButton("Horizontal Flip", "horizontalFlip",
            buttonPanel);
    blurButton = createButton("Blur", "blur", buttonPanel);
    sharpenButton = createButton("Sharpen", "sharpen", buttonPanel);
    lumaButton = createButton("Luma", "luma", buttonPanel);
    sepiaButton = createButton("Sepia", "sepia", buttonPanel);
    compressButton = createButton("Compress", "compress", buttonPanel);
    colorCorrectButton = createButton("Color Correct", "colorCorrect",
            buttonPanel);
    adjustLevelsButton = createButton("Adjust Levels", "adjustLevels",
            buttonPanel);
    saveButton = createButton("Save", "save", buttonPanel);

    confirmButton = createButton("Confirm", "confirm", confirmCancelPanel);
    confirmButton.setVisible(false);

    cancelButton = createButton("Cancel", "cancel", confirmCancelPanel);
    cancelButton.setVisible(false);

    JPanel mainButtonPanel = new JPanel(new BorderLayout());
    mainButtonPanel.add(buttonPanel, BorderLayout.NORTH);
    mainButtonPanel.add(confirmCancelPanel, BorderLayout.SOUTH);

    add(mainButtonPanel, BorderLayout.SOUTH);
  }

  /**
   * Creates a JButton with the specified text and action command, and adds it to a JPanel.
   *
   * @param text          The text to be displayed on the button.
   * @param actionCommand The action command associated with the button.
   * @param panel         The JPanel to which the button is added.
   * @return The created JButton.
   */
  private JButton createButton(String text, String actionCommand, JPanel panel) {
    JButton button = new JButton(text);
    button.setActionCommand(actionCommand);
    panel.add(button);
    return button;
  }

  /**
   * Adds event listeners to the GUI buttons to attach image processing features.
   *
   * @param features The `Features` object providing access to image processing functionality.
   */
  @Override
  public void addFeatures(Features features) {
    loadButton.addActionListener(evt -> features.loadImage());
    saveButton.addActionListener(evt -> features.saveImage());
    redButton.addActionListener(evt -> features.redComponent());
    greenButton.addActionListener(evt -> features.greenComponent());
    blueButton.addActionListener(evt -> features.blueComponent());
    verticalFlipButton.addActionListener(evt -> features.verticalFlip());
    horizontalFlipButton.addActionListener(evt -> features.horizontalFlip());
    blurButton.addActionListener(evt -> features.blur());
    sharpenButton.addActionListener(evt -> features.sharpen());
    lumaButton.addActionListener(evt -> features.luma());
    sepiaButton.addActionListener(evt -> features.sepia());
    compressButton.addActionListener(evt -> features.compress());
    colorCorrectButton.addActionListener(evt -> features.colorCorrect());
    adjustLevelsButton.addActionListener(evt -> features.adjustLevels());
    confirmButton.addActionListener(evt -> features.confirm());
    cancelButton.addActionListener(evt -> features.cancel());
  }

  /**
   * Shows or hides the operation control buttons.
   *
   * @param show `true` to show the buttons, `false` to hide them.
   */
  @Override
  public void showOperationControls(boolean show) {
    confirmButton.setVisible(show);
    cancelButton.setVisible(show);
    loadButton.setEnabled(!show);
    redButton.setEnabled(!show);
    greenButton.setEnabled(!show);
    blueButton.setEnabled(!show);
    verticalFlipButton.setEnabled(!show);
    horizontalFlipButton.setEnabled(!show);
    blurButton.setEnabled(!show);
    sharpenButton.setEnabled(!show);
    lumaButton.setEnabled(!show);
    sepiaButton.setEnabled(!show);
    compressButton.setEnabled(!show);
    colorCorrectButton.setEnabled(!show);
    adjustLevelsButton.setEnabled(!show);
    saveButton.setEnabled(!show);
  }

  /**
   * Prompts the user to enter a split percentage for previewing an image effect.
   *
   * @return An `Optional` containing the split percentage if entered, or empty if not entered.
   * @throws Exception if the input is invalid or the user cancels the prompt.
   */

  @Override
  public Optional<Double> promptForSplitPercentage() throws Exception {
    JTextField textField = new JTextField();
    Object[] message = { "Enter split percentage for preview " + "\n"
                    + "(Keep blank for effect to be applied to entire image):", textField };

    int option = JOptionPane.showConfirmDialog(this, message, "Input",
            JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
      String result = textField.getText();
      if (result.trim().isEmpty()) {
        return Optional.empty();
      }
      try {
        return Optional.of(Double.parseDouble(result.trim()));
      } catch (NumberFormatException e) {
        throw new Exception("Invalid input. Please enter a numeric value.");
      }
    } else {
      return null;
    }
  }

  /**
   * Configures the file chooser dialog with the specified title and file filters.
   *
   * @param fileChooser The `JFileChooser` to be configured.
   * @param dialogTitle The title of the file chooser dialog.
   */
  private void configureFileChooser(JFileChooser fileChooser, String dialogTitle) {
    fileChooser.setDialogTitle(dialogTitle);

    FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("JPG files (*.jpg)",
            "jpg");
    FileNameExtensionFilter jpegFilter = new FileNameExtensionFilter("JPEG files (*.jpeg)",
            "jpeg");
    FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG files (*.png)",
            "png");
    FileNameExtensionFilter ppmFilter = new FileNameExtensionFilter("PPM files (*.ppm)",
            "ppm");

    fileChooser.addChoosableFileFilter(jpgFilter);
    fileChooser.addChoosableFileFilter(jpegFilter);
    fileChooser.addChoosableFileFilter(pngFilter);
    fileChooser.addChoosableFileFilter(ppmFilter);

    fileChooser.setFileFilter(pngFilter);
    fileChooser.setAcceptAllFileFilterUsed(false);
  }

  /**
   * Opens a file dialog for loading an image and returns the selected file's absolute path.
   *
   * @return The absolute path of the selected image file or `null` if canceled.
   */
  @Override
  public String loadImage() {
    JFileChooser fileChooser = new JFileChooser();
    configureFileChooser(fileChooser, "Open Image File");

    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      return file.getAbsolutePath();
    }
    return null;
  }

  /**
   * Opens a file dialog for saving an image and returns the selected file's absolute path.
   *
   * @return The absolute path of the file to save or `null` if canceled.
   */
  @Override
  public String saveImage() {
    JFileChooser fileChooser = new JFileChooser();
    configureFileChooser(fileChooser, "Save Image File");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      String filePath = fileToSave.getAbsolutePath();
      FileNameExtensionFilter selectedFilter = (FileNameExtensionFilter)
              fileChooser.getFileFilter();
      String selectedExtension = selectedFilter.getExtensions()[0];

      if (!(filePath.toLowerCase().endsWith(".jpg") || filePath.toLowerCase().endsWith(".jpeg") ||
              filePath.toLowerCase().endsWith(".png") || filePath.toLowerCase().endsWith(".ppm"))) {
        filePath += "." + selectedExtension;
      }
      return filePath;
    }
    return null;
  }

  /**
   * Prompts the user to enter bright, mid, and white values for image processing.
   *
   * @return An array of three integers containing bright, mid, and white values.
   * @throws Exception if the input is invalid or the user cancels the prompt.
   */
  @Override
  public int[] promptForLevels() throws Exception {
    JTextField brightField = new JTextField(5);
    JTextField midField = new JTextField(5);
    JTextField whiteField = new JTextField(5);

    JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("Bright:"));
    myPanel.add(brightField);
    myPanel.add(Box.createHorizontalStrut(15));
    myPanel.add(new JLabel("Mid:"));
    myPanel.add(midField);
    myPanel.add(Box.createHorizontalStrut(15));
    myPanel.add(new JLabel("White:"));
    myPanel.add(whiteField);

    int result = JOptionPane.showConfirmDialog(this, myPanel,
            "Enter Bright, Mid, and White Values:", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      try {
        int bright = Integer.parseInt(brightField.getText());
        int mid = Integer.parseInt(midField.getText());
        int white = Integer.parseInt(whiteField.getText());
        return new int[]{bright, mid, white};
      } catch (NumberFormatException e) {
        throw new Exception("Invalid input for b, m, w. Please enter numeric values.");
      }
    }
    return null;
  }

  /**
   * Prompts the user to enter a compression percentage.
   *
   * @return The compression percentage as a `Double`.
   * @throws Exception if the input is invalid or the user cancels the prompt.
   */
  @Override
  public Double promptForCompressionRatio() throws Exception {
    JTextField textField = new JTextField();
    Object[] message = { "Enter compression percentage:", textField };

    int option = JOptionPane.showConfirmDialog(this, message, "Input",
            JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
      String result = textField.getText();
      try {
        return Double.parseDouble(result.trim());
      } catch (NumberFormatException e) {
        throw new Exception("Invalid input. Please enter a numeric value.");
      }
    } else {
      return null;
    }
  }

  /**
   * Asks the user to confirm discarding unsaved changes when loading a new image.
   *
   * @return `true` if the user confirms, `false` otherwise.
   */
  @Override
  public boolean confirmDiscardChanges() {
    int choice = JOptionPane.showConfirmDialog(this,
            "You have unsaved changes. Do you want to continue loading a new image?",
            "Unsaved Changes", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    return choice == JOptionPane.YES_OPTION;
  }

  /**
   * Sets the displayed image in the GUI.
   *
   * @param image The `BufferedImage` to be displayed.
   */
  @Override
  public void setImage(BufferedImage image) {
    imageDisplay.setIcon(new ImageIcon(image));
  }

  /**
   * Sets the displayed histogram in the GUI.
   *
   * @param histogram The `BufferedImage` representing the histogram to be displayed.
   */
  @Override
  public void setHistogram(BufferedImage histogram) {
    histogramDisplay.setIcon(new ImageIcon(histogram));
  }

  /**
   * Displays a message in a message dialog.
   *
   * @param message The message to be displayed.
   */
  @Override
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message,
            "Message", JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Gets input from the user (placeholder method, not implemented).
   *
   * @return `null` (this method is not implemented).
   */
  @Override
  public String getInput() {
    return null;
  }

  /**
   * Displays an error message in an error dialog.
   *
   * @param errorMessage The error message to be displayed.
   */
  @Override
  public void showError(String errorMessage) {
    JOptionPane.showMessageDialog(this, errorMessage,
            "Error", JOptionPane.ERROR_MESSAGE);
  }
}