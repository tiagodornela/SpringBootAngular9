package br.com.mad.springboot2crudgm.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.mad.springboot2crudgm.converter.mocks.MockProduct;
import br.com.mad.springboot2crudgm.model.Product;
import br.com.mad.springboot2crudgm.model.enumeration.CategoryType;
import br.com.mad.springboot2crudgm.vo.ProductVO;

public class DozerConverterTest {
	
    MockProduct inputObject;

    @Before
    public void setUp() {
        inputObject = new MockProduct();
    }

    @Test
    public void parseEntityToVOTest() {
        ProductVO output = DozerConverter.parseObject(inputObject.mockEntity(), ProductVO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("Name Teste 0", output.getName());
        Assert.assertEquals(CategoryType.non_perishable, output.getCategory());
        Assert.assertEquals(Double.valueOf(0D), output.getPrice());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<ProductVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), ProductVO.class);
        ProductVO outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("Name Teste 0", outputZero.getName());
        Assert.assertEquals(CategoryType.non_perishable, outputZero.getCategory());
        Assert.assertEquals(Double.valueOf(0D), outputZero.getPrice());
        
        ProductVO outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("Name Teste 7", outputSeven.getName());
        Assert.assertEquals(CategoryType.perishable, outputSeven.getCategory());
        Assert.assertEquals(Double.valueOf(7D), outputSeven.getPrice());
        
        ProductVO outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("Name Teste 12", outputTwelve.getName());
        Assert.assertEquals(CategoryType.non_perishable, outputTwelve.getCategory());
        Assert.assertEquals(Double.valueOf(12D), outputTwelve.getPrice());
        
    }

    @Test
    public void parseVOToEntityTest() {
        Product output = DozerConverter.parseObject(inputObject.mockVO(), Product.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("Name Teste 0", output.getName());
        Assert.assertEquals(CategoryType.non_perishable, output.getCategory());
        Assert.assertEquals(Double.valueOf(0D), output.getPrice());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Product> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Product.class);
        Product outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("Name Teste 0", outputZero.getName());
        Assert.assertEquals(CategoryType.non_perishable, outputZero.getCategory());
        Assert.assertEquals(Double.valueOf(0D), outputZero.getPrice());

        Product outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("Name Teste 7", outputSeven.getName());
        Assert.assertEquals(CategoryType.perishable, outputSeven.getCategory());
        Assert.assertEquals(Double.valueOf(7D), outputSeven.getPrice());
        
        Product outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("Name Teste 12", outputTwelve.getName());
        Assert.assertEquals(CategoryType.non_perishable, outputTwelve.getCategory());
        Assert.assertEquals(Double.valueOf(12D), outputTwelve.getPrice());
    }
}
