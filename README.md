# fuego-de-quasar by Paula

Se puede ejecutar el programa con los siguientes datos: 

* Api Url:  https://geyc73n98h.execute-api.us-east-2.amazonaws.com/default/topsecret <br>
* Método: POST
* Body:
{
  "satellites": [
    {
      "name": "kenobi",
      "distance": 100.0,
      "message": [ "este", "", "", "mensaje", "" ]
    },
    {
      "name": "skywalker",
      "distance": 115.5,
      "message": [ "", "es", "", "", "secreto" ]
    },
    {
      "name": "sato",
      "distance": 142.7,
      "message": [ "este", "", "un", "", "" ]
    }
  ]
}

Para obtener el 404 es necesario "borrar" alguna de las palabras de los mensajes. No logré que falle por no encontrar coordenadas, las encuentra siempre.

<b>Notas</b>
* Para hallar las coordenadas de la nave emisora del mensaje utilicé una librería que resuelve problemas de trilateración. Siempre encuentra una respuesta con ciertos parámetros de proximidad que no tuve en cuenta en mi algoritmo. Dejo el link a la librería: https://github.com/lemmingapex/trilateration
* Utilicé un lambda de AWS y un API Gateway.
* No llegué a hacer lo que pedía el nivel 3. 
* No logré hacer andar bien el logger así que lo saqué. Había usado algo así: private static final Logger log = LoggerFactory.getLogger(ClaseALaQuePertenecía.class);
* Adjunto un pdf con capturas de la API y su configuración, en la carpeta resources del proyecto
