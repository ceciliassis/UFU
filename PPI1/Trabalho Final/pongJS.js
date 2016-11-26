
window.onload = function(){
    var canvas = document.getElementById("pongCanvas");
    var ctx = canvas.getContext("2d");
    var teclas = {};
    var bola = {
      x: canvas.width / 2 - 15, 
      y: canvas.height / 2 - 15,
      altura: 30,
      largura: 30,
      dirx: -1, //começa indo pra esquerda , logo decrementa o x
      diry: 1, // começa indo pra baixo
      mod: 0,
      speed: 1
    };
    
    var blocoEsquerda = {
      x: 10, //margem de 10px
      y: canvas.height / 2 -60,
      altura: 120,
      largura: 30,
      pontos: 0,
      speed: 10
      
    };
    
    var blocoDireita = {
      x: 560, //margem de 10px
      y: canvas.height / 2 -60,
      altura: 120,
      largura: 30,
      pontos: 0,
      speed: 10
      
    };
    document.addEventListener("keydown", function(e){
       teclas[e.keyCode] = true; 
       //alert(e.keyCode);
    });
    
    document.addEventListener("keyup", function(e){
        delete teclas[e.keyCode];
    });
    
    function moveBloco(){
        if(87 in teclas && blocoEsquerda.y >0 )
            blocoEsquerda.y = blocoEsquerda.y - blocoEsquerda.speed;
        else if (83 in teclas && blocoEsquerda.y + blocoEsquerda.altura < canvas.height)
            blocoEsquerda. y += blocoEsquerda.speed;
        if (38 in teclas && blocoDireita.y > 0)
            blocoDireita.y -= blocoDireita.speed;
        else if (40 in teclas && blocoDireita.y + blocoDireita.altura < canvas.height)
            blocoDireita.y += blocoDireita.speed;
            
    }
    
    function moveBola(){
        if(bola.y + bola.altura >= blocoEsquerda.y && bola.y <= blocoEsquerda.y + blocoEsquerda.altura && bola.x <= blocoEsquerda.x + blocoEsquerda.largura){
            bola.dirx = 1;
            bola.mod += 0.2;
        }
            
        else if (bola.y + bola.altura >= blocoDireita.y && bola.y <= blocoDireita.y + blocoDireita.altura && bola.x + bola.largura >= blocoDireita.x){
            bola.dirx = -1;
            bola.mod += 0.1; //a cada colisao a velocidade da bola aumentará
        }
            
        if(bola.y <= 0)
            bola.diry = 1; //bola desce
        
        else if (bola.y + bola.altura >= canvas.height)
            bola.diry = -1; //bola sobe
       
        bola.x += (bola.speed + bola.mod) * bola.dirx;
        bola.y += (bola.speed + bola.mod) * bola.diry;
        
        if (bola.x < blocoEsquerda.x + blocoEsquerda.largura - 15)
            novoJogo ("player2");
        else if (bola.x + bola.largura > blocoDireita.x + 15)
            novoJogo("player1");
    };
    function novoJogo(vencedor){
        if (vencedor === "player1")
            blocoEsquerda.pontos ++;
        else
            blocoDireita.pontos ++;
        
        blocoEsquerda.y = canvas.height / 2 - blocoEsquerda.altura / 2;
        blocoDireita.y = blocoEsquerda.y;
        bola.y = canvas.height / 2 - bola.altura / 2 ;
        bola.x = canvas.width / 2 - bola.largura / 2;
        bola.mod = 0;
    }
    function onScreen (){
        ctx.clearRect(0,0,canvas.width, canvas.height);
        moveBloco();
        moveBola();
        
        ctx.fillStyle = "pink"; 
        ctx.fillRect(blocoEsquerda.x,blocoEsquerda.y,blocoEsquerda.largura,blocoEsquerda.altura);
        ctx.fillRect(blocoDireita.x,blocoDireita.y,blocoDireita.largura,blocoDireita.altura);
        ctx.fillRect(bola.x,bola.y,bola.largura,bola.altura);
        
        ctx.font =  "20px Segoe Print";
        ctx.fillText ("Player 1: "+ blocoEsquerda.pontos, 50, 20);
        ctx.fillText ("Player 2: "+ blocoDireita.pontos, canvas.width - 160, 20);
    }   
    
    setInterval(onScreen, 5);
    //
    //ctx.fillRect(0,0,50,50); // x =0 y =0 com o tamanho 50 por 50   
};