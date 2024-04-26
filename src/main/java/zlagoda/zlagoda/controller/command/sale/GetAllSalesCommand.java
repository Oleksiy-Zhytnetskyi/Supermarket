package zlagoda.zlagoda.controller.command.sale;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.ProductEntity;
import zlagoda.zlagoda.entity.ReceiptEntity;
import zlagoda.zlagoda.entity.SaleEntity;
import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.service.ProductService;
import zlagoda.zlagoda.service.ReceiptService;
import zlagoda.zlagoda.service.SaleService;
import zlagoda.zlagoda.service.StoreProductService;

import java.io.IOException;
import java.util.List;

public class GetAllSalesCommand implements Command {

    private static final SaleService saleService = SaleService.getInstance();
    private static final ReceiptService receiptService = ReceiptService.getInstance();
    private static final StoreProductService storeProductService = StoreProductService.getInstance();
    private static final ProductService productService = ProductService.getInstance();

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
