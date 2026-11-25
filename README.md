# 🌿 Meu Companheiro — Companheiro de Rotina para Idosos

Sistema desenvolvido em Java com foco em Programação Orientada a Objetos (POO), como parte do Projeto A3 da UC Projeto e Engenharia de Software. O sistema auxilia idosos na organização de medicamentos e atividades diárias, permitindo que cuidadores e familiares acompanhem suas confirmações. Os registros são armazenados no banco de dados H2.

---

## 📋 Descrição do Projeto

O Meu Companheiro busca auxiliar na organização da rotina de idosos, reduzindo dificuldades relacionadas ao esquecimento de horários e facilitando o acompanhamento pelo cuidador.

O sistema permite:

- Informar o nome do **idoso** e do **cuidador/familiar**.
- Cadastrar, consultar e excluir **medicamentos** e **atividades**.
- Confirmar a tomada de medicamentos e a realização de atividades.
- Acompanhar a situação dos registros: pendente ou confirmado.
- Salvar os cadastros e as confirmações no **H2**, mesmo após fechar o programa.
- Validar os horários informados e solicitar confirmação antes de excluir registros.

O sistema funciona pelo terminal (linha de comando), com menus interativos e navegação por números. É um protótipo acadêmico, não um sistema médico.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Finalidade |
|---|---|
| Java | Linguagem de programação |
| POO | Organização em classes e objetos |
| H2 2.5.252 | Banco de dados embarcado em modo arquivo |
| JDBC | Conexão entre o Java e o H2 |
| SQL | Criação de tabelas e manipulação dos registros |
| DAO | Padrão de acesso a dados |
| Git e GitHub | Controle de versões e disponibilização do projeto |
| VS Code | Ambiente de desenvolvimento e execução do projeto |

O projeto não utiliza low-code, no-code, MySQL, Spring Boot, Hibernate ou interface gráfica. A única biblioteca externa necessária é o H2.

---

## 🗂️ Estrutura do Projeto

```text
📁 MeuCompanheiro/
├── .vscode/
│   ├── settings.json
│   └── launch.json
├── lib/
│   └── h2-2.5.252.jar
├── Pessoa.java
├── Idoso.java
├── Cuidador.java
├── Medicamento.java
├── Atividade.java
├── BancoDados.java
├── MedicamentoDAO.java
├── AtividadeDAO.java
├── MeuCompanheiro.java
├── documentacao/
│   └── Documentacao_Completa_A3_Meu_Companheiro_Final_16pg.pdf
├── .gitignore
└── README.md
```

As nove classes Java, a pasta `lib/` e a pasta `.vscode/` compõem o ZIP final de execução. Para a entrega no GitHub, acrescente este `README.md`, o PDF indicado na pasta `documentacao/` e o `.gitignore` descrito abaixo.

A pasta `bin/` contém os arquivos compilados e é criada automaticamente pelo VS Code, ficando oculta no explorador. A pasta `dados/` e o arquivo `dados/meucompanheiro.mv.db` são criados automaticamente pelo programa. Não é necessário incluir esses arquivos gerados no repositório. A estrutura do banco é criada pelo próprio `BancoDados.java`.

---

## 🧱 Classes e Responsabilidades

### `Pessoa` — Classe Base

Classe pai que representa uma pessoa no sistema.

| Atributo | Tipo | Descrição |
|---|---|---|
| nome | String | Nome da pessoa |

**Métodos:** `getNome()` e `setNome()` — consultam e alteram o nome.

### `Idoso` — Herda de `Pessoa`

Representa o idoso que consulta medicamentos e atividades e registra suas confirmações pelo menu. Reutiliza o nome armazenado em `Pessoa` por meio dos métodos públicos herdados. Utiliza `super(nome)` no construtor.

### `Cuidador` — Herda de `Pessoa`

Representa o cuidador ou familiar responsável pelo gerenciamento da rotina. Reutiliza o nome armazenado em `Pessoa` por meio dos métodos públicos herdados. Utiliza `super(nome)` no construtor.

### `Medicamento`

