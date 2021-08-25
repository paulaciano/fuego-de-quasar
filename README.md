# fuego-de-quasar by Paula

Se puede ejecutar el programa con los siguientes datos: 

* Api Url:  https://geyc73n98h.execute-api.us-east-2.amazonaws.com/default/topsecret
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

Los endpoints pedidos en el nivel 3 son: 
* Api Url: https://geyc73n98h.execute-api.us-east-2.amazonaws.com/default/topsecret_split/{nombre de la nave}
* Método: POST
* Body: 
{
    "distance": 143.7,
    "message": [ "este", "", "un", "", ""]
}

* Api Url: https://geyc73n98h.execute-api.us-east-2.amazonaws.com/default/topsecret_split
* Método: GET

Como las posiciones están hardcodeadas para los tres satélites del ejemplo, deben estar esas tres guardadas para que funcione todo el proceso. A medida que se cargan nuevas distancias y mensajes se pisan sobre el mismo nombre de satélite. Se pueden cargar otras naves con otros nombres y quedan guardadas, pero no se usan para encontrar la ubicación de la nave emisora del mensaje.

<b>Notas</b>
* Para hallar las coordenadas de la nave emisora del mensaje utilicé una librería que resuelve problemas de trilateración. Siempre encuentra una respuesta con ciertos parámetros de proximidad que no tuve en cuenta en mi algoritmo. Dejo el link a la librería: https://github.com/lemmingapex/trilateration
* Los servicios de AWS que utilicé fueron: tres lambdas, una base de datos dynamo y un API Gateway.
* El lambda que se ejecuta en el GET del topsecret_split, recupera los datos de la base y arma el request para el lambda que busca la posición y el mensaje en el endpoint /topsecret
