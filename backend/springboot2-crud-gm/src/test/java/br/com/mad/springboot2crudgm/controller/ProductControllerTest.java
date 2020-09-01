package br.com.mad.springboot2crudgm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mad.springboot2crudgm.model.enumeration.CategoryType;
import br.com.mad.springboot2crudgm.service.ProductServices;
import br.com.mad.springboot2crudgm.vo.FilterVO;
import br.com.mad.springboot2crudgm.vo.ProductVO;
 

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	public static final String PATH = "/api/product/v1";
	public static final String NAME_PRODUCT = "ovo";
	
	@MockBean
    private ProductServices productServices;
 
    @Test
    public void findAll() throws Exception {
//    	given(helloService.listar()).willReturn(new Hello("Hello"));
    	mockMvc.perform(get(PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//    	System.out.println("content = "+result.getResponse().getContentAsString());
    }
    
    @Test
    public void findProductByName() throws Exception {
        mockMvc.perform(get(PATH+"/findProductByName/{name}", NAME_PRODUCT)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void findProductByFilter() throws Exception {
        mockMvc.perform(post(PATH+"/findProductByFilter")
        		.content(asJsonString(new FilterVO()))
        		.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void findById() throws Exception {
        mockMvc.perform(get(PATH+"/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void create() throws Exception {
        
    	mockMvc.perform(post(PATH)
        		.content(asJsonString(new ProductVO(null, "Teste Create", CategoryType.perishable, 1D)))
        		.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void update() throws Exception {
        
    	mockMvc.perform(put(PATH)
        		.content(asJsonString(new ProductVO(1L, "Teste Create", CategoryType.perishable, 1D)))
        		.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void deleteProduct() throws Exception {
        mockMvc.perform(delete(PATH+"/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