Representa um medicamento cadastrado no sistema.

| Atributo | Tipo | Descrição |
|---|---|---|
| id | int | Identificador do registro no banco |
| nome | String | Nome do medicamento |
| horario | String | Horário informado no cadastro |
| tomado | boolean | Estado da confirmação da tomada |

**Métodos principais:** `confirmarTomada()`, `mostrarMedicamento()`, `getId()`, `getNome()`, `getHorario()` e `isTomado()`.

### `Atividade`

Representa uma atividade da rotina do idoso.

| Atributo | Tipo | Descrição |
|---|---|---|
| id | int | Identificador do registro no banco |
| nome | String | Nome da atividade |
| horario | String | Horário informado no cadastro |
| concluida | boolean | Estado da confirmação da atividade |

**Métodos principais:** `confirmarAtividade()`, `mostrarAtividade()`, `getId()`, `getNome()`, `getHorario()` e `isConcluida()`.

### `BancoDados` — Conexão com o H2

Responsável pela conexão e pela criação automática das tabelas.

| Método | Função |
|---|---|
| `conectar()` | Retorna uma conexão JDBC com o H2 |
| `iniciar()` | Prepara a pasta de dados e cria as tabelas caso ainda não existam |

### `MedicamentoDAO` e `AtividadeDAO` — Acesso a Dados

As classes DAO concentram as operações SQL, separando o acesso ao banco da interação com o usuário.

| Método | Operação SQL | Função |
|---|---|---|
| `cadastrar()` | INSERT | Insere um registro |
| `listar()` | SELECT | Recupera os registros em um vetor de objetos |
| `confirmar()` | UPDATE | Atualiza a situação do registro |
| `excluir()` | DELETE | Exclui um registro pelo ID |

### `MeuCompanheiro` — Classe Principal

Contém o método `main()` e controla os menus, a leitura dos dados, as validações e as chamadas às classes DAO.

**Validações:** nome e horário não podem ficar vazios; o horário deve seguir o formato `HH:mm`, entre `00:00` e `23:59`; opções numéricas inválidas são tratadas. A exclusão exige confirmação do usuário.

---

## 🖥️ Menus do Sistema

### Menu principal

| Opção | Função |
|---|---|
| 1 | Entrar como idoso |
| 2 | Entrar como cuidador/familiar |
| 0 | Sair |

### Menu do idoso

| Opção | Função |
|---|---|
| 1 | Ver medicamentos |
| 2 | Confirmar medicamento tomado |
| 3 | Ver atividades |
| 4 | Confirmar atividade realizada |
| 0 | Voltar ao menu principal |

### Menu do cuidador/familiar

| Opção | Função |
|---|---|
| 1 | Cadastrar medicamento |
| 2 | Ver medicamentos e confirmações |
| 3 | Excluir medicamento |
| 4 | Cadastrar atividade |
| 5 | Ver atividades e confirmações |
| 6 | Excluir atividade |
| 0 | Voltar ao menu principal |

---

## 🧩 Conceitos de POO Utilizados

### ✅ Classes e Objetos

As entidades do sistema são representadas por classes, como `Medicamento`, `Atividade`, `Idoso` e `Cuidador`. Cada cadastro é representado por um objeto Java.

### ✅ Atributos e Métodos

Os atributos armazenam características dos objetos e os métodos executam comportamentos, como confirmar a tomada de um medicamento.

### ✅ Encapsulamento

Os atributos dos modelos são privados (`private`), acessados por métodos públicos, como `getNome()` e `isTomado()`.

### ✅ Herança

`Idoso` e `Cuidador` herdam de `Pessoa`, reutilizando suas características e métodos. A palavra-chave `super` chama o construtor da classe pai.

```text
Pessoa
├── Idoso
└── Cuidador
```

### ✅ Sobrecarga de Construtores

`Medicamento` e `Atividade` possuem dois construtores: um para novos cadastros e outro para objetos recuperados do banco de dados, incluindo ID e estado de confirmação.

---

## 🏗️ Arquitetura do Sistema

O projeto utiliza uma organização simplificada em camadas, com separação de responsabilidades:

