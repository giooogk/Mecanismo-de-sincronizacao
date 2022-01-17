# Mecanismo-de-sincronizaca

Este repositório contem duas versões de uma lista encadeada que pode ser acessada por varias threadas ao mesmo tempo para operações de inserção, leitura e remoção.

### explicit-lock

O projeto foi feito utilizando JAVA SDK 11. Para os testes, foi utilizado o JUnit 5.
Esta versão utiliza locks explícitos para realizar as operações da lista. Para ver a lista funcionando, execute os testes.

#### Como compilar
Utilizando o IntelliJ, após abrir o projeto, basta clicar em __Build -> Build Project__.

#### Rodando testes
Utilizando o IntelliJ, após abrir o projeto, navegue até `test/`, clique com o botão esquerdo em `SimpleConcurrentListTest` e em seguida em "Run 'SimpleConcurrentListTest'".

### implicit-lock
O projeto foi implementado em Java, usando o eclipse IDE.

#### Como executar
Basta ir na classe Main.java, clicar com o botão direito ir na opção "Run as" e, em seguida, clicar na primeira opção "Java application"
![image](https://user-images.githubusercontent.com/47827014/149682207-a8a9b656-27c9-4d9e-9bce-b139f9ad05a4.png)
