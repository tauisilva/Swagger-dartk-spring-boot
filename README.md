# Swagger Dartk Spring Boot

Este projeto demonstra como personalizar o Swagger UI em uma aplicação Spring Boot, aplicando um tema personalizado utilizando CSS. O Swagger UI permite documentar e testar a API de forma interativa, e com a personalização, ele também pode refletir a identidade visual do seu projeto.

## **Objetivo**

O objetivo deste projeto é mostrar como personalizar o Swagger UI com um tema próprio, utilizando um arquivo CSS personalizado para modificar as cores, fontes e outros elementos da interface.

## **Tecnologias Utilizadas**

- **Spring Boot**: Framework para construção da API.
- **Swagger UI**: Ferramenta de documentação e teste de APIs.
- **CSS**: Para personalizar a aparência do Swagger UI.

## **Funcionalidades**

- Customização do tema do Swagger UI.
- Aplicação de um arquivo `swagger-theme.css` no projeto Spring Boot.
- Personalização das cores, fontes e estilo da interface.

## **Como Usar**

### 1. **Configuração Inicial**

Clone o repositório:

```bash
git clone https://github.com/tauisilva/Swagger-dartk-spring-boot.git
```

### 2. **Adicionar o Arquivo CSS Personalizado**

Crie um arquivo `swagger-theme.css` na pasta `src/main/resources/static/` e adicione o CSS desejado. No exemplo, um tema foi modificado para alterar as cores e o layout do Swagger UI.

### 3. **Alterações no Swagger Config**

Em seguida, você precisa garantir que o Swagger UI carregue automaticamente o arquivo CSS. Isso é feito alterando o arquivo de configuração do Swagger em sua aplicação Spring Boot.

### 4. **Iniciar o Projeto**

Depois de configurar tudo, inicie o projeto com o comando:

```bash
mvn spring-boot:run
```

Ao acessar a interface do Swagger UI (geralmente disponível em `http://localhost:8080/swagger-ui.html`), você verá a interface personalizada com o tema modificado.

## **Resultado Final**

Após a aplicação do tema, o Swagger UI será exibido com o visual personalizado, conforme as modificações feitas no CSS.

`Antes:`
![Swagger sem tema modificado](https://github.com/user-attachments/assets/eecb5d45-a8f3-4855-bb93-150f6e55d6f1)


`Depois:`
![Swagger com tema modificado](https://github.com/user-attachments/assets/6af729f4-4742-4695-9756-0d46e3fb874e)
