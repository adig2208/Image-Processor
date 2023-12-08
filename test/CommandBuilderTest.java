import org.junit.Before;
import org.junit.Test;

import controller.commands.CommandBuilder;
import controller.commands.CommandPair;
import model.IImageModel;
import model.MockModel;
import view.IGUIView;
import view.MockView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * JUnit class to test the functionality of the CommandBuilder class.
 */
public class CommandBuilderTest {
  private CommandBuilder commandBuilder;

  /**
   * Sets up the test environment by creating instances of MockModel, MockView,
   * and CommandBuilder.
   */
  @Before
  public void setUp() {
    IImageModel model = new MockModel();
    IGUIView view = new MockView();
    commandBuilder = new CommandBuilder(model, view);
  }

  /**
   * Tests the CommandBuilder for creating a LoadCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a LoadCommand.
   */
  @Test
  public void testLoadCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating a SaveCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a SaveCommand.
   */
  @Test
  public void testSaveCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("save");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("SaveCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating a RedComponentCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a
   * RedComponentCommand.
   */
  @Test
  public void testRedComponentCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("red");

    assertNotNull(commandPair);
    assertNotNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("RedComponentCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating a GreenComponentCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a
   * GreenComponentCommand.
   */
  @Test
  public void testGreenComponentCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("green");

    assertNotNull(commandPair);
    assertNotNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("GreenComponentCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating a BlueComponentCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a
   * BlueComponentCommand.
   */
  @Test
  public void testBlueComponentCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("blue");

    assertNotNull(commandPair);
    assertNotNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("BlueComponentCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating a BlurCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a BlurCommand.
   */
  @Test
  public void testBlurCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("blur");

    assertNotNull(commandPair);
    assertNotNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("BlurCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating a SharpenCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a SharpenCommand.
   */
  @Test
  public void testSharpenCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("sharpen");

    assertNotNull(commandPair);
    assertNotNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("SharpenCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating a VerticalFlipCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a
   * VerticalFlipCommand.
   */
  @Test
  public void testVerticalFlipCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("verticalFlip");

    assertNotNull(commandPair);
    assertNotNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("VerticalFlipCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating a HorizontalFlipCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a
   * HorizontalFlipCommand.
   */
  @Test
  public void testHorizontalFlipCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("horizontalFlip");

    assertNotNull(commandPair);
    assertNotNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("HorizontalFlipCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating a SepiaCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a SepiaCommand.
   */
  @Test
  public void testSepiaCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("sepia");

    assertNotNull(commandPair);
    assertNotNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("SepiaCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating a LumaComponentCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a
   * LumaComponentCommand.
   */
  @Test
  public void testLumaCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("luma");

    assertNotNull(commandPair);
    assertNotNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LumaComponentCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating a CompressCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a CompressCommand.
   */
  @Test
  public void testCompressCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("compress");

    assertNotNull(commandPair);
    assertNotNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("CompressCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating a ColorCorrectCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is a
   * ColorCorrectCommand.
   */
  @Test
  public void testColorCorrectCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("colorCorrect");

    assertNotNull(commandPair);
    assertNotNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("ColorCorrectCommand", commandPair.getApplyCommand().toString());
  }

  /**
   * Tests the CommandBuilder for creating an AdjustLevelsCommand.
   * Verifies that the CommandPair is created correctly and the ApplyCommand is an
   * AdjustLevelsCommand.
   */
  @Test
  public void testAdjustLevelsCommandBuilder() {
    CommandPair commandPair = commandBuilder.createCommand("load");

    assertNotNull(commandPair);
    assertNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("LoadCommand", commandPair.getApplyCommand().toString());

    commandPair = commandBuilder.createCommand("adjustLevels");

    assertNotNull(commandPair);
    assertNotNull(commandPair.getPreviewCommand());
    assertNotNull(commandPair.getApplyCommand());
    assertEquals("AdjustLevelsCommand", commandPair.getApplyCommand().toString());
  }
}
