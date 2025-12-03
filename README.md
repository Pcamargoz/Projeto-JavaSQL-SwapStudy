🧩 Sistema de Gerenciamento — Java | JDBC | DAO | MySQL

Este repositório contém um projeto completo desenvolvido em Java, utilizando JDBC, Padrão DAO, MySQL, Orientação a Objetos, e boas práticas de arquitetura para acesso a dados.
O objetivo é demonstrar domínio sobre:

JDBC puro (sem frameworks)

CRUD completo

Conexão segura com banco

Tratamento de exceções personalizadas

Organização do código com camadas profissionais

Reutilização de conexões

Padrões de repositório e fábrica de DAOs

🚀 Tecnologias utilizadas

Java 17+

MySQL

JDBC

Padrão DAO

Factory Pattern

Orientação a Objetos

Collections (List, Map, HashMap)

Tratamento de exceções com DbException

📂 Estrutura do Projeto
src/
 ├── Application/
 │    └── Program.java
 ├── ModelDao/
 │    ├── SellerDao.java
 │    ├── DepartmentDao.java
 │    └── DaoFactory.java
 ├── ModelDaoImpl/
 │    ├── SellerDaoJDBC.java
 │    └── DepartmentDaoJDBC.java
 ├── ModelEntities/
 │    ├── Seller.java
 │    └── Department.java
 ├── db/
 │    ├── DB.java
 │    ├── DbException.java
 │    └── DbIntegrityException.java
 └── resources/
      └── db.properties

⚙️ Funcionalidades
✔ Seller

Inserir vendedor

Buscar por ID

Buscar todos

Buscar por departamento

Atualizar vendedor

Deletar vendedor

✔ Department

Inserir departamento

Buscar por ID

Listar todos

Atualizar departamento

Deletar departamento

🗄️ Modelo de Banco (MySQL)

Tabela Department

CREATE TABLE department (
  Id INT PRIMARY KEY AUTO_INCREMENT,
  Name VARCHAR(60)
);


Tabela Seller

CREATE TABLE seller (
  Id INT PRIMARY KEY AUTO_INCREMENT,
  Name VARCHAR(60),
  Email VARCHAR(80),
  BirthDate DATE,
  BaseSalary DOUBLE,
  DepartmentId INT,
  FOREIGN KEY (DepartmentId) REFERENCES department(Id)
);

🧠 Pontos importantes do projeto

✔ Uso correto de PreparedStatement

✔ Fechamento adequado de ResultSet, Statement e Connection

✔ Conversão de datas: java.util.Date → java.sql.Date

✔ Implementação completa do padrão DAO

✔ Uso de Factory Pattern para instanciar DAOs

✔ Utilização de HashMap para otimizar criação repetida de entidades em queries com join

▶️ Como executar

Clone o repositório:

git clone https://github.com/SEU-USUARIO/NOME-DO-REPO.git


Configure o arquivo db.properties:

user = seu_usuario
password = sua_senha
dburl = jdbc:mysql://localhost:3306/suabase
useSSL = false


Execute o arquivo:

Application/Program.java

📌 Exemplos de uso (main)

Listar todos os vendedores:

List<Seller> list = sellerDao.findAll();
for (Seller obj : list) {
    System.out.println(obj);
}


Buscar por departamento:

Department dep = new Department(2, null);
List<Seller> listDep = sellerDao.findByDepartment(dep);


Inserir novo vendedor:

Seller newSeller = new Seller(null, "Pedro", "pedro@gmail.com",
    new Date(), 4000.0, department);

sellerDao.insert(newSeller);

🧑‍💻 Autor

Pedro César Camargo dos Santos
Desenvolvedor Java | Backend | JDBC | Spring em progresso
Sorocaba — São Paulo
LinkedIn e GitHub no perfil

⭐ Contribuições

Sinta-se à vontade para abrir PRs, issues ou sugestões!
