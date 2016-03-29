# RAMses.circ

**RAMses** é umas das [maquinas hipotéticas](https://pt.wikipedia.org/wiki/M%C3%A1quinas_hipot%C3%A9ticas_da_Universidade_Federal_do_Rio_Grande_do_Sul) projetadas por professores da UFRGS. Este projeto é uma simulação do RAMses feito no software de simulação de sistemas digitais [Logisim](http://www.cburch.com/logisim/pt/index.html).

**Como usar**

Para rodar esta simulação, [baixe o Logisim](http://www.cburch.com/logisim/pt/download.html) e abra o RAMses.circ no logisim.<br> Para rodar programas, pode ser usado o MontadorRamses que converte de "Assembly" para a linguagem de máquina.

**Screenshots**
![alt tag](https://raw.githubusercontent.com/LaercioSantana/RAMses.circ/master/screenshots/ramses.png)
![alt tag](https://raw.githubusercontent.com/LaercioSantana/RAMses.circ/master/screenshots/ramsesEncapsulado.png)
![alt tag](https://raw.githubusercontent.com/LaercioSantana/RAMses.circ/master/screenshots/MontadorRamses.png)



**Estrutura de arquivos**

Exemplos/LOOP_ATE_10.src --> Codigo fonte de um loop ate 10 com passo 2<br>
Exemploa/LOOP_ATE_10.RAM --> Um loop ate 10 com passo 2 em formato de arquivo de memoria do logisim<br>
Exemplos/subrotina soma 1.src --> Codigo fonte de uma subrotina que quando chamada soma incrementa o registrador A<br>
Exemploa/LOOP_ATE_10.RAM --> Uma subrotina que quando chamada soma incrementa o registrador A em formato de arquivo de memoria do logisim<br>
RAMses.circ --> Maquina hipotetica feita no logisim<br>
matriz de led.circ --> Biblioteca para matriz de led do RAMses.cir<br>
