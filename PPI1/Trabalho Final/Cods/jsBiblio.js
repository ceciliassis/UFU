//JS Biblioteca

	var tab, tab2,linha, celula, tit, aut, edi, numPage;
	tab =  document.getElementById("tabBiblio");

 $("#ok2").click(function(){
			tit = document.formBiblio.tit.value;
	aut = document.formBiblio.aut.value;
	edi = document.formBiblio.edi.value;
	numPage = document.formBiblio.numPg.value;

	linha = tab.insertRow(1); 
	celula = linha.insertCell(0);
	celula.innerHTML = tit;
	celula = linha.insertCell(1);
	celula.innerHTML = aut;
	celula = linha.insertCell(2);
	celula.innerHTML = numPage;
	celula = linha.insertCell(3);
	celula.innerHTML = edi;
	}); 