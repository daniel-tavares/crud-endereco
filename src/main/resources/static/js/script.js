$(function() {
	
	
	$("#saveUpdateForm").submit(function(event) {
	    event.preventDefault();
	    
	    var data=getDataForm();
	    
	    if(data.id!="")
		    update(data);
	    else
	    	save(data);
	
	});

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
			list();
		}).fail(function(jqXHR, textStatus, msg) {
			alert(msg);
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
			$("#resultado").html(msg);
		}).fail(function(jqXHR, textStatus, msg) {
			alert(msg);
		});
	}
	
	
	function list(data){
		$.ajax({
			url : "/enderecos",
			type : 'get',
			beforeSend : function() {
				$("#loading").html("ENVIANDO...");
			}
		}).done(function(html) {
			$("#resultado").html(html);
		}).fail(function(jqXHR, textStatus, msg) {
			alert(msg);
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

});