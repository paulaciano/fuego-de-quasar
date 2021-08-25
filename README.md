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
Como las coordenadas conocidas de los satélites están preseteadas, es importante ingresarlos en el mismo orden del ejemplo. Se matchean en ese orden con su ubicación hardcodeada.

<b>Notas</b>
* Para hallar las coordenadas de la nave emisora del mensaje utilicé una librería que resuelve problemas de trilateración. Siempre encuentra una respuesta con ciertos parámetros de proximidad que no tuve en cuenta en mi algoritmo. Dejo el link a la librería: https://github.com/lemmingapex/trilateration
* Utilicé un lambda de AWS y un API Gateway.
* Adjunto un pdf con capturas de la API y su configuración, en la carpeta resources del proyecto
