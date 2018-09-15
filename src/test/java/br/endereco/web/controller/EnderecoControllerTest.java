package br.endereco.web.controller;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.endereco.model.Endereco;
import br.endereco.service.EnderecoService;

@ContextConfiguration(classes= {EnderecoController.class, Endereco.class, String.class})
@RunWith(SpringRunner.class)
@WebMvcTest(value = {EnderecoController.class}, secure = false)
public class EnderecoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EnderecoService enderecotService;
	
	@Autowired
	Endereco mockEndereco;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Before
	public void setUp() {
		mockEndereco = new Endereco();
		mockEndereco.setId(1l);
		mockEndereco.setRua("Nações unidas");
		mockEndereco.setCep("89765432");
		mockEndereco.setCidade("São Paulo");
		mockEndereco.setEstado("Sao Paulo");
		mockEndereco.setNumero("1000");
		mockEndereco.setComplemento("Marginal");
	}
	
	@Test
	public void shoudSaveEndereco() throws Exception {
		
		Mockito.when(enderecotService.save(mockEndereco)).thenReturn(mockEndereco);

		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
				.post("/enderecos")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockEndereco)).characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	
	
	
	@Test
	public void shoudSaveEnderecoSemCampoObrigatorio() throws Exception {
		
		
		mockEndereco.setRua(null);
		
		Mockito.when(enderecotService.save(mockEndereco)).thenReturn(mockEndereco);
		
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
				.post("/enderecos")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockEndereco)).characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

	    assertEquals(response.getContentAsString(), "Rua é um campo brigatório");
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		
	}
	
	
	
	@Test
	public void shoudUpdateEndereco() throws Exception {
		
		
		mockEndereco.setRua("Rua atualizada");
		
		Mockito.when(enderecotService.save(mockEndereco)).thenReturn(mockEndereco);
		Mockito.when(enderecotService.findById(mockEndereco.getId())).thenReturn(Optional.of(mockEndereco));

		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
				.put("/enderecos")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockEndereco)).characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

	   assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	
	@Test
	public void shoudUpdateEnderecoSemID() throws Exception {
		
		mockEndereco.setId(null);
		mockEndereco.setRua("Rua atualizada");
		
		Mockito.when(enderecotService.save(mockEndereco)).thenReturn(mockEndereco);
		Mockito.when(enderecotService.findById(mockEndereco.getId())).thenReturn(Optional.of(mockEndereco));
		
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
				.put("/enderecos")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockEndereco)).characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

	    assertEquals(response.getContentAsString(), "Campo id não pode ser nulo na atualizacao");
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		
	}
	
	
	
	@Test
	public void shoudDeleteEndereco() throws Exception {
		
		
		Mockito.when(enderecotService.findById(mockEndereco.getId())).thenReturn(Optional.of(mockEndereco));
		
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
				.delete("/enderecos/1")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockEndereco)).characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

	    assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	
	@Test
	public void shoudDeleteEnderecoComIdInexistente() throws Exception {
		
		Mockito.when(enderecotService.findById(4l)).thenReturn(Optional.empty());
		
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
				.delete("/enderecos/4"))
				.andReturn().getResponse();


	    assertEquals(response.getContentAsString(), "Endereco a ser atualizado não existe na base de dados");
	    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		
	}
	
	
	@Test
	public void shoudFindEnderecoById() throws Exception {
		
		Mockito.when(enderecotService.findById(1l)).thenReturn(Optional.of(mockEndereco));
		
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
				.get("/enderecos/1"))
				.andReturn().getResponse();

	    assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}

}
