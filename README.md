# Weather API

Challenge basado en: [Weather API](https://roadmap.sh/projects/weather-api-wrapper-service)
Una API que obtiene información meteorológica de un servicio de terceros y utiliza Redis para
almacenar los datos en caché, mejorando así la eficiencia y reduciendo las solicitudes repetitivas.

##Características

* **Consulta de Clima por Código de Ciudad**: Obtén información meteorológica actualizada para
  cualquier ciudad ingresando su código.
* **Almacenamiento en Caché**: Los resultados se almacenan en Redis con una caducidad de 12 horas
  para reducir la carga en la API externa.
* **Variables de Entorno**: Uso de variables de entorno para configurar la clave de la API y la
  conexión a Redis.
* **Manejo de Errores**: Respuestas claras en caso de errores de conexión o entradas inválidas.

## Requisitos Previos

* Clave de API de Visual Crossing (o el servicio de clima que elijas).
* Redis configurado y en ejecución.
* Configuración de variables de entorno.