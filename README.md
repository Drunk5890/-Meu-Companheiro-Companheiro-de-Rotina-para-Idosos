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
| VS Code | Ambiente de desenvolvimento opcional |

O projeto não utiliza low-code, no-code, MySQL, Spring Boot, Hibernate ou interface gráfica. A única biblioteca externa necessária é o H2.

---

## 🗂️ Estrutura do Projeto

```text
📁 MeuCompanheiro_H2/
├── Pessoa.java
├── Idoso.java
├── Cuidador.java
├── Medicamento.java
├── Atividade.java
├── BancoDados.java
├── MedicamentoDAO.java
├── AtividadeDAO.java
├── MeuCompanheiro.java
├── .vscode/
│   └── settings.json
├── dados/
│   └── schema.sql
├── lib/
│   └── h2-2.5.252.jar
├── testes/
│   ├── TestesUnitarios.java
│   └── TestesIntegracao.java
├── documentacao/
│   ├── Requisitos.pdf
│   ├── Casos_de_Uso.pdf
│   ├── Classes_UML.pdf
│   ├── Arquitetura_e_Modelo.md
│   ├── Metodologia_e_Requisitos.md
│   └── Resultados_Testes.md
├── Iniciar.bat
├── Iniciar.sh
├── Testar.bat
├── Testar.sh
├── Baixar_H2.ps1
├── LEIA-ME.txt
├── .gitignore
└── README.md
```

A pasta `bin/` e o arquivo `dados/meucompanheiro.mv.db` são gerados durante a execução e não precisam ser enviados ao GitHub. O script `dados/schema.sql` contém a estrutura necessária para recriar as tabelas.

---

## 🧱 Classes e Responsabilidades

### `Pessoa` — Classe Base

Classe pai que representa uma pessoa no sistema.

| Atributo | Tipo | Descrição |
|---|---|---|
| nome | String | Nome da pessoa |

**Métodos:** `getNome()` e `setNome()` — consultam e alteram o nome.

### `Idoso` — Herda de `Pessoa`

Representa o idoso que consulta medicamentos e atividades e registra suas confirmações pelo menu. Herda o atributo `nome` e os métodos públicos de `Pessoa`. Utiliza `super(nome)` no construtor.

### `Cuidador` — Herda de `Pessoa`

Representa o cuidador ou familiar responsável pelo gerenciamento da rotina. Herda o atributo `nome` e os métodos públicos de `Pessoa`. Utiliza `super(nome)` no construtor.

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

O método `BancoDados.iniciar()` cria automaticamente as tabelas com `CREATE TABLE IF NOT EXISTS`. O arquivo `dados/schema.sql` documenta a estrutura equivalente; não é necessário importá-lo manualmente. Os comandos que recebem dados do usuário utilizam `PreparedStatement`.

Não são necessárias variáveis de ambiente específicas nem credenciais de login da aplicação. O usuário `sa` sem senha é uma configuração local do protótipo, inadequada para produção.

> ⚠️ Não apague o arquivo `.mv.db` se quiser manter os registros. Não publique bancos com informações pessoais no GitHub. O `.gitignore` exclui os arquivos de banco gerados localmente, mas mantém o esquema SQL versionado.

---

## ▶️ Como Executar

### Pré-requisitos

- JDK 11 ou superior, com `java` e `javac` no PATH. Recomenda-se JDK 21, utilizado nos testes registrados.
- Git (opcional, para clonar o repositório).
- Terminal (CMD, PowerShell, Bash ou similar).
- VS Code com suporte a Java (opcional).
- Driver H2 2.5.252 na pasta `lib/`, incluído no ZIP corrigido.

Confira a instalação do Java:

```bash
java -version
javac -version
```

### 1. Baixar o projeto

No GitHub, acesse o repositório do projeto e selecione **Code → Download ZIP**, depois extraia os arquivos. Ou copie a URL HTTPS exibida em **Code** e execute:

```bash
git clone URL_DO_REPOSITORIO
cd NOME_DA_PASTA_CLONADA
```

Substitua `URL_DO_REPOSITORIO` e `NOME_DA_PASTA_CLONADA` pelos valores reais. O link do repositório da equipe ainda deve ser informado na publicação.

### 2. Executar no Windows

Abra a pasta que contém os arquivos `.java` e execute `Iniciar.bat` com duplo clique ou no PowerShell:

```powershell
.\Iniciar.bat
```

O script compila o código para a pasta `bin/` e inicia o sistema.

### 3. Executar no Linux ou macOS

No terminal, a partir da pasta do projeto:

