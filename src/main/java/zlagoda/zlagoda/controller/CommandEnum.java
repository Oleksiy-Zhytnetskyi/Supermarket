package zlagoda.zlagoda.controller;

import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.command.PageNotFoundCommand;
import zlagoda.zlagoda.controller.command.authorization.GetLoginCommand;
import zlagoda.zlagoda.controller.command.authorization.LogOutCommand;
import zlagoda.zlagoda.controller.command.authorization.PostLoginCommand;
import zlagoda.zlagoda.controller.command.user.GetUserByEmailCommand;
import zlagoda.zlagoda.service.UserService;

public enum CommandEnum {

    PAGE_NOT_FOUND {
        {
            this.key = "GET:pageNotFound";
            this.command = new PageNotFoundCommand();
        }
    },
    LOGOUT {
        {
            this.key = "GET:logout";
            this.command = new LogOutCommand();
        }
    },
    POST_LOGIN {
        {
            this.key = "POST:login";
            this.command = new PostLoginCommand(UserService.getInstance());
        }
    },
    GET_LOGIN {
        {
            this.key = "GET:login";
            this.command = new GetLoginCommand();
        }
    },
    USER_BY_EMAIL {
        {
            this.key = "GET:userByEmail";
            this.command = new GetUserByEmailCommand(UserService.getInstance());
        }
    };


    String key;
    Command command;

    public Command getCommand() {
        return command;
    }

    public String getKey() {
        return key;
    }

    public static Command getCommand(String key) {
        for (final CommandEnum command : CommandEnum.values()) {
            if (command.getKey().equals(key)) {
                return command.getCommand();
            }
        }
        return PAGE_NOT_FOUND.getCommand();
    }
}
