package controller.commands;

/**
 * The CommandPair class represents a pair of commands used in image manipulation.
 * It holds a preview command for immediate effect visualization and an apply command
 * for applying the changes permanently.
 */
public class CommandPair {
  private final ICommand previewCommand;
  private final ICommand applyCommand;

  /**
   * Constructs a CommandPair with a preview command and an apply command.
   *
   * @param previewCommand the command to preview changes
   * @param applyCommand   the command to apply changes
   */
  public CommandPair(ICommand previewCommand, ICommand applyCommand) {
    this.previewCommand = previewCommand;
    this.applyCommand = applyCommand;
  }

  /**
   * Returns the preview command.
   *
   * @return the preview command, or null if no preview command is set
   */
  public ICommand getPreviewCommand() {
    return previewCommand;
  }

  /**
   * Returns the apply command.
   *
   * @return the apply command, or null if no apply command is set
   */
  public ICommand getApplyCommand() {
    return applyCommand;
  }

  /**
   * Checks if a preview command is available.
   *
   * @return true if a preview command is set, false otherwise
   */
  public boolean hasPreview() {
    return previewCommand != null;
  }

  /**
   * Checks if an apply command is available.
   *
   * @return true if an apply command is set, false otherwise
   */
  public boolean hasApply() {
    return applyCommand != null;
  }
}