```bash
chmod +x Iniciar.sh Testar.sh
./Iniciar.sh
```

### 4. Compilar e executar manualmente (opcional)

**Windows:**

```bat
mkdir bin
javac --release 11 -encoding UTF-8 -d bin -cp "lib\h2-2.5.252.jar" *.java
java -cp "bin;lib\h2-2.5.252.jar" MeuCompanheiro
```

**Linux/macOS:**

```bash
mkdir -p bin
javac --release 11 -encoding UTF-8 -d bin -cp 'lib/h2-2.5.252.jar' ./*.java
java -cp 'bin:lib/h2-2.5.252.jar' MeuCompanheiro
```

### 5. Executar pelo VS Code

Abra a pasta inteira do projeto, não apenas um arquivo Java. A configuração `.vscode/settings.json` reconhece as bibliotecas da pasta `lib/` e utiliza `bin/` como pasta de compilação. Execute o script de inicialização no terminal integrado para manter o diretório de trabalho correto.

### Instalação da dependência H2 (se necessário)

O ZIP corrigido inclui a biblioteca. Caso esteja ausente, o script de inicialização tenta baixá-la (com internet; no Linux/macOS, é necessário `curl`). Também é possível [baixar o driver H2 2.5.252](https://repo.maven.apache.org/maven2/com/h2database/h2/2.5.252/h2-2.5.252.jar) e colocá-lo na pasta `lib/` com o nome `h2-2.5.252.jar`.

SHA-256 esperado: `90b11dc413da82070ef15fc943282c1408e6ffd34fa68998335fea8603b5ff20`.

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

### Testes automatizados

No Windows, execute:

```powershell
.\Testar.bat
```

No Linux/macOS, execute:

```bash
chmod +x Testar.sh
./Testar.sh
```

Os scripts compilam e executam os testes Java em uma pasta temporária, com um banco H2 independente dos dados normais da aplicação. Não é necessário instalar JUnit.

| Tipo | Resultado registrado |
|---|---|
| Testes unitários | 13 de 13 verificações aprovadas |
| Testes de integração | 12 de 12 verificações aprovadas |

Os testes incluem herança, encapsulamento, confirmação de registros, validação de horários, conexão com o H2, cadastro, consulta, atualização, exclusão e persistência. Os resultados foram registrados em Linux com Java 21 e compilação `--release 11`. Consulte [Resultados dos testes](documentacao/Resultados_Testes.md). A execução em Windows, macOS e JDK 11 real deve ser validada pela equipe.

### Teste manual e de implantação

1. Baixe ou clone o projeto em outro computador seguindo somente este README.
2. Execute a aplicação e informe nomes fictícios.
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
| [Requisitos](documentacao/Requisitos.pdf) | Requisitos funcionais e não funcionais |
| [Casos de uso](documentacao/Casos_de_Uso.pdf) | Atores e funcionalidades |
| [Diagrama de classes UML](documentacao/Classes_UML.pdf) | Modelagem das classes Java |
| [Arquitetura e modelo](documentacao/Arquitetura_e_Modelo.md) | Camadas e estrutura do banco |
| [Metodologia e requisitos](documentacao/Metodologia_e_Requisitos.md) | Processo de desenvolvimento e análise |
| [Resultados dos testes](documentacao/Resultados_Testes.md) | Evidências de testes automatizados e funcionais |

O PDF consolidado da documentação da A3 deve ser incluído pela equipe na pasta `documentacao/` junto aos arquivos acima.

---

## 🚧 Limitações e Melhorias Futuras

- O sistema não possui login, autenticação ou contas independentes.
- Os nomes informados não são salvos no banco.
- As confirmações não são reiniciadas automaticamente a cada dia.
- Não há notificações automáticas, contato de emergência ou edição de cadastros na versão atual.
- A confirmação de um medicamento registra a declaração do usuário, não comprova sua ingestão. O sistema não substitui orientações de profissionais de saúde.

---

## 👥 Integrantes do Grupo

| Nome | Curso |
|---|---|
| Yuri Richard da Silva | Análise e Desenvolvimento de Sistemas |
| Guilherme Fernandes do Nascimento | Análise e Desenvolvimento de Sistemas |
| Leticia Vitória Guedes Batista | Ciência da Computação |
| João Lucas de Oliveira Mendes | Ciência da Computação |
| Flauberth Dennes Teixeira de Souza | Análise e Desenvolvimento de Sistemas |

**Instituição:** Universidade Potiguar (UnP)  
**Unidade Curricular:** Projeto e Engenharia de Software  
**Projeto:** A3 — Meu Companheiro
