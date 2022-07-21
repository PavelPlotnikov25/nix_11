package com;

import com.model.ProductComparator;
import com.model.ProductVersion;
import com.model.computer.Computer;
import com.model.television.Television;
import com.repository.ComputerRepository;
import com.repository.TelevisionRepository;
import com.service.*;

import java.awt.*;
import java.util.*;


public class Main {
    private static final ComputerRepository repository = new ComputerRepository();
    private static final TelevisionRepository repository1 = new TelevisionRepository();
    private static final ProductService<Television> TELEVISION_SERVICE = new TelevisionService(repository1);
    private static final ProductService<Computer> COMPUTER_SERVICE = new ComputerService(repository);
    private static final Random random = new Random();
    private static final DiscountService<Computer> DISCOUNT_SERVICE = new DiscountService<>();
    private static final DiscountService<Television> DISCOUNT_SERVICE1 = new DiscountService<>();
    private static final ProductVersionList LIST = new ProductVersionList();

    public static void main(String[] args) throws CloneNotSupportedException {
        TELEVISION_SERVICE.createAndSaveProduct(5);
        Television television = repository1.getAll().get(2);
        ProductVersionList productVersions = new ProductVersionList();
        productVersions.add(television);
        television.setTitle("New TITLE");
        productVersions.add(television);
        television.setPrice(9999.9);
        productVersions.add(television);
        System.out.println(productVersions);
        television.setTitle("Updated Title");
        productVersions.add(television);
        television.setCount(0);
        productVersions.add(television);
        for (ProductVersion product : productVersions) {
            System.out.println(product);
        }
        COMPUTER_SERVICE.createAndSaveProduct(15);
        ArrayList<Computer> computerList = new ArrayList();
        repository.getAll().get(1).setPrice(repository.getAll().get(2).getPrice());
        repository.getAll().get(1).setTitle(repository.getAll().get(2).getTitle());
        repository.getAll().get(10).setPrice(repository.getAll().get(2).getPrice());
        repository.getAll().get(10).setTitle(repository.getAll().get(2).getTitle());
        computerList.addAll(repository.getAll());

        computerList.sort(new ProductComparator<Computer>());
        for (Computer computers : computerList) {
            System.out.println(computers);
        }
    }
}