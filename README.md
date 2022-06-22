# fuego-de-quasar by Paula

You can test the API using the following data: 

* Api Url:  https://geyc73n98h.execute-api.us-east-2.amazonaws.com/default/topsecret
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

To get a 404, one of the words should be replaced by a "". I could not make it failed with a "No location found", it is always found.
Considering all the satellites locations are hardcoded, it is required to set them in the order you see in the example. They are matched in that order.

The endpoints of the level 3 are:
* Api Url: https://geyc73n98h.execute-api.us-east-2.amazonaws.com/default/topsecret_split/{nombre_de_la_nave}
* Method: POST
* Body: 
{
    "distance": 143.7,
    "message": [ "this", "", "a", "", ""]
}
<br><br>
* Api Url: https://geyc73n98h.execute-api.us-east-2.amazonaws.com/default/topsecret_split
* Method: GET

Considering the location of the three satellites from the example  are hardcoded, they have to be stored in order for the process to work properly. 
When new distances and messages are added, they overwrite the existing data for that satellite.
More satellites with different names can be added, they will be stored but they will never be considered when looking for the transmission location.


<b>Notes</b>
* In order to find the transmission location I used a library that solves trilateration problems. It always finds an answer with an error that I have ignored in my procedure. This is the library location: https://github.com/lemmingapex/trilateration
* I used three AWS lambdas, a Dynamo db and an API Gateway.
* The lambda that runs the topsecret_split GET, gets the data from the dynamo db and builds the request that looks for the location and message in the /topsecret endpoint.