| Camada | Classes |
|---|---|
| Apresentação e parte da lógica | MeuCompanheiro |
| Modelo | Pessoa, Idoso, Cuidador, Medicamento, Atividade |
| Acesso a dados | MedicamentoDAO, AtividadeDAO, BancoDados |
| Persistência | Banco H2 |

```text
Usuário → MeuCompanheiro → DAO → JDBC → H2
                ↕          ↕
             Objetos do modelo
```

A lógica de validação permanece na classe principal; não existe uma camada de serviços independente. O padrão DAO permite centralizar os comandos SQL sem misturá-los diretamente aos menus.

---

## 🗄️ Banco de Dados

O H2 funciona em modo embarcado e armazena os registros localmente. Não é necessário instalar um servidor de banco de dados.

### Tabela `medicamentos`

| Campo | Tipo SQL | Restrição |
|---|---|---|
| id | INTEGER | Chave primária, gerado automaticamente |
| nome | VARCHAR(120) | NOT NULL |
| horario | VARCHAR(30) | NOT NULL |
| tomado | BOOLEAN | NOT NULL, DEFAULT FALSE |

### Tabela `atividades`

| Campo | Tipo SQL | Restrição |
|---|---|---|
| id | INTEGER | Chave primária, gerado automaticamente |
| nome | VARCHAR(120) | NOT NULL |
| horario | VARCHAR(30) | NOT NULL |
| concluida | BOOLEAN | NOT NULL, DEFAULT FALSE |

As duas tabelas são independentes e não possuem chaves estrangeiras. Os nomes do idoso e do cuidador são informados em cada execução e não são persistidos.

### Configuração

| Configuração | Valor |
|---|---|
| URL JDBC | `jdbc:h2:file:./dados/meucompanheiro` |
| Usuário do banco | `sa` |
| Senha | Vazia |
| Arquivo gerado | `dados/meucompanheiro.mv.db` |
| Driver | `lib/h2-2.5.252.jar` |

O método `BancoDados.iniciar()` cria automaticamente as tabelas com `CREATE TABLE IF NOT EXISTS`. Os comandos de criação das duas tabelas estão no próprio arquivo `BancoDados.java`. Não é necessário importar um arquivo SQL ou configurar um servidor manualmente. Os comandos que recebem dados do usuário utilizam `PreparedStatement`.

Não são necessárias variáveis de ambiente específicas nem credenciais de login da aplicação. O usuário `sa` sem senha é uma configuração local do protótipo, inadequada para produção.

> ⚠️ Não apague o arquivo `.mv.db` se quiser manter os registros. Não publique bancos com informações pessoais no GitHub. Configure o `.gitignore` conforme a seção de versionamento para não enviar os arquivos de banco gerados localmente.

---

## ▶️ Como Executar

### Pré-requisitos

