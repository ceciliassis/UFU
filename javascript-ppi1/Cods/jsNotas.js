//JS Notas

var tab2,linha, celula, nome, mat, nota;
tab2 =  document.getElementById("tabNota");


$("#okok").click(function(){
			mat = document.formNota.mat.value;
	nome = document.formNota.nomee.value;
	nota = document.formNota.nota.value;
	
	linha = tab2.insertRow(1); 
	celula = linha.insertCell(0);
	celula.innerHTML = mat;
	celula = linha.insertCell(1);
	celula.innerHTML = nome;
	celula = linha.insertCell(2);
	celula.innerHTML = nota; 
	}); 