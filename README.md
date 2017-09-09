# AvailForms Exemplo
Este projeto tem como objetivo demonstrar a utilização do Framework [AvailFormsGeneretor](https://github.com/leogomes26/AvailFormsGenerator.git) e [AvailFormsAngular](https://github.com/leogomes26/AvailFormsAngular.git).

# Instruções:

- Realizar clone do projeto:
    ```sh
   git clone https://github.com/leogomes26/AvailFormsExemplo.git
    ```
# BackEnd
Pré-requisitos:
- MySql Instalado.
- Criar banco de dados com o nome exemplo_avail_forms
- Importar projeto Back-End/exemplo-avail-forms como projeto maven no eclipse.
- Alterar as propriedades spring.datasource.username= e spring.datasource.password=
para as configurações de seu banco de dados.
- Executar SQLs do arquivo dados.sql disponivel em arquivos_projeto.
- Startar o projeto.

# FrontEnd
Pré-requisitos:
- Npm Instalado.
- Ir na pasta Front-End/ e executar os comandos abaixo, recomendo executar um por vez.
  ```sh
    - npm install
    - gulp copy-AvailFormsAngular
    - gulp
  ```

Pronto, se tudo ocorreu bem o projeto será startado na porta 8081.

Dúvidas ou sugestões: leonardogomesdev@gmail.com

## Licença
## MIT


