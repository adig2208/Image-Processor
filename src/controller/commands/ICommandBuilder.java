package controller.commands;

/**
 * The ICommandBuilder interface defines the contract for a command builder in an application.
 * Implementations of this interface are responsible for creating CommandPair objects based on
 * specific action commands, facilitating the execution of various actions in the application.
 */
public interface ICommandBuilder {

  /**
   * Creates a CommandPair based on the specified action command. The method is responsible for
   * interpreting the action command and returning a corresponding CommandPair object.
   *
   * @param actionCommand An action command for which a CommandPair needs to be created.
   * @return a CommandPair object containing the appropriate preview and apply commands
   *         for the specified action, or null if the action cannot be processed.
   */
  CommandPair createCommand(String actionCommand);
}
