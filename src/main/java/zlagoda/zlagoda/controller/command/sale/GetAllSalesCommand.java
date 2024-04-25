package zlagoda.zlagoda.controller.command.sale;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.*;
import zlagoda.zlagoda.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetAllSalesCommand implements Command {

    private final SaleService saleService;
    private final ReceiptService receiptService;
    private final StoreProductService storeProductService;
    private final ProductService productService;

    public GetAllSalesCommand(SaleService saleService, ReceiptService receiptService, StoreProductService storeProductService, ProductService productService) {
        this.saleService = saleService;
        this.receiptService = receiptService;
        this.storeProductService = storeProductService;
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SaleEntity> sales = saleService.getAllSales();
        List<ReceiptEntity> receipts = receiptService.getAllReceipts();
        List<StoreProductEntity> storeProducts = storeProductService.getAllStoreProducts();
        List<ProductEntity> products = productService.getAllProducts();
        req.setAttribute(Attribute.SALES, sales);
        req.setAttribute(Attribute.RECEIPTS, receipts);
        req.setAttribute(Attribute.STORE_PRODUCTS, storeProducts);
        req.setAttribute(Attribute.PRODUCTS, products);
        return Page.ALL_SALES;
    }
}
