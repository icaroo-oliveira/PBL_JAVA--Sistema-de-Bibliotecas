# pbl1
 Sistema de gerenciamento de biblioteca (EXA863 - 2023.2 - UEFS)

### Conhecimentos adquiridos

- Classes e Objetos
- Interface
- Classe Abstrata
- Herança
- Padrão de Projeto DAO (Data Access Object)
- Padrão de Projeto Singleton
- Diagrama de Classes

### Diagrama de Classes

![Diagrama de Classe](/doc/Diagrama_de_classes.pdf)

### Requisitos do sistema
1. **Registro de Livros:** Permite adicionar diferentes tipos de livro, com campos como: autor, titulo, editora, ISBN, categoria e disponibilidade.
2. **Pesquisa de Livros:** Os usuários devem ser capazes de pesquisar livros por autor, titulo, ISBN ou categoria.
3. **Empréstimo e Devolução:** Registro de empréstimo, com data de empréstimo e data de entrega e a identificação do usuário. Deve permitir o registro de devolução e atualização da disponibilidade do livro.
4. **Reserva de livros:** Os usuários devem ter a opção de reservar livros que estejam emprestados por outros usuários. O sistema deve registrar a reserva por ordem de solicitação.
5. **Renovação de Empréstimos:** O sistema deve permitir a renovação dos empréstimos de livros, desde que não haja outras reservas para o mesmo livro e o limite de renovações não tenha sido atingido.
6. **Controle de Usuários:** O sistema deve permitir o cadastro de novos usuários, com informações como nome, endereço, telefone e número de identificação. Além disso, deve ser possível bloquear uma conta, não permitindo que o usuário faça empréstimos e renovação.
7. **Relatórios e Estatísticas:** O sistema deve ser capaz de gerar relatórios e estatísticas, como número de livros emprestados, atrasados e reservados; histórico de empréstimos de um usuário específico; e livros mais populares.
8. **Gerenciamento de Multas:** O sistema deve ser capaz de calcular e registrar multas por atraso na devolução de livros. O usuário deverá ser multado com o dobro de dias em atraso.
9. **Gerenciamento de Acervo:** O sistema deve permitir o gerenciamento do acervo da biblioteca, incluindo adição, remoção e atualização de informações sobre os livros, além do controle de estoque.
10. **Controle de operadores do sistema:**O sistema deve permitir o cadastro de novos operadores, com informações como nome, número de identificação, cargo e senha de acesso. Os cargos podem ser do tipo Administrador ou Bibliotecário. O Bibliotecário só terá acesso às funcionalidades #1, #2 e #3.

### Estrutura de diretórios para desenvolvimento
- [model](main/src/main/java/org/biblioteca/Model): modelos de dados do sistema
- [dao](main/src/main/java/org/biblioteca/dao): implementação do padrão DAO (Data Access Object)
- [exceptions](main/src/main/src/main/java/org/biblioteca/excepctions): modelos de exceções
- *controller (em breve...):*
- *view (em breve...):*
## Desenvolvimento

### Instalação

Faça o download do repositório

```
git clone https://github.com/icaroo-oliveira/pbl1.git
```

E abra o projeto no IntelliJ IDEA e aguarde o download das dependências.

## Executando os testes de unidade

Para rodar os testes de unidade clique com o botão direito sobre a pasta (main/src/test/java) e "run all tests"

## Construído com

* [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/) - IDE utilizado para codificação
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [JavaFX](https://openjfx.io/) - Framework para o front-end
* [Scene Builder](https://gluonhq.com/products/scene-builder/) - Para construção das telas
