package zlagoda.zlagoda.controller;

import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.command.PageNotFoundCommand;
import zlagoda.zlagoda.controller.command.authorization.GetLoginCommand;
import zlagoda.zlagoda.controller.command.authorization.LogOutCommand;
import zlagoda.zlagoda.controller.command.authorization.PostLoginCommand;
import zlagoda.zlagoda.controller.command.category.*;
import zlagoda.zlagoda.controller.command.customer.card.*;
import zlagoda.zlagoda.controller.command.i18n.ChangeLocaleCommand;
import zlagoda.zlagoda.controller.command.product.*;
import zlagoda.zlagoda.controller.command.receipt.DeleteReceiptCommand;
import zlagoda.zlagoda.controller.command.receipt.GetAllReceiptsCommand;
import zlagoda.zlagoda.controller.command.receipt.GetCreateReceiptCommand;
import zlagoda.zlagoda.controller.command.receipt.PostCreateReceiptCommand;
import zlagoda.zlagoda.controller.command.sale.GetAllSalesCommand;
import zlagoda.zlagoda.controller.command.store.product.*;
import zlagoda.zlagoda.controller.command.user.*;
import zlagoda.zlagoda.service.*;

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
    },
    GET_ALL_PRODUCTS {
        {
            this.key = "GET:allProducts";
            this.command = new GetAllProductsCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    GET_VIEW_PRODUCT {
        {
            this.key = "GET:viewProduct";
            this.command = new GetViewProductCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    GET_UPDATE_PRODUCT {
        {
            this.key = "GET:updateProduct";
            this.command = new GetUpdateProductCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    GET_CREATE_PRODUCT {
        {
            this.key = "GET:createProduct";
            this.command = new GetCreateProductCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    POST_UPDATE_PRODUCT {
        {
            this.key = "POST:updateProduct";
            this.command = new PostUpdateProductCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    POST_CREATE_PRODUCT {
        {
            this.key = "POST:createProduct";
            this.command = new PostCreateProductCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    DELETE_PRODUCT {
        {
            this.key = "GET:deleteProduct";
            this.command = new DeleteProductCommand(ProductService.getInstance());
        }
    },
    GET_SORT_PRODUCTS {
        {
            this.key = "GET:sortProducts";
            this.command = new GetSortProductCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    GET_ALL_CUSTOMER_CARDS {
        {
            this.key = "GET:allCustomerCards";
            this.command = new GetAllCustomerCardCommand(CardService.getInstance());
        }
    },
    GET_CUSTOMER_CARD_VIEW {
        {
            this.key = "GET:viewCustomerCard";
            this.command = new GetViewCustomerCardCommand(CardService.getInstance());
        }
    },
    GET_UPDATE_CUSTOMER_CARD {
        {
            this.key = "GET:updateCustomerCard";
            this.command = new GetUpdateCustomerCardCommand(CardService.getInstance());
        }
    },
    GET_CREATE_CUSTOMER_CARD {
        {
            this.key = "GET:createCustomerCard";
            this.command = new GetCreateCustomerCardCommand(CardService.getInstance());
        }
    },
    POST_UPDATE_CUSTOMER_CARD {
        {
            this.key = "POST:updateCustomerCard";
            this.command = new PostUpdateCustomerCardCommand(CardService.getInstance());
        }
    },
    POST_CREATE_CUSTOMER_CARD {
        {
            this.key = "POST:createCustomerCard";
            this.command = new PostCreateCustomerCardCommand(CardService.getInstance());
        }
    },
    DELETE_CUSTOMER_CARD {
        {
            this.key = "GET:deleteCustomerCard";
            this.command = new DeleteCustomerCardCommand(CardService.getInstance());
        }
    },
    GET_SORT_CUSTOMER_CARDS {
        {
            this.key = "GET:sortCustomerCards";
            this.command = new GetSortCustomerCardCommand(CardService.getInstance());
        }
    },
    GET_ALL_STORE_PRODUCTS {
        {
            this.key = "GET:allStoreProducts";
            this.command = new GetAllStoreProductCommand(ProductService.getInstance(), StoreProductService.getInstance());
        }
    },
    GET_STORE_PRODUCT_VIEW {
        {
            this.key = "GET:viewStoreProduct";
            this.command = new GetViewStoreProductCommand(StoreProductService.getInstance(), ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    GET_UPDATE_STORE_PRODUCT {
        {
            this.key = "GET:updateStoreProduct";
            this.command = new GetUpdateStoreProductCommand(StoreProductService.getInstance(), ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    GET_CREATE_STORE_PRODUCT {
        {
            this.key = "GET:createStoreProduct";
            this.command = new GetCreateStoreProductCommand(StoreProductService.getInstance(), ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    POST_UPDATE_STORE_PRODUCT {
        {
            this.key = "POST:updateStoreProduct";
            this.command = new PostUpdateStoreProductCommand(StoreProductService.getInstance());
        }
    },
    POST_CREATE_STORE_PRODUCT {
        {
            this.key = "POST:createStoreProduct";
            this.command = new PostCreateStoreProductCommand(StoreProductService.getInstance());
        }
    },
    DELETE_STORE_PRODUCT {
        {
            this.key = "GET:deleteStoreProduct";
            this.command = new DeleteStoreProductCommand(StoreProductService.getInstance());
        }
    },
    GET_SORT_STORE_PRODUCTS {
        {
            this.key = "GET:sortStoreProducts";
            this.command = new GetSortStoreProductsCommand(StoreProductService.getInstance(), ProductService.getInstance());
        }
    },
    GET_ALL_RECEIPTS {
        {
            this.key = "GET:allReceipts";
            this.command = new GetAllReceiptsCommand(ReceiptService.getInstance(), UserService.getInstance(), CardService.getInstance());
        }
    },
    GET_CREATE_RECEIPT {
        {
            this.key = "GET:createReceipt";
            this.command = new GetCreateReceiptCommand(ReceiptService.getInstance(), CardService.getInstance());
        }
    },
    POST_CREATE_RECEIPT {
        {
            this.key = "POST:createReceipt";
            this.command = new PostCreateReceiptCommand(ReceiptService.getInstance(), CardService.getInstance());
        }
    },
    DELETE_RECEIPT {
        {
            this.key = "GET:deleteReceipt";
            this.command = new DeleteReceiptCommand(ReceiptService.getInstance());
        }
    },
    GET_ALL_SALES {
        {
            this.key = "GET:allSales";
            this.command = new GetAllSalesCommand(SaleService.getInstance(), ReceiptService.getInstance(), StoreProductService.getInstance(), ProductService.getInstance());
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
