# Guía de ejecución

## Requisitos Previos
### Herramientas Necesarias
1. **IntelliJ IDEA**: Se recomienda usar IntelliJ IDEA para gestionar y ejecutar el proyecto. Puedes descargarlo desde [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).
2. **Java 17**: Asegúrate de tener instalado JDK 17. Puedes verificarlo ejecutando el siguiente comando en tu terminal:
    ```bash
    java -version
    ```

### Instalación de IntelliJ IDEA y Java 17
- **IntelliJ IDEA**: Sigue las instrucciones de instalación proporcionadas en el sitio web de JetBrains.
- **Java 17**: Descárgalo e instálalo desde [Oracle JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) o usa [OpenJDK 17](https://jdk.java.net/17/).

## Instalación de Dependencias
1. **Abrir el archivo `build.gradle`**: IntelliJ detectará automáticamente las dependencias y las descargará.
2. **Sincronizar Proyecto**: Si no se sincroniza automáticamente, haz clic en el ícono de sincronización en la barra de herramientas de Gradle en IntelliJ.

## Ejecución de la Aplicación
1. **Abrir el archivo principal**: Navega al archivo principal que contiene el método `main`.
2. **Ejecutar la Aplicación**: Haz clic derecho en el archivo y selecciona `Run 'NombreDelProyecto'`.

## Ejecución del Proyecto desde la Línea de Comandos

1. **Navegar al Directorio del Proyecto**:
    ```bash
    cd /ruta/completa/del/proyecto
    ```
2. **Ejecutar el Proyecto**:
    ```bash
    ./gradlew bootRun
    ```
   o en Windows:
    ```bash
    gradlew.bat bootRun
    ```
Java y Gradle deben estár instalados para poder ejecutarlos.