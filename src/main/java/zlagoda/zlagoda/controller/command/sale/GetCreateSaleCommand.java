package zlagoda.zlagoda.controller.command.sale;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.ProductEntity;
import zlagoda.zlagoda.entity.ReceiptEntity;
import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.service.ProductService;
import zlagoda.zlagoda.service.ReceiptService;
import zlagoda.zlagoda.service.SaleService;
import zlagoda.zlagoda.service.StoreProductService;

import java.io.IOException;
import java.util.List;

public class GetCreateSaleCommand implements Command {

    private final SaleService saleService;
    private final StoreProductService storeProductService;
    private final ProductService productService;
    private final ReceiptService receiptService;

    public GetCreateSaleCommand(SaleService saleService, StoreProductService storeProductService, ProductService productService, ReceiptService receiptService) {
        this.saleService = saleService;
        this.storeProductService = storeProductService;
        this.productService = productService;
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StoreProductEntity> storeProducts = storeProductService.getAllStoreProducts();
        List<ProductEntity> products = productService.getAllProducts();
        List<ReceiptEntity> receipts = receiptService.getAllReceipts();
        req.setAttribute(Attribute.STORE_PRODUCTS, storeProducts);
        req.setAttribute(Attribute.PRODUCTS, products);
        req.setAttribute(Attribute.RECEIPTS, receipts);
        return Page.CREATE_SALE;
    }
}

