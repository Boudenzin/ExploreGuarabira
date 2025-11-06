# 🏙️ ExploreGuarabira - Seu Guia Urbano em Compose

ExploreGuarabira é um projeto de aplicativo Android desenvolvido com **Kotlin** e **Jetpack Compose**. Ele foi concebido como um guia interativo e responsivo, focado em ajudar o usuário a descobrir e explorar os principais pontos de interesse da cidade de Guarabira, desde opções gastronômicas até centros de lazer e esportes.

Este projeto visa aplicar e consolidar os conhecimentos de navegação, estado e design de interface modernos com o *declarative UI* do Compose.

## 🚀 Funcionalidades Principais

O aplicativo é estruturado para oferecer uma experiência de descoberta fluida e organizada:

### 1\. Navegação por Categorias

A tela inicial funciona como um *dashboard* com ícones de acesso rápido que direcionam o usuário para as seguintes categorias:

* ☕ **Cafés**
* 🍽️ **Restaurantes**
* 🫓 **Tapiocarias**
* ⚽ **Centros Esportivos**
* 🛍️ **Shopping Centers**

### 2\. Listagem e Pesquisa

Em cada tela de categoria (ex: Cafés), é exibida uma lista completa de todos os locais cadastrados na cidade.

* A lista inclui uma **barra de pesquisa** eficiente para filtrar os resultados rapidamente.
* Os itens são apresentados em cards interativos.

### 3\. Detalhes do Local (Pop-up/Modal)

Ao clicar em qualquer card da lista (ex: "Café Aroma"), o usuário é apresentado a um pop-up ou modal (sheet) com um resumo detalhado e informações cruciais sobre o local:

* ⭐ **Avaliações**
* 📍 **Localização**
* ℹ️ **Especialidade ou "Pelo que o local é conhecido"** (por exemplo, "Melhor Cappuccino da Cidade").

## 🛠️ Stack Tecnológico

* **Linguagem:** Kotlin
* **UI Toolkit:** Jetpack Compose
* **Ferramenta de Design:** Figma (para o modelo de interface)
* **Arquitetura:** *(Sugestão: Adicionar aqui se for usar MVVM, MVI ou outra.)*

## 🧑‍💻 Como Rodar

1.  Clone o repositório:
    ```bash
    git clone https://github.com/Boudenzin/ExploreGuarabira.git
    ```
2.  Abra o projeto no Android Studio.
3.  Sincronize o projeto (Gradle Sync).
4.  Execute em um emulador ou dispositivo físico com Android 5.0 (API 21) ou superior.

---

## 🐞 Issues

Encontrou algum problema ou tem sugestões?
Abra uma **issue** no repositório! Sua contribuição é muito bem-vinda.

---

## 📜 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.