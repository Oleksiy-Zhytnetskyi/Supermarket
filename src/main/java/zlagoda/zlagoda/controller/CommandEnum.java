package zlagoda.zlagoda.controller;

import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.command.PageNotFoundCommand;
import zlagoda.zlagoda.controller.command.authorization.GetLoginCommand;
import zlagoda.zlagoda.controller.command.authorization.LogOutCommand;
import zlagoda.zlagoda.controller.command.authorization.PostLoginCommand;
import zlagoda.zlagoda.controller.command.category.*;
import zlagoda.zlagoda.controller.command.i18n.ChangeLocaleCommand;
import zlagoda.zlagoda.controller.command.user.*;
import zlagoda.zlagoda.service.CategoryService;
import zlagoda.zlagoda.service.UserService;

public enum CommandEnum {

    CHANGE_LOCALE {
        {
            this.key = "GET:locale";
            this.command = new ChangeLocaleCommand();
        }
    },
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
    GET_MY_PROFILE{
        {
            this.key = "GET:myProfile";
            this.command = new GetMyProfileCommand(UserService.getInstance());
        }
    },
    GET_VIEW_USER {
        {
            this.key = "GET:viewUser";
            this.command = new GetViewUserCommand(UserService.getInstance());
        }
    },
    ALL_USERS {
        {
            this.key = "GET:allUsers";
            this.command = new GetAllUsersCommand(UserService.getInstance());
        }
    },
    GET_UPDATE_USER {
        {
            this.key = "GET:updateUser";
            this.command = new GetUpdateUserCommand(UserService.getInstance());
        }
    },
    GET_CREATE_USER {
        {
            this.key = "GET:createUser";
            this.command = new GetCreateUserCommand(UserService.getInstance());
        }
    },
    POST_UPDATE_USER {
        {
            this.key = "POST:updateUser";
            this.command = new PostUpdateUserCommand(UserService.getInstance());
        }
    },
    POST_CREATE_USER {
        {
            this.key = "POST:createUser";
            this.command = new PostCreateUserCommand(UserService.getInstance());
        }
    },
    DELETE_USER {
        {
            this.key = "GET:deleteUser";
            this.command = new DeleteUserCommand(UserService.getInstance());
        }
    },
    SORT_USERS {
        {
            this.key = "GET:sortUsers";
            this.command = new GetSortUserCommand(UserService.getInstance());
        }
    },
    ALL_CATEGORIES {
        {
            this.key = "GET:allCategories";
            this.command = new GetAllCategoryCommand(CategoryService.getInstance());
        }
    },
    GET_UPATE_CATEGORY {
        {
            this.key = "GET:updateCategory";
            this.command = new GetUpdateCategoryCommand(CategoryService.getInstance());
        }
    },
    GET_CREATE_CATEGORY {
        {
            this.key = "GET:createCategory";
            this.command = new GetCreateCategoryCommand(CategoryService.getInstance());
        }
    },
    POST_UPDATE_CATEGORY {
        {
            this.key = "POST:updateCategory";
            this.command = new PostUpdateCategoryCommand(CategoryService.getInstance());
        }
    },
    POST_CREATE_CATEGORY {
        {
            this.key = "POST:createCategory";
            this.command = new PostCreateCategoryCommand(CategoryService.getInstance());
        }
    },
    DELETE_CATEGORY {
        {
            this.key = "GET:deleteCategory";
            this.command = new DeleteCategoryCommand(CategoryService.getInstance());
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
