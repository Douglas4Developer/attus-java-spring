# Attus Backend - Sistema de Gerenciamento de Processos Jurídicos

Este é um projeto desenvolvido com o objetivo de criar uma API RESTful para o gerenciamento de processos jurídicos. O sistema permite a criação, listagem, e gestão de processos, partes envolvidas e ações processuais, além de automatizações usando **Camunda BPM**.
![image](https://github.com/user-attachments/assets/2f3f21e9-6c37-49d2-8f45-2796475cc4a4)
![image](https://github.com/user-attachments/assets/455bebba-edaa-4be5-bb29-e109288989a6)

## Tecnologias Utilizadas

O projeto foi desenvolvido utilizando as seguintes tecnologias:

- **Java 17**: Linguagem principal do projeto.
- **Spring Boot 3**: Framework para simplificar o desenvolvimento de aplicações Java.
- **PostgreSQL**: Banco de dados relacional para persistência dos dados.
- **Docker e Docker Compose**: Para containerização da aplicação e gerenciamento dos serviços.
- **Kubernetes**: Para orquestração de containers e deploy da aplicação.
- **Camunda BPM**: Para orquestração de processos automatizados.
- **Jacoco**: Ferramenta de cobertura de testes.
- **SonarQube**: Ferramenta para análise estática do código e verificação de qualidade.
- **Swagger/OpenAPI**: Para documentação automática da API.
  
## Funcionalidades Principais

1. **Gerenciamento de Processos**: Criação, edição, exclusão e listagem de processos jurídicos.
2. **Gestão de Partes**: Adição e listagem de partes envolvidas nos processos.
3. **Registro de Ações**: Acompanhamento de ações judiciais associadas a um processo.
4. **Automação de Processos**: Integração com o Camunda BPM para automação de etapas processuais, como mudança automática de status após uma audiência.

## Como Executar o Projeto

### Pré-requisitos

- **Java 17** instalado.
- **Docker** e **Docker Compose** instalados.
- **PostgreSQL** instalado (opcional, pode ser utilizado via Docker).
- **SonarQube** (opcional para análise de código local).
  
### Passo a Passo de Configuração

#### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/attus-backend.git
cd attus-backend
```

#### 2. Configuração do Banco de Dados

O projeto utiliza PostgreSQL como banco de dados. Caso você não tenha o PostgreSQL instalado localmente, pode usar o Docker:

```bash
docker-compose up -d
```

Isso irá subir o banco de dados e o Camunda BPM.

#### 3. Executando a Aplicação com Docker

```bash
docker-compose up --build
```

Esse comando irá construir a imagem do backend, configurar o banco de dados e o Camunda BPM, e iniciar a aplicação na porta **8080**.

#### 4. Documentação da API com Swagger

Após rodar a aplicação, você pode acessar a documentação da API gerada automaticamente pelo Swagger no seguinte endereço:

```plaintext
http://localhost:8080/swagger-ui/index.html
```

#### 5. Testes Automatizados e Cobertura

Para rodar os testes automatizados e gerar o relatório de cobertura Jacoco, use:

```bash
mvn clean verify
```

Os relatórios de cobertura serão gerados na pasta `target/site/jacoco`.

#### 6. Análise de Código com SonarQube

![image](https://github.com/user-attachments/assets/5ae181b0-ef65-4a6a-a572-ce18bc5a803e)

Se você quiser rodar a análise de código no **SonarQube**, certifique-se de que o **SonarQube** está rodando e use o seguinte comando:

```bash
mvn clean verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=seu-token-sonar
```

Ou, para rodar via Docker:

```bash
docker container run --rm --network=host -e SONAR_HOST_URL="http://localhost:9000" -v "$(pwd):/usr/src" sonarsource/sonar-scanner-cli -D"sonar.projectKey=attus" -D"sonar.sources=/usr/src/src" -D"sonar.java.binaries=/usr/src/target/classes" -D"sonar.host.url=http://localhost:9000" -D"sonar.login=seu-token-sonar"
```

### 7. Subindo o Projeto Localmente e Expondo Externamente com Ngrok

#### 1. Inicie o Minikube e Configure o Docker:
```bash
minikube start
minikube -p minikube docker-env --shell powershell | Invoke-Expression
```

#### 2. Build da Imagem Docker:
```bash
docker build -t attus-backend:latest .
```

#### 3. Atualize o Deployment no Kubernetes:
```bash
kubectl delete -f kubernetes/attus-backend-deployment.yml
kubectl apply -f kubernetes/attus-backend-deployment.yml
```

#### 4. Obtenha o URL Local do Serviço no Minikube:
```bash
minikube service attus-backend-service --url
```

#### 5. Exponha o Serviço com Ngrok:
```bash
ngrok http <porta_minikube>
```
Exemplo:
```bash
ngrok http 57486
```

#### 6. Acesse o Swagger Externamente:
```bash
https://xyz123.ngrok.io/swagger-ui/index.html
```

### Resumo de Comandos:
1. **Iniciar Minikube**:  
   ```bash
   minikube start
   ```
2. **Build Docker**:  
   ```bash
   docker build -t attus-backend:latest .
   ```
3. **Atualizar Kubernetes**:  
   ```bash
   kubectl apply -f kubernetes/attus-backend-deployment.yml
   ```
4. **Obter URL Minikube**:  
   ```bash
   minikube service attus-backend-service --url
   ```
5. **Expor com Ngrok**:  
   ```bash
   ngrok http <porta>
   ```

## Como Contribuir

Se você deseja contribuir com o projeto, siga as etapas abaixo:

1. Faça um fork do projeto.
2. Crie uma nova branch para sua feature (`git checkout -b feature/nova-feature`).
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova feature'`).
4. Envie para o branch original (`git push origin feature/nova-feature`).
5. Abra um Pull Request.

## Considerações Finais

Este projeto foi desenvolvido com o foco em aplicar boas práticas de desenvolvimento, como princípios **SOLID**, **Clean Code**, e utilização de **Design Patterns**. Além disso, a cobertura de testes é garantida com o uso do **Jacoco** e a qualidade do código é assegurada com o **SonarQube**.

Sinta-se à vontade para explorar, contribuir ou utilizar como base para outros projetos!
 