- JDK 21 recomendado (versão utilizada nos testes registrados). O código também foi compilado com compatibilidade Java 11, mas a execução em um JDK 11 real não foi validada.
- [Visual Studio Code](https://code.visualstudio.com/).
- [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack), da Microsoft.
- Git (opcional, para clonar o repositório).
- Driver H2 2.5.252 na pasta `lib/`, já incluído no ZIP final.

Confira a instalação do Java no terminal integrado do VS Code:

```bash
java -version
javac -version
```

### 1. Baixar o projeto

No GitHub, acesse o repositório do projeto e selecione **Code → Download ZIP**, depois extraia os arquivos. Como alternativa, copie a URL HTTPS exibida em **Code** e execute:

```bash
git clone URL_DO_REPOSITORIO
cd NOME_DA_PASTA_CLONADA
```

Substitua os marcadores pelos valores reais do repositório. A URL final não foi informada nesta documentação.

### 2. Abrir no VS Code

1. No VS Code, selecione **File → Open Folder** (Arquivo → Abrir Pasta).
2. Abra a pasta que contém diretamente `MeuCompanheiro.java`, `.vscode/` e `lib/`, e não uma pasta externa que englobe o projeto.
3. Confira se a extensão Java está instalada e se `lib/h2-2.5.252.jar` existe.
4. Os arquivos de configuração já estão incluídos no ZIP: não é necessário criar um projeto Maven ou configurar o banco manualmente.

### 3. Executar a aplicação

1. Abra o arquivo `MeuCompanheiro.java`.
2. Clique em **Run**, acima do método `main()`. Como alternativa, pressione `F5` e escolha a configuração **Meu Companheiro**.
3. Utilize o terminal integrado para informar os nomes do idoso e do cuidador e navegar pelos menus.
4. Para sair, selecione `0` no menu principal.

Na primeira execução, o Java criará automaticamente a pasta `dados/`, o arquivo do H2 e as tabelas. Os registros serão recuperados nas próximas execuções. Não há scripts de inicialização obrigatórios nem necessidade de internet para executar com o driver já incluído.

### Configuração do VS Code

- `.vscode/settings.json`: informa onde estão as bibliotecas (`lib/**/*.jar`), define a raiz como pasta de código-fonte, utiliza `bin/` como saída da compilação e oculta essa pasta no explorador.
- `.vscode/launch.json`: define `MeuCompanheiro` como classe principal, a raiz do projeto como diretório de trabalho, o classpath com `bin/` e o driver H2, e o terminal integrado como console.

### Solução de problemas

Se aparecer `No suitable driver found for jdbc:h2:file:./dados/meucompanheiro`, verifique se a pasta aberta é a raiz do projeto e se o driver está na pasta `lib/`. Pressione `Ctrl + Shift + P`, execute **Java: Clean Java Language Server Workspace**, confirme o reinício do ambiente Java e tente novamente usando `F5` com a configuração **Meu Companheiro**.

Se o driver estiver ausente, [baixe o H2 2.5.252](https://repo.maven.apache.org/maven2/com/h2database/h2/2.5.252/h2-2.5.252.jar) e coloque-o em `lib/` com o nome `h2-2.5.252.jar`.

SHA-256 do driver incluído: `90b11dc413da82070ef15fc943282c1408e6ffd34fa68998335fea8603b5ff20`.

### Consultar o banco no H2 Console (opcional)

Com o aplicativo fechado, execute na pasta do projeto:

```bash
java -jar lib/h2-2.5.252.jar
```

Utilize a URL JDBC, o usuário e a senha da tabela de configuração acima. Use apenas localmente e encerre o console após a consulta.

---

## 💡 Exemplo de Uso

Exemplo ilustrativo com nomes e registros fictícios:

```text
Banco H2 conectado! Cadastros ficam salvos na pasta dados.
Nome do idoso: Maria
Nome do cuidador/familiar: Ana

===== MEU COMPANHEIRO =====
1 - Entrar como idoso (Maria)
2 - Entrar como cuidador/familiar (Ana)
0 - Sair
Escolha um numero: 2

===== MENU DO CUIDADOR/FAMILIAR =====
1 - Cadastrar remedio
2 - Ver remedios e confirmacoes
3 - Excluir remedio
4 - Cadastrar atividade
5 - Ver atividades e confirmacoes
6 - Excluir atividade
0 - Voltar ao menu principal
Escolha um numero: 1
Nome do remedio: Medicamento teste
Horario do remedio: 08:00
Remedio cadastrado e salvo no H2!
```

Depois, volte ao menu principal, entre como idoso e selecione a opção `2` para confirmar o medicamento cadastrado. A confirmação ficará salva no H2.

---

## 🧪 Testes do Sistema

### Testes automatizados — evidências registradas

| Tipo | Resultado registrado |
|---|---|
| Testes unitários | 13 de 13 verificações aprovadas |
| Testes de integração | 12 de 12 verificações aprovadas |

Os testes incluem herança, encapsulamento, confirmação de registros, validação de horários, conexão com o H2, cadastro, consulta, atualização, exclusão e persistência. Foram reexecutados com as mesmas nove classes Java e o driver H2 da versão final, em Linux com Java 21 e compilação `--release 11`.

As classes de teste foram preservadas da versão anterior e executadas separadamente, em um ambiente isolado, sem modificar o banco de uso normal. Elas não integram o ZIP final simplificado para VS Code. Os resultados estão registrados nas páginas 12 e 13 da documentação técnica. Para reproduzir as verificações automatizadas, é necessário recuperar os arquivos de teste anteriores; a equipe deve conservá-los como evidência acadêmica.

A execução pelo VS Code no Windows, no macOS e a implantação em outro computador da equipe ainda precisam ser confirmadas.

### Teste manual e de implantação

1. Baixe ou clone o projeto em outro computador seguindo somente este README.
2. Abra a pasta do projeto no VS Code, execute `MeuCompanheiro.java` pelo Run ou F5 e informe nomes fictícios. Use uma cópia de teste sem dados pessoais.
3. No cuidador, cadastre um medicamento e uma atividade com horários válidos.
4. No idoso, consulte e confirme os dois registros.
5. Encerre o programa normalmente e abra novamente. Verifique se os registros e as confirmações permaneceram salvos.
6. Tente cadastrar um horário inválido, como `99:99`: o sistema deve recusar.
7. Teste cancelar uma exclusão com `N` e confirme outra com `S`.
8. Registre o ambiente e os resultados obtidos pela equipe.

---

## 📚 Documentação e Metodologia

O desenvolvimento foi realizado de forma iterativa e incremental: iniciou-se com classes e vetores, evoluindo para menus separados, atividades, integração com H2, validações e testes. Essa abordagem é compatível com práticas ágeis, sem pressupor a adoção formal de Scrum.

| Documento | Descrição |
|---|---|
| [Documentação Completa da A3](documentacao/Documentacao_Completa_A3_Meu_Companheiro_Final_16pg.pdf) | PDF de 16 páginas com requisitos funcionais e não funcionais, elicitação, metodologia, casos de uso, diagrama de classes UML, arquitetura, modelo do banco H2, integração, testes e configuração. |

O PDF consolidado deve ser colocado na pasta `documentacao/` com o nome indicado acima para que o link funcione no GitHub.

---

## 🔧 Versionamento e Configuração

O código-fonte, a biblioteca H2, os arquivos de configuração do VS Code, este README e a documentação técnica devem ser disponibilizados no repositório público da equipe.

Crie um arquivo `.gitignore` na raiz do projeto com o seguinte conteúdo, caso ainda não exista:

```gitignore
bin/
*.class
/dados/*.mv.db
/dados/*.trace.db
/dados/*.lock.db
```

A pasta `bin/` e os bancos gerados localmente não precisam ser enviados. O código de criação das tabelas está em `BancoDados.java`, que deve permanecer no repositório. Preserve os arquivos dos testes anteriores como material de avaliação, sem incluí-los na raiz de código-fonte do VS Code.

Antes da entrega, confirme a visibilidade pública do repositório e teste a execução em outro computador seguindo este README.

---

## 🚧 Limitações e Melhorias Futuras

- O sistema não possui login, autenticação ou contas independentes.
- Os nomes informados não são salvos no banco.
- As confirmações não são reiniciadas automaticamente a cada dia.
- Não há notificações automáticas, contato de emergência ou edição de cadastros na versão atual.
- A confirmação de um medicamento registra a declaração do usuário, não comprova sua ingestão. O sistema não substitui orientações de profissionais de saúde.

---

## 👥 Integrantes do Grupo

| Nome | Curso | RA |
|---|---|---|
| Yuri Richard da Silva | Análise e Desenvolvimento de Sistemas | 12826132322 |
| Guilherme Fernandes do Nascimento | Análise e Desenvolvimento de Sistemas | — |
| Leticia Vitória Guedes Batista | Ciência da Computação | — |
| João Lucas de Oliveira Mendes | Ciência da Computação | — |
| Flauberth Dennes Teixeira de Souza | Análise e Desenvolvimento de Sistemas | — |

**Instituição:** Universidade Potiguar (UnP)  
**Unidade Curricular:** Projeto e Engenharia de Software  
**Projeto:** A3 — Meu Companheiro
