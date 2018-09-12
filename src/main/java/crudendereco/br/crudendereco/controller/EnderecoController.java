package br.gov.caixa.portalbanking.service.impl;

@Controller
@RequestMapping("/api/v1/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
	
	@RequestMapping("/save")
	public String save(Endereco endereco){
		
	}
	
	
	@RequestMapping("/update")
	public void update(Endereco endereco){
		enderecoService.update();
	}
	
	
	@RequestMapping("/detete/{id}")
	public String delete(@PathVariable("id") id){
		enderecoService.delete(id);
	} 
	
	
	@RequestMapping("/findAll")
	public String findAll(){
       	return 	enderecoService.findAll();
	}
}
