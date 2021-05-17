package com.eemall.demo.service.impl;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.eemall.demo.model.product.ProductCategory;
import com.eemall.demo.repository.CategoryRepository;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


public class CategoryServiceImpTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @InjectMocks
    CategoryServiceImp categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<ProductCategory> valuesInDB = new ArrayList<>();
        when(categoryRepository.findAll()).thenReturn(valuesInDB);

        List<ProductCategory> list = categoryService.findAll();
        assert(list == valuesInDB);
    }

    @Test
    public void testFindByCategoryType() throws Exception {
        ProductCategory valueInDB = new ProductCategory();
        when(categoryRepository.findByCategoryType(1)).thenReturn(valueInDB);

        ProductCategory pc = categoryService.findByCategoryType(1);
        assert(pc == valueInDB);

    }


    @Test
    public void testFindByCategoryTypeException() throws Exception {
        when(categoryRepository.findByCategoryType(0)).thenReturn(null);

        thrown.expect(Exception.class);
        thrown.expectMessage("CATEGORY_NOT_FOUND");
        categoryService.findByCategoryType(0);
    }


    @Test
    public void testFindByCategoryIn() {
        List<Integer> categoryTypeList = new ArrayList<>();
        categoryTypeList.add(1);


        List<ProductCategory> valuesInDB = new ArrayList<>();
        when(categoryRepository.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList)).thenReturn(valuesInDB);

        List<ProductCategory> list = categoryService.findByCategoryIn(categoryTypeList);
        assert(list == valuesInDB);
    }

    @Test
    public void testSave() {
        ProductCategory value = new ProductCategory();
        List<ProductCategory> valuesInDB = new ArrayList<>();
        Answer answer = new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ProductCategory pc = invocation.getArgument(0);
                valuesInDB.add(pc);
                return valuesInDB.get(0);
            }

        };
        when(categoryRepository.save(value)).thenAnswer(answer);

        ProductCategory pc = categoryService.save(value);
        assert(pc == valuesInDB.get(0));
        assert(valuesInDB.size() == 1);
    }

}


