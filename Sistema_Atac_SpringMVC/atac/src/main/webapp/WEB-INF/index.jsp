<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<!-- ${pageContext.request.contextPath} = contexto atual da requisição, url absoluta -->

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrossel.css">
  </head>
  <body>

   <nav class="nav_menu">
        <ul>
            <li>
              <a class="current_page" href="${pageContext.request.contextPath}/">Atac</a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/login">Login</a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/cadastro">Cadastro</a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/loginADM">ADM</a>
            </li>
        </ul>
    </nav>

  <!--ideia de latout para mostrar os serviços e funcionalidades do sistema em forma de cards-->
    <main class="content_layout">

      <h1 class="title">Sistema de estoque da Atac</h1>

      <div class="carrossel-container" id="carrossel">
        <div class="slide fade">
          <img src="${pageContext.request.contextPath}/img/service1.jpg" alt="img1">
        </div>
        <div class="slide fade">
          <img src="${pageContext.request.contextPath}/img/service2.jpg" alt="img1">
        </div>
        <div class="slide fade">
          <img src="${pageContext.request.contextPath}/img/service3.jpg" alt="img1">
        </div>
        <div class="slide fade">
          <img src="${pageContext.request.contextPath}/img/service4.jpg" alt="img1">
        </div>
        <a class="prev">&#10094;</a>
        <a class="next">&#10095;</a>
      </div>
    </main>

    <script>
        document.addEventListener("DOMContentLoaded", () => {
          const carrossel = document.getElementById('carrossel');
          let slideIndex = 0;

          const showSlides = () => {
            const slides = carrossel.querySelectorAll(".slide");
            slides.forEach(slide => slide.style.display = "none");
              slideIndex++;
              if (slideIndex > slides.length) slideIndex = 1;
              slides[slideIndex - 1].style.display = "block";
              setTimeout(showSlides, 4000); // troca automática a cada 4s
          };
          showSlides();

          // controle manual
          document.querySelector('.prev').addEventListener('click', () => changeSlide(-1));
          document.querySelector('.next').addEventListener('click', () => changeSlide(1));

          function changeSlide(n) {
            const slides = document.querySelectorAll(".slide");
            slides.forEach(slide => slide.style.display = "none");
            slideIndex += n;
            if (slideIndex > slides.length) slideIndex = 1;
            if (slideIndex < 1) slideIndex = slides.length;
            slides[slideIndex - 1].style.display = "block";
        }
        });
    </script>
  </body>
</html>
