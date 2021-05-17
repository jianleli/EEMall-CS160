package com.eemall.demo.service.impl;


import static org.mockito.Matchers.any;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.eemall.demo.model.product.Product;
import com.eemall.demo.model.product.ProductStatus;
import com.eemall.demo.repository.ProductRepository;
import com.eemall.demo.service.CategoryService;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

class MyPageable implements Pageable {

    @Override
    public int getPageNumber() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getPageSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getOffset() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Sort getSort() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pageable next() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pageable first() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasPrevious() {
        // TODO Auto-generated method stub
        return false;
    }

}

class MyPage<T> implements Page<T> {

    @Override
    public int getNumber() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNumberOfElements() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List getContent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasContent() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Sort getSort() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isFirst() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isLast() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasPrevious() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Pageable nextPageable() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pageable previousPageable() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getTotalPages() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getTotalElements() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Page map(Function converter) {
        // TODO Auto-generated method stub
        return null;
    }

}


public class ProductServiceImpTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @InjectMocks
    ProductServiceImp productService;

    @Mock
    ProductRepository productRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testFindOne() {
        Long productId = 1L;
        Product productInDB = new Product();
        when(productRepository.findByProductId(productId)).thenReturn(productInDB);

        Product p = productService.findOne(productId);
        assert(p == productInDB);
    }

    @Test
    public void testFindAllInStock() {

        MyPage<Product> productsInDB = new MyPage<>();
        when(productRepository.findAllByProductStatus(any(Integer.class), any(MyPageable.class))).thenReturn(productsInDB);

        Page<Product> ps = productService.findAllInStock(new MyPageable());
        assert(ps == productsInDB);
    }

    @Test
    public void testFindAll() {
        MyPage<Product> productsInDB = new MyPage<>();
        when(productRepository.findAllByOrderByProductId(any(MyPageable.class))).thenReturn(productsInDB);

        Page<Product> ps = productService.findAll(new MyPageable());
        assert(ps == productsInDB);

    }

    @Test
    public void testFindAllInCategory() {
        MyPage<Product> productsInDB = new MyPage<>();
        when(productRepository.findAllByCategory(any(Integer.class), any(MyPageable.class))).thenReturn(productsInDB);

        Page<Product> ps = productService.findAllInCategory(0, new MyPageable());
        assert(ps == productsInDB);
    }

    @Test
    public void testFindAllWithKeyword() {
        MyPage<Product> productsInDB = new MyPage<>();
        when(productRepository.findByProductNameContainingIgnoreCase(any(String.class), any(MyPageable.class))).thenReturn(productsInDB);

        Page<Product> ps = productService.findAllWithKeyword("", new MyPageable());
        assert(ps == productsInDB);
    }

    @Test
    public void testUpdate() throws Exception {
        List<Product> productsInDB = new ArrayList<>();
        Product productInDB = new Product();
        productsInDB.add(productInDB);
        Product product = new Product();

        product.setProductStatus(0);
        when(productRepository.save(product)).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product p = invocation.getArgument(0);
                productsInDB.set(0, p);
                return productsInDB.get(0);
            }

        });

        Product p = productService.update(product);
        assert(p == productsInDB.get(0));
    }

    @Test
    public void testUpdateException() throws Exception {
        List<Product> productsInDB = new ArrayList<>();
        Product productInDB = new Product();
        productsInDB.add(productInDB);

        Product product = new Product();
        product.setProductStatus(999);
        when(productRepository.save(product)).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product p = invocation.getArgument(0);
                productsInDB.set(0, p);
                return productsInDB.get(0);
            }

        });

        thrown.expect(Exception.class);
        thrown.expectMessage("Status_error");
        Product p = productService.update(product);
    }


    @Test
    public void testSave() throws Exception{
        List<Product> productsInDB = new ArrayList<>();
        Product product = new Product();
        product.setProductStatus(0);
        when(productRepository.save(product)).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product p = invocation.getArgument(0);
                productsInDB.add(p);
                return productsInDB.get(0);
            }

        });

        Product p = productService.save(product);
        assert(p == productsInDB.get(0));
    }


    @Test
    public void testSaveException() throws Exception {
        List<Product> productsInDB = new ArrayList<>();
        Product product = new Product();
        product.setProductStatus(999);
        when(productRepository.save(product)).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product p = invocation.getArgument(0);
                productsInDB.add(p);
                return productsInDB.get(0);
            }

        });

        thrown.expect(Exception.class);
        thrown.expectMessage("Status_error");
        Product p = productService.save(product);
    }




    @Test
    public void testDelete() throws Exception {
        Long productId = 1L;
        List<Product> productsInDB = new ArrayList<>();
        Product productInDB = new Product();
        productsInDB.add(productInDB);

        when(productRepository.findByProductId(1L)).thenReturn(productsInDB.get(0));
        when(productRepository.findByProductId(0L)).thenReturn(null);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product p = invocation.getArgument(0);
                productsInDB.remove(0);
                return null;
            }
        }).when(productRepository).delete(any(Product.class));

        productService.delete(productId);
        assert(productsInDB.size() == 0);

    }

    @Test
    public void testDeleteException() throws Exception {
        Long productId = 1L;
        List<Product> productsInDB = new ArrayList<>();
        Product productInDB = new Product();
        productsInDB.add(productInDB);

        when(productRepository.findByProductId(1L)).thenReturn(productsInDB.get(0));
        when(productRepository.findByProductId(0L)).thenReturn(null);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product p = invocation.getArgument(0);
                productsInDB.remove(0);
                return null;
            }
        }).when(productRepository).delete(any(Product.class));


        thrown.expect(Exception.class);
        thrown.expectMessage("Product not exist");
        productService.delete(0L);

    }



    @Test
    public void testSoldOut() throws Exception {
        Long productId = 1L;
        List<Product> productsInDB = new ArrayList<>();
        Product productInDB = new Product();
        productsInDB.add(productInDB);

        when(productRepository.findByProductId(1L)).thenReturn(productsInDB.get(0));
        when(productRepository.findByProductId(0L)).thenReturn(null);
        when(productRepository.save(any(Product.class))).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product p = invocation.getArgument(0);
                productsInDB.set(0, p);
                return productsInDB.get(0);
            }

        });

        Product p = productService.SoldOut(productId);
        assert(p == productsInDB.get(0));
        assert(p.getProductStatus() == ProductStatus.OUT_OF_STOCK.getCode());
    }

    @Test
    public void testSoldOutException() throws Exception {
        Long productId = 1L;
        List<Product> productsInDB = new ArrayList<>();
        Product productInDB = new Product();
        productsInDB.add(productInDB);

        when(productRepository.findByProductId(1L)).thenReturn(productsInDB.get(0));
        when(productRepository.findByProductId(0L)).thenReturn(null);
        when(productRepository.save(any(Product.class))).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product p = invocation.getArgument(0);
                productsInDB.set(0, p);
                return productsInDB.get(0);
            }

        });

        thrown.expect(Exception.class);
        thrown.expectMessage("product not exist");
        Product p = productService.SoldOut(0L);
    }

    @Test
    public void testSoldOutException2() throws Exception {
        Long productId = 1L;
        List<Product> productsInDB = new ArrayList<>();
        Product productInDB = new Product();
        productInDB.setProductStatus(ProductStatus.OUT_OF_STOCK.getCode());
        productsInDB.add(productInDB);

        when(productRepository.findByProductId(1L)).thenReturn(productsInDB.get(0));
        when(productRepository.findByProductId(0L)).thenReturn(null);
        when(productRepository.save(any(Product.class))).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product p = invocation.getArgument(0);
                productsInDB.set(0, p);
                return productsInDB.get(0);
            }

        });

        thrown.expect(Exception.class);
        thrown.expectMessage("product not exist");
        Product p = productService.SoldOut(1L);
    }

    @Test
    public void testOnSale() throws Exception {
        Long productId = 1L;
        List<Product> productsInDB = new ArrayList<>();
        Product productInDB = new Product();
        productsInDB.add(productInDB);

        when(productRepository.findByProductId(1L)).thenReturn(productsInDB.get(0));
        when(productRepository.findByProductId(0L)).thenReturn(null);
        when(productRepository.save(any(Product.class))).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product p = invocation.getArgument(0);
                productsInDB.set(0, p);
                return productsInDB.get(0);
            }

        });

        Product p = productService.onSale(productId);
        assert(p == productsInDB.get(0));
        assert(p.getProductStatus() == ProductStatus.IN_STOCK.getCode());
    }

    @Test
    public void testOnSaleException() throws Exception {
        Long productId = 1L;
        List<Product> productsInDB = new ArrayList<>();
        Product productInDB = new Product();
        productsInDB.add(productInDB);

        when(productRepository.findByProductId(1L)).thenReturn(productsInDB.get(0));
        when(productRepository.findByProductId(0L)).thenReturn(null);
        when(productRepository.save(any(Product.class))).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product p = invocation.getArgument(0);
                productsInDB.set(0, p);
                return productsInDB.get(0);
            }

        });

        thrown.expect(Exception.class);
        thrown.expectMessage("product not exist");
        Product p = productService.onSale(0L);
    }

    @Test
    public void testOnSaleException2() throws Exception {
        Long productId = 1L;
        List<Product> productsInDB = new ArrayList<>();
        Product productInDB = new Product();
        productInDB.setProductStatus(ProductStatus.IN_STOCK.getCode());
        productsInDB.add(productInDB);

        when(productRepository.findByProductId(1L)).thenReturn(productsInDB.get(0));
        when(productRepository.findByProductId(0L)).thenReturn(null);
        when(productRepository.save(any(Product.class))).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product p = invocation.getArgument(0);
                productsInDB.set(0, p);
                return productsInDB.get(0);
            }

        });

        thrown.expect(Exception.class);
        thrown.expectMessage("product not exist");
        Product p = productService.onSale(1L);
    }

}

