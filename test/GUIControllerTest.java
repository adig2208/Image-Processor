import org.junit.Before;
import org.junit.Test;

import controller.GUIController;
import model.MockModel;
import view.IGUIView;
import view.MockView;

import static org.junit.Assert.assertEquals;

/**
 * JUnit class to test GUIController.
 */
public class GUIControllerTest {
  private GUIController controller;
  private MockModel model;

  /**
   * Sets up the test environment by creating instances of MockModel, MockView, and GUIController.
   */
  @Before
  public void setup() {
    model = new MockModel();
    IGUIView view = new MockView();
    controller = new GUIController(model, view);
    MockView.clearLog();
  }

  /**
   * Tests the loadImage() method of GUIController.
   * Verifies that the expected log messages are generated when loadImage() is called.
   */
  @Test
  public void testLoadImage() {
    controller.loadImage();
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n";
    assertEquals(expectedResult, actualResult);
  }

  /**
   * Tests the saveImage() method of GUIController when no image is loaded.
   * Verifies that the expected log message is generated when saveImage() is called without loading
   * an image.
   */
  @Test
  public void testSaveImageWithoutLoad() {
    controller.saveImage();
    String actualResult = MockView.getLog();
    String expectedResult = "Show error method invoked.\n";
    assertEquals(expectedResult, actualResult);
  }

  /**
   * Tests the saveImage() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when saveImage() is called after loading
   * an image.
   */
  @Test
  public void testSaveImageWithLoad() {
    controller.loadImage();
    controller.saveImage();
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n" +
            "Show error method invoked.\n" +
            "Save image window prompt method invoked.\n" +
            "Show error method invoked.\n";
    assertEquals(expectedResult, actualResult);
  }

  /**
   * Tests the redComponent() method of GUIController without loading an image.
   * Verifies that the expected log message is generated when redComponent() is called without
   * loading an image.
   */
  @Test
  public void testRedComponentWithoutLoad() {
    controller.redComponent();
    String actualResult = MockView.getLog();
    String expectedResult = "Show error method invoked.\n";
    assertEquals(expectedResult, actualResult);
  }

  /**
   * Tests the redComponent() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when redComponent() is called after
   * loading an image.
   */
  @Test
  public void testRedComponentWithLoad() {
    controller.loadImage();
    controller.redComponent();
    String modelResult = model.getLog();
    String expectedModelResult = "Red Component method called.Get Image method called.";
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";

    assertEquals(expectedResult, actualResult);
    assertEquals(expectedModelResult, modelResult);
  }

  /**
   * Tests the greenComponent() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when greenComponent() is called after
   * loading an image.
   */
  @Test
  public void testGreenComponent() {
    controller.loadImage();
    controller.greenComponent();
    String modelResult = model.getLog();
    String expectedModelResult = "Green Component method called.Get Image method called.";
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";
    assertEquals(expectedResult, actualResult);
    assertEquals(expectedModelResult, modelResult);
  }

  /**
   * Tests the blueComponent() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when blueComponent() is called after
   * loading an image.
   */
  @Test
  public void testBlueComponent() {
    controller.loadImage();
    controller.blueComponent();
    String modelResult = model.getLog();
    String expectedModelResult = "Blue Component method called.Get Image method called.";
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";
    assertEquals(expectedResult, actualResult);
    assertEquals(expectedModelResult, modelResult);
  }

  /**
   * Tests the verticalFlip() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when verticalFlip() is called after
   * loading an image.
   */
  @Test
  public void testVerticalFlip() {
    controller.loadImage();
    controller.verticalFlip();
    String modelResult = model.getLog();
    String expectedModelResult = "Vertical Flip method called.Get Image method called.";
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";
    assertEquals(expectedResult, actualResult);
    assertEquals(expectedModelResult, modelResult);
  }

  /**
   * Tests the horizontalFlip() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when horizontalFlip() is called after
   * loading an image.
   */
  @Test
  public void testHorizontalFlip() {
    controller.loadImage();
    controller.horizontalFlip();
    String modelResult = model.getLog();
    String expectedModelResult = "Horizontal Flip method called.Get Image method called.";
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";
    assertEquals(expectedResult, actualResult);
    assertEquals(expectedModelResult, modelResult);
  }

  /**
   * Tests the blur() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when blur() is called after loading an
   * image.
   */
  @Test
  public void testBlur() {
    controller.loadImage();
    controller.blur();
    String modelResult = model.getLog();
    String expectedModelResult = "Blur method called.Get Image method called.";
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Prompt for split percentage method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";
    assertEquals(expectedResult, actualResult);
    assertEquals(expectedModelResult, modelResult);
  }

