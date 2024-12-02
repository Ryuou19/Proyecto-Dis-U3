
# Guía de ejecución

## Requisitos Previos

Tener instalado Node.js y NPM en tu sistema. Puedes verificarlo ejecutando los siguientes comandos en tu terminal:

    node -v
    npm -v`
 

Si no los tienes instalados, descárgalos e instálalos desde [Node.js](https://nodejs.org/).

## Instalación de Angular CLI

Instala Angular (17) CLI globalmente en tu sistema usando el siguiente comando:

    npm install -g @angular/cli

## Instalación de Dependencias

Navega al directorio de tu proyecto Angular y ejecuta el comando para instalar todas las dependencias. Dependiendo de la velocidad de tu conexión podría tardar varios minutos.

    npm install 

## Ejecución de la Aplicación

Para iniciar el servidor de desarrollo, ejecuta:

    ng serve


La aplicación estará disponible en `http://localhost:4200/`. Puedes ingresar escribiendo esa URL en tu navegador.

## Creación de Componentes

Para crear un nuevo componente, usa el siguiente comando:

    ng generate component nombre-del-componente

 
