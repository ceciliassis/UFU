window.onload = function () { 
    //ROLAGEM
    
    var largura = window.innerWidth;
    var altura = window.innerHeight;

    $("#notas").css("width", largura);
    $("#notas").css("height", altura);
    $("#biblio").css("width", largura);
    $("#biblio").css("height", altura);
    $("#song").css("width", largura);
    $("#song").css("height", altura);
    $("#talk").css("width", largura);
    $("#talk").css("height", altura);
    $("#menu").css("width", largura);
    $("#menu").css("height", altura);
    
    $("#a").click(function(){
        $('html, body').animate({
           scrollTop: $("#notas").offset().top
         }, 1000);
    });
    $("#b").click(function(){
     
        $('html, body').animate({
           scrollTop: $("#biblio").offset().top
         }, 1000);
    });
    $("#d").click(function(){
        
        $('html, body').animate({
           scrollTop: $("#talk").offset().top
         }, 1000);
         
    });
    $("#c").click(function(){
         alert("BAM BAM BAM!");  
    });
    //Rolagem menuzin
    $("#e").click(function(){
        $('html, body').animate({
           scrollTop: $("#menu").offset().top
         }, 1000);
    });
    
    $("#g").click(function(){
     
        $('html, body').animate({
           scrollTop: $("#biblio").offset().top
         }, 1000);
    });
    $("#h").click(function(){
        alert("Bem, chegamos ao final, muito obrigada pela visita!");
        $('html, body').animate({
           scrollTop: $("#talk").offset().top
         }, 1000);
         
    });
    
    $("#i").click(function(){
        $('html, body').animate({
           scrollTop: $("#menu").offset().top
         }, 1000);
    });
    $("#j").click(function(){
        $('html, body').animate({
           scrollTop: $("#notas").offset().top
         }, 1000);
    });
    $("#l").click(function(){
        alert("Bem, chegamos ao final, muito obrigada pela visita!");
        $('html, body').animate({
           scrollTop: $("#talk").offset().top
         }, 1000);
         
    });

    
    ////////////////////// TABELAS
    var tab, tab2,linha, celula, tit, aut, edi, numPage, nome, mat, nota;
    tab =  document.getElementById("tabBiblio");
    tab2 =  document.getElementById("tabNota");
	
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

};