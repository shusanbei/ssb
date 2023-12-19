package edu.hue.jk.controller.admin;

import edu.hue.jk.mapper.ProductMapper;
import edu.hue.jk.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    @Value("${bookstore.upload-locations}")
    private String uploadDir;
    @Value("${bookstore.url-locations}")
    private String urlDir;

    @Autowired
    private ProductMapper productMapper;

    @RequestMapping("/list")
    public String list(Model model, String id, String category, String name, Double lowPrice, Double highPrice) {
        List<Product> productList = productMapper.findAll(id, category, name, lowPrice, highPrice);
        model.addAttribute("productList", productList);

        return "admin/products/list";
    }

    @RequestMapping("edit")
    public String edit(Model model, String id) {
        Product product = productMapper.findById(id);
        model.addAttribute("product", product);

        return "admin/products/edit";
    }

    @RequestMapping("add")
    public String add(Model model, String id) {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        model.addAttribute("product", product);

        return "admin/products/add";
    }

    @RequestMapping("del")
    public String del(Model model, String id) {
        productMapper.delete(id);

        List<Product> productList = productMapper.findAll(null, null, null, null, null);
        model.addAttribute("productList", productList);

        return "admin/products/list";
    }

    @RequestMapping("save")
    public String save(Model model, Product product, @RequestParam(value = "productImg") MultipartFile file) {
        boolean isNew = productMapper.findById(product.getId()) == null;
        if (isNew) {
            product.setId(UUID.randomUUID().toString());
            productMapper.insert(product);
        } else {
            productMapper.update(product);
        }

        String newFileName = product.getId() + ".jpeg";

        try {
            file.transferTo(new File(uploadDir + "/" + newFileName));
            product.setImgurl(urlDir + "/" + newFileName);
            productMapper.update(product);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Product> productList = productMapper.findAll(null, null, null, null, null);
        model.addAttribute("productList", productList);

        return "admin/products/list";
    }
}