  /**
   * Tests the sharpen() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when sharpen() is called after loading
   * an image.
   */
  @Test
  public void testSharpen() {
    controller.loadImage();
    controller.sharpen();
    String modelResult = model.getLog();
    String expectedModelResult = "Sharpen method called.Get Image method called.";
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Prompt for split percentage method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";
    assertEquals(expectedResult, actualResult);
    assertEquals(expectedModelResult, modelResult);
  }

  /**
   * Tests the luma() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when luma() is called after loading
   * an image.
   */
  @Test
  public void testLuma() {
    controller.loadImage();
    controller.luma();
    String modelResult = model.getLog();
    String expectedModelResult = "Luma Component method called.Get Image method called.";
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Prompt for split percentage method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";
    assertEquals(expectedResult, actualResult);
    assertEquals(expectedModelResult, modelResult);
  }

  /**
   * Tests the sepia() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when sepia() is called after loading
   * an image.
   */
  @Test
  public void testSepia() {
    controller.loadImage();
    controller.sepia();
    String modelResult = model.getLog();
    String expectedModelResult = "Sepia Component method called.Get Image method called.";
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Prompt for split percentage method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";
    assertEquals(expectedResult, actualResult);
    assertEquals(expectedModelResult, modelResult);
  }

  /**
   * Tests the compress() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when compress() is called after loading
   * an image.
   */
  @Test
  public void testCompress() {
    controller.loadImage();
    controller.compress();
    String modelResult = model.getLog();
    String expectedModelResult = "Compress method called.Get Image method called.";
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Prompt for compression ratio method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";
    assertEquals(expectedResult, actualResult);
    assertEquals(expectedModelResult, modelResult);
  }

  /**
   * Tests the colorCorrect() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when colorCorrect() is called after
   * loading an image.
   */
  @Test
  public void testColorCorrect() {
    controller.loadImage();
    controller.colorCorrect();
    String modelResult = model.getLog();
    String expectedModelResult = "Color Correct method called.Get Image method called.";
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Prompt for split percentage method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";
    assertEquals(expectedResult, actualResult);
    assertEquals(expectedModelResult, modelResult);
  }

  /**
   * Tests the adjustLevels() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when adjustLevels() is called after
   * loading an image.
   */
  @Test
  public void testAdjustLevels() {
    controller.loadImage();
    controller.adjustLevels();
    String modelResult = model.getLog();
    String expectedModelResult = "Adjust Levels method called.Get Image method called.";
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Prompt for levels method invoked.\n"
            + "Prompt for split percentage method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";
    assertEquals(expectedResult, actualResult);
    assertEquals(expectedModelResult, modelResult);
  }

  /**
   * Tests the confirm() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when confirm() is called after
   * loading an image.
   */
  @Test
  public void testConfirm() {
    controller.loadImage();
    controller.confirm();
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Show error method invoked.\n";
    assertEquals(expectedResult, actualResult);
  }

  /**
   * Tests the cancel() method of GUIController after loading an image.
   * Verifies that the expected log messages are generated when cancel() is called after
   * loading an image.
   */
  @Test
  public void testCancel() {
    controller.loadImage();
    controller.cancel();
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n";
    assertEquals(expectedResult, actualResult);
  }

  /**
   * Tests the doubleLoadBeforeSave() method of GUIController.
   * Verifies that the expected log messages are generated when loading an image, applying an
   * operation, and then loading another image without saving.
   */
  @Test
  public void testDoubleLoadBeforeSave() {
    controller.loadImage();
    controller.sepia();
    controller.loadImage();
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Prompt for split percentage method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n"
            + "Confirm/discard changes method invoked.\n";
    assertEquals(expectedResult, actualResult);
  }

  /**
   * Tests the doubleLoadAfterSave() method of GUIController.
   * Verifies that the expected log messages are generated when loading an image, applying an
   * operation, saving the image, and then loading another image without confirming the changes.
   */
  @Test
  public void testDoubleLoadAfterSave() {
    controller.loadImage();
    controller.sepia();
    controller.saveImage();
    controller.loadImage();
    String actualResult = MockView.getLog();
    String expectedResult = "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Prompt for split percentage method invoked.\n"
            + "Set image method invoked.\n"
            + "Show operations method invoked.\n"
            + "Save image window prompt method invoked.\n"
            + "Show error method invoked.\n"
            + "Load image window prompt method invoked.\n"
            + "Show error method invoked.\n";
    assertEquals(expectedResult, actualResult);
  }
}
