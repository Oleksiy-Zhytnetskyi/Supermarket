package zlagoda.zlagoda.controller;

import zlagoda.zlagoda.controller.command.Command;

public class CommandFactory {

    static Command getCommand(String commandKey) {
        return CommandEnum.getCommand(commandKey);
    }
}
