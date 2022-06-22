# fuego-de-quasar by Paula

You can use the API using the following data: 

* Api Url:  https://geyc73n98h.execute-api.us-east-2.amazonaws.com/default/topsecret <br>
* Method: POST
* Body:
{
  "satellites": [
    {
      "name": "kenobi",
      "distance": 100.0,
      "message": [ "this", "", "", "secret", "" ]
    },
    {
      "name": "skywalker",
      "distance": 115.5,
      "message": [ "", "is", "", "", "message" ]
    },
    {
      "name": "sato",
      "distance": 142.7,
      "message": [ "this", "", "a", "", "" ]
    }
  ]
}

In order to get a 404, it is required to delete any of the words of the messages. It always gives an answer, I could not make it fail by not finding the coordinates.

<b>Notes</b>
* In order to find the transmission coordinates, I use a library (com.lemmingapex.trilateration) to solve trilateration problems. 
* It always finds an answer with a centroid and an error (that I have just ignored in my solution). Library: https://github.com/lemmingapex/trilateration
* I needed an AWS lambda and an API Gateway.
* 
* There is a doc with screenshots of the API configuration.