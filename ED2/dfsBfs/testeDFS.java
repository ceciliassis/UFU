//DFS
Vertice um = Grafo.addVertice(1); // preciso que a funcao retorne o 
						//vertice, para que ja seja usado na de aresta
Vertice dois = grafo.addVertice(2);
Vertice tres = grafo.addVertice(3);
Vertice qatro = grafo.addVertice(4);
Vertice cinco = grafo.addVertice(5);
//Ciclos nao sao representados

Aresta umCinco = grafo.addAresta(um, cinco);
Aresta umDois = grafo.addAresta(um, dois);
Aresta doisUm = grafo.addAresta(dois, um);
Aresta doisQatro = grafo.addAresta(dois, qatro);
Aresta doisTres = grafo.addAresta(dois, tres);
Aresta tresDois = grafo.addAresta(tres, dois);
Aresta tresQatro = grafo.addAresta(tres, qatro);
Aresta qatroCinco = grafo.addAresta(qatro, cinco);
Aresta qatroDois = grafo.addAresta(qatro, dois);
Aresta qatroTres = grafo.addAresta(qatro, tres);
Aresta cincoUm = grafo.addAresta(cinco, um);
Aresta cincoQuatro = grafo.addAresta(cinco, qatro);

System.out.println("----- DFS -----");
BuscaGrafoDFS();