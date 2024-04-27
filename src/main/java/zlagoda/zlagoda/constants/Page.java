package zlagoda.zlagoda.constants;

public interface Page {

    String PREFIX = "/WEB-INF/view/";
    String SUFFIX = ".jsp";

    String HOME_VIEW = "/index" + SUFFIX;
    String LOGIN_VIEW = PREFIX + "login" + SUFFIX;

    String MY_PROFILE = PREFIX + "myProfile" + SUFFIX;

    String ALL_USERS = PREFIX + "allUsers" + SUFFIX;
    String VIEW_USER = PREFIX + "addUpdateUser" + SUFFIX;

    String ALL_CATEGORIES = PREFIX + "allCategories" + SUFFIX;
    String VIEW_CATEGORY = PREFIX + "addUpdateCategory" + SUFFIX;

    String ALL_PRODUCTS = PREFIX + "allProducts" + SUFFIX;
    String VIEW_PRODUCT = PREFIX + "addUpdateProduct" + SUFFIX;

    String ALL_CUSTOMER_CARDS = PREFIX + "allCustomerCards" + SUFFIX;
    String VIEW_CUSTOMER_CARD = PREFIX + "addUpdateCustomerCard" + SUFFIX;

    String ALL_STORE_PRODUCTS = PREFIX + "allStoreProducts" + SUFFIX;
    String VIEW_STORE_PRODUCT = PREFIX + "viewStoreProduct" + SUFFIX;
    String ADD_STORE_PRODUCT = PREFIX + "addUpdateStoreProduct" + SUFFIX;

    String ALL_RECEIPTS = PREFIX + "allReceipts" + SUFFIX;
    String ADD_RECEIPT = PREFIX + "createReceipt" + SUFFIX;
    String VIEW_RECEIPT = PREFIX + "viewReceipt" + SUFFIX;
    String SORTED_RECEIPTS = PREFIX + "sortedReceipts" + SUFFIX;

    String ALL_SALES = PREFIX + "allSales" + SUFFIX;
    String CREATE_SALE = PREFIX + "createSale" + SUFFIX;

    String ALL_STATISTICS = PREFIX + "statistic" + SUFFIX;

    String PAGE_NOT_FOUND = PREFIX + "pageNotFound" + SUFFIX;
}
