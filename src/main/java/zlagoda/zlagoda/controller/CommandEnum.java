package zlagoda.zlagoda.controller;

import lombok.Getter;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.command.PageNotFoundCommand;
import zlagoda.zlagoda.controller.command.authorization.GetLoginCommand;
import zlagoda.zlagoda.controller.command.authorization.LogOutCommand;
import zlagoda.zlagoda.controller.command.authorization.PostLoginCommand;
import zlagoda.zlagoda.controller.command.category.*;
import zlagoda.zlagoda.controller.command.customer.card.*;
import zlagoda.zlagoda.controller.command.i18n.ChangeLocaleCommand;
import zlagoda.zlagoda.controller.command.product.*;
import zlagoda.zlagoda.controller.command.receipt.*;
import zlagoda.zlagoda.controller.command.sale.DeleteSaleCommand;
import zlagoda.zlagoda.controller.command.sale.GetAllSalesCommand;
import zlagoda.zlagoda.controller.command.sale.GetCreateSaleCommand;
import zlagoda.zlagoda.controller.command.sale.PostCreateSaleCommand;
import zlagoda.zlagoda.controller.command.store.product.*;
import zlagoda.zlagoda.controller.command.user.*;
import zlagoda.zlagoda.service.*;

@Getter
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
            this.command = new PostLoginCommand();
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
            this.command = new GetMyProfileCommand();
        }
    },
    GET_VIEW_USER {
        {
            this.key = "GET:viewUser";
            this.command = new GetViewUserCommand();
        }
    },
    ALL_USERS {
        {
            this.key = "GET:allUsers";
            this.command = new GetAllUsersCommand();
        }
    },
    GET_UPDATE_USER {
        {
            this.key = "GET:updateUser";
            this.command = new GetUpdateUserCommand();
        }
    },
    GET_CREATE_USER {
        {
            this.key = "GET:createUser";
            this.command = new GetCreateUserCommand();
        }
    },
    POST_UPDATE_USER {
        {
            this.key = "POST:updateUser";
            this.command = new PostUpdateUserCommand();
        }
    },
    POST_CREATE_USER {
        {
            this.key = "POST:createUser";
            this.command = new PostCreateUserCommand();
        }
    },
    DELETE_USER {
        {
            this.key = "GET:deleteUser";
            this.command = new DeleteUserCommand();
        }
    },
    SORT_USERS {
        {
            this.key = "GET:sortUsers";
            this.command = new GetSortUserCommand();
        }
    },
    ALL_CATEGORIES {
        {
            this.key = "GET:allCategories";
            this.command = new GetAllCategoryCommand();
        }
    },
    GET_UPDATE_CATEGORY {
        {
            this.key = "GET:updateCategory";
            this.command = new GetUpdateCategoryCommand();
        }
    },
    GET_CREATE_CATEGORY {
        {
            this.key = "GET:createCategory";
            this.command = new GetCreateCategoryCommand();
        }
    },
    POST_UPDATE_CATEGORY {
        {
            this.key = "POST:updateCategory";
            this.command = new PostUpdateCategoryCommand();
        }
    },
    POST_CREATE_CATEGORY {
        {
            this.key = "POST:createCategory";
            this.command = new PostCreateCategoryCommand();
        }
    },
    DELETE_CATEGORY {
        {
            this.key = "GET:deleteCategory";
            this.command = new DeleteCategoryCommand();
        }
    },
    GET_ALL_PRODUCTS {
        {
            this.key = "GET:allProducts";
            this.command = new GetAllProductsCommand();
        }
    },
    GET_VIEW_PRODUCT {
        {
            this.key = "GET:viewProduct";
            this.command = new GetViewProductCommand();
        }
    },
    GET_UPDATE_PRODUCT {
        {
            this.key = "GET:updateProduct";
            this.command = new GetUpdateProductCommand();
        }
    },
    GET_CREATE_PRODUCT {
        {
            this.key = "GET:createProduct";
            this.command = new GetCreateProductCommand();
        }
    },
    POST_UPDATE_PRODUCT {
        {
            this.key = "POST:updateProduct";
            this.command = new PostUpdateProductCommand();
        }
    },
    POST_CREATE_PRODUCT {
        {
            this.key = "POST:createProduct";
            this.command = new PostCreateProductCommand();
        }
    },
    DELETE_PRODUCT {
        {
            this.key = "GET:deleteProduct";
            this.command = new DeleteProductCommand();
        }
    },
    GET_SORT_PRODUCTS {
        {
            this.key = "GET:sortProducts";
            this.command = new GetSortProductCommand();
        }
    },
    GET_ALL_CUSTOMER_CARDS {
        {
            this.key = "GET:allCustomerCards";
            this.command = new GetAllCustomerCardCommand();
        }
    },
    GET_CUSTOMER_CARD_VIEW {
        {
            this.key = "GET:viewCustomerCard";
            this.command = new GetViewCustomerCardCommand();
        }
    },
    GET_UPDATE_CUSTOMER_CARD {
        {
            this.key = "GET:updateCustomerCard";
            this.command = new GetUpdateCustomerCardCommand();
        }
    },
    GET_CREATE_CUSTOMER_CARD {
        {
            this.key = "GET:createCustomerCard";
            this.command = new GetCreateCustomerCardCommand();
        }
    },
    POST_UPDATE_CUSTOMER_CARD {
        {
            this.key = "POST:updateCustomerCard";
            this.command = new PostUpdateCustomerCardCommand();
        }
    },
    POST_CREATE_CUSTOMER_CARD {
        {
            this.key = "POST:createCustomerCard";
            this.command = new PostCreateCustomerCardCommand();
        }
    },
    DELETE_CUSTOMER_CARD {
        {
            this.key = "GET:deleteCustomerCard";
            this.command = new DeleteCustomerCardCommand();
        }
    },
    GET_SORT_CUSTOMER_CARDS {
        {
            this.key = "GET:sortCustomerCards";
            this.command = new GetSortCustomerCardCommand();
        }
    },
    GET_ALL_STORE_PRODUCTS {
        {
            this.key = "GET:allStoreProducts";
            this.command = new GetAllStoreProductCommand();
        }
    },
    GET_STORE_PRODUCT_VIEW {
        {
            this.key = "GET:viewStoreProduct";
            this.command = new GetViewStoreProductCommand();
        }
    },
    GET_UPDATE_STORE_PRODUCT {
        {
            this.key = "GET:updateStoreProduct";
            this.command = new GetUpdateStoreProductCommand();
        }
    },
    GET_CREATE_STORE_PRODUCT {
        {
            this.key = "GET:createStoreProduct";
            this.command = new GetCreateStoreProductCommand();
        }
    },
    POST_UPDATE_STORE_PRODUCT {
        {
            this.key = "POST:updateStoreProduct";
            this.command = new PostUpdateStoreProductCommand();
        }
    },
    POST_CREATE_STORE_PRODUCT {
        {
            this.key = "POST:createStoreProduct";
            this.command = new PostCreateStoreProductCommand();
        }
    },
    DELETE_STORE_PRODUCT {
        {
            this.key = "GET:deleteStoreProduct";
            this.command = new DeleteStoreProductCommand();
        }
    },
    GET_SORT_STORE_PRODUCTS {
        {
            this.key = "GET:sortStoreProducts";
            this.command = new GetSortStoreProductsCommand();
        }
    },
    GET_ALL_RECEIPTS {
        {
            this.key = "GET:allReceipts";
            this.command = new GetAllReceiptsCommand();
        }
    },
    GET_CREATE_RECEIPT {
        {
            this.key = "GET:createReceipt";
            this.command = new GetCreateReceiptCommand();
        }
    },
    POST_CREATE_RECEIPT {
        {
            this.key = "POST:createReceipt";
            this.command = new PostCreateReceiptCommand();
        }
    },
    DELETE_RECEIPT {
        {
            this.key = "GET:deleteReceipt";
            this.command = new DeleteReceiptCommand();
        }
    },
    GET_ALL_SALES {
        {
            this.key = "GET:allSales";
            this.command = new GetAllSalesCommand();
        }
    },
    GET_CREATE_SALE {
        {
            this.key = "GET:createSale";
            this.command = new GetCreateSaleCommand();
        }
    },
    POST_CREATE_SALE {
        {
            this.key = "POST:createSale";
            this.command = new PostCreateSaleCommand();
        }
    },
    DELETE_SALE {
        {
            this.key = "GET:deleteSale";
            this.command = new DeleteSaleCommand();
        }
    },
    VIEW_RECEIPT {
        {
            this.key = "GET:viewReceipt";
            this.command = new GetViewReceiptCommand();
        }
    };

    String key;
    Command command;

    public static Command getCommand(String key) {
        for (final CommandEnum command : CommandEnum.values()) {
            if (command.getKey().equals(key)) {
                return command.getCommand();
            }
        }
        return PAGE_NOT_FOUND.getCommand();
    }
}
