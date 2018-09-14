$(function() {
	
	
	iniciarComponentes();
	
	
	 $(".btn-novo").click(function(){
		 $(".btn-novo").css("display","none");
		 $(".btn-save-update").text("Salvar");
		 $('input#id').val("");
		 $('input#rua').val("");
	     $('input#cep').val("");
	     $('input#estado').val("");
	     $('input#bairro').val("");
	     $('input#numero').val("");
	     $('input#cidade').val("");
	     $("input#complemento").val("");
	 });
	 
	
	$("#saveUpdateForm").submit(function(event) {
	    event.preventDefault();
	    
	    var data=getDataForm();
	    
	    if(data.id!=""){
		    update(data);
	    }else{
	    	save(data);
	    }
	    
	});
	
	
	function iniciarComponentes(){
		carregarEnderecos();	
		$(".btn-save-update").text("Salvar");
		$(".btn-novo").css("display","none");
		
	}

	function save(data){
		$.ajax({
			url : "/enderecos",
			type : 'post',
			data :JSON.stringify(data),
			contentType : 'application/json',
		    dataType : 'json',
			beforeSend : function() {
				$("#loading").html("ENVIANDO...");
			}
		}).done(function(msg) {
			$("#resultado").html("<div class=\"alert alert-primary\" role=\"alert\">Registro salvo com sucesso</div>");
			carregarEnderecos();
		}).fail(function(jqXHR, textStatus, msg) {
			$("#resultado").html("<div class=\"alert alert-danger\" role=\"alert\">Erro ao salvar endereco:"+jqXHR.responseJSON.defaultMessage+"</div>");
		});
	}
	
	function update(data){
		$.ajax({
			url : "/enderecos",
			type : 'put',
			contentType : "application/json",
			dataType : 'json',
			data :JSON.stringify(data),
			beforeSend : function() {
				$("#loading").html("ENVIANDO...");
			}
		}).done(function(msg) {
			$("#resultado").html("<div class=\"alert alert-primary\" role=\"alert\">Registro atualizado com sucesso</div>");
			carregarEnderecos();
		}).fail(function(jqXHR, textStatus, msg) {
			$("#resultado").html("<div class=\"alert alert-danger\" role=\"alert\">Erro ao atualizar endereco</div>");
		});
	}
	
	function excluir(id){
		$.ajax({
			url : "/enderecos/"+id,
			type : 'delete',
			beforeSend : function() {
				$("#loading").html("Excluindo...");
			}
		}).done(function() {
			$("#resultado").html("<div class=\"alert alert-primary\" role=\"alert\">Registro excluido com sucesso</div>");
			
		}).fail(function(jqXHR, textStatus, msg) {
			$("#resultado").html("<div class=\"alert alert-danger\" role=\"alert\">Erro ao excluir endereco.</div>");
		});
	}
	
	
	
	function getDataForm(){
		  return {
			  "id":$('input#id').val(),
			  "rua":$('input#rua').val(),
		      "cep": $('input#cep').val(),
		      "estado": $('input#estado').val(),
		      "bairro": $('input#bairro').val(),
		      "numero":$('input#numero').val(),
		      "cidade":$('input#cidade').val(),
		      "complemento":$("input#complemento").val()
		  }   
	}
	
	

	function carregarEnderecos(){	

		var columns = [
		               { "data": "id"},
		               { "data": "rua"},	               
		               { "data": "numero" },	               
		               { "data": "cep" },
		               { "data": "cidade" },
		               { "data": "estado" },
		               { "data": "bairro" },
		               { "data": "complemento" },
		               { "data": "null", "class": "center", "defaultContent" : '<span class="acoesDatatable"></span>'}
		           ];
	    
	  
	    var columnDefs = [
	                      {targets: [0], searchable: false},
	                      {targets: [1], "type": "char-specials"},
	              		  {targets: [2], "type": "char-specials"},
	              		  {targets: [3], "type": "char-specials"},
	              		  {targets: [4], "type": "char-specials"},
	              		  {targets: [5], "type": "char-specials"},
	              		  {targets: [6], "type": "char-specials"},
	              		  {targets: [7], "type": "char-specials"},
	                      {targets: [8], searchable: false, orderable: false}
	                  ];
	    
	     fnDataTable("#tableEndereco", "/enderecos", columns, columnDefs);

	 } 



	function fnDataTable(tableId, urlRequisicao, columns, columnDefs, zeroRecords, emptyTable, history) {
	    $(tableId).DataTable({
	    	destroy: true,
	    	aaSorting: [],
	        bProcessing: true,
	        ajax: {
	            url: urlRequisicao,
	            dataSrc: ''
	        },
	        columns: columns,
	        columnDefs: columnDefs,
	        language: {
	            processing:     "Processando...",
	            search:         "Pesquisar:",
	            lengthMenu:     "Mostrar _MENU_ registros por p&aacute;gina",
	            info:           "Mostrar de _START_ at&eacute; _END_ de _TOTAL_ registros",
	            infoEmpty:      "Mostrando 0 at&eacute; 0 de 0 registros",
	            infoFiltered:   "(Filtrar de _MAX_ total registros)",
	            infoPostFix:    "",
	            loadingRecords: "Carregando...",
	            zeroRecords:    "Nenhum registro encontrado para o filtro informado!",
	            emptyTable:     "Nenhum registro encontrado!",
	            paginate: {
	                first:      "Primeiro",
	                previous:   "Anterior",
	                next:       "Pr&oacute;ximo",
	                last:       "&Uacute;ltimo"
	            },
	            aria: {
	                sortAscending:  ": Ordenar colunas de forma ascendente",
	                sortDescending: ": Ordenar colunas de forma descendente"
	            }
	        },
	        fnDrawCallback : function() {
	            $(tableId).find("tr").each(function() {
	            	    var acoes = $(this).find('.acoesDatatable');
	                    var excluirCode = "<a class=\"linkExcluir\" href=\"#\">Excluir</a>";
	                    var editarCode =  "<a class=\"linkEditar\" href=\"#\">Editar</a>";
	                   
	                    
	                    
	         	                    
	                    var id=$(this).find('td:eq(0)').text();
	                    var rua=$(this).find('td:eq(1)').text();
	                    var numero=$(this).find('td:eq(2)').text();
	                    var cep=$(this).find('td:eq(3)').text();
	                    var cidade=$(this).find('td:eq(4)').text();
	                    var estado=$(this).find('td:eq(5)').text();
	                    var bairro=$(this).find('td:eq(6)').text();
	                    var complemento=$(this).find('td:eq(7)').text();
	                    
	                    
	                    acoes.html(editarCode + "  " + excluirCode);
	                    acoes.on("click", ".linkExcluir", function () {
		                       excluir(id);
		                       carregarEnderecos();
		                });
	                    
	                    acoes.on("click", ".linkEditar", function () {
	                    	 $('input#id').val(id);
	           			     $('input#rua').val(rua);
	           		         $('input#cep').val(cep);
	           		         $('input#estado').val(estado);
	           		         $('input#bairro').val(bairro);
	           		         $('input#numero').val(numero);
	           		         $('input#cidade').val(cidade);
	           		         $("input#complemento").val(complemento);
	           				 $(".btn-save-update").text("Atualizar");
	           				 $(".btn-novo").css("display","block");
	           				 $(".btn-novo").text("Novo");
	           				 
		                });
	            });
	        }
	    });
	}
});