# airline-reservation-system

Airline Reservation System


# SISTEMI CALISTIRMAK
Calistirilacak ortamda Docker yüklü olması gerekmektedir.
Eğer Docker macOS platformunda çalışıyor ise Docker Enginenin en az 4 gb memory limitinin olduğuna emin olunuz.
Proje klasoru icerisinde docker-compose up komutu sistemi ayağa kaldırmaya yetmektedir.
Herhangi bir ekstra konfigürasyona ihtiyac yoktur. 

# API BLUEPRINT
## Flight-Management-Service-Airline [localhost:4567][/airline]
### Add Airline [POST]

+ airlineName (required, string) - Airline Name(id)

+ Request (application/json;charset=utf-8)

    + Body

            {
                "airlineName":"PEGASUS",
            }

+ Response 204 (application/json;charset=utf-8)

### Update Airline [PUT]

+ id          (required,string)  - Id
+ airlineName (required, string) - Airline Name

+ Request (application/json;charset=utf-8)

    + Body

            {   
                "Id": "Pegasus"
                "airlineName":"PEGASUS",
            }

+ Response 204 (application/json;charset=utf-8)

### Delete Airline [GET][/{id}]

+ Parameters
    + id: "PEGASUS" (required, string) - Airline id

+ Response 204 (application/json;charset=utf-8)

### Get All Airlines - [GET]

+ Response 200 (application/json;charset=utf-8)

        {
            "airline": "Pegasus",
            "flightList":[]
        }

## Flight-Management-Service-Airport [/airport]
### Add Airport [POST]      

+ airportCode (required, string) - Airport Code(id)
+ airportName (required,string)  - Airport Name
+ country     (required,string)  - Country

+ Request (application/json;charset=utf-8)

    + Body

            {
                "airportCode": "SAW",
                "airportName": "Sabiha Gökçen Havalimanı"
                "country": "Turkey"
            }

+ Response 204 (application/json;charset=utf-8)

### Update Airport [PUT]

+ airportCode (required, string) - Airport Code(id)
+ airportName (required,string)  - Airport Name
+ country     (required,string)  - Country

+ Request (application/json;charset=utf-8)

    + Body

            {
                "airportCode": "SAW",
                "airportName": "Sabiha Gökçen Havalimanı"
                "country": "Turkey"
            }

+ Response 204 (application/json;charset=utf-8)

### Delete Airpot [DELETE][/{id}]

+ Parameters
    + id: "SAW" (required, string) - Airport id

+ Response 204 (application/json;charset=utf-8)


### Get All Airports [GET]

+ Response 200 (application/json;charset=utf-8)

        {
            "code": "SAW",
            "airportName": "Sabiha Gökçen Havalimanı"
            "country": "Turkey"
        }

## Flight-Management-Service-Route [/route]
### Add Route [POST]      

+ departureAirportCode (required, string) - Departure Airport Code
+ arrivalAirportCode (required,string)  - Arrival Airport Code

+ Request (application/json;charset=utf-8)

    + Body

            {
                "departureAirportCode": "SAW",
                "arrivalAirportCode": "ATY"
            }

+ Response 204 (application/json;charset=utf-8)

### Delete Route [DELETE][/{id}]

+ Parameters
    + id: 1 (required, long) - Route id

+ Response 204 (application/json;charset=utf-8)

### Get All Routes [GET]

+ Response 200 (application/json;charset=utf-8)

        {
            "id": 1
        }

## Flight-Management-Service-Airport [/flight]
### Add Flight [POST]      

+ airlineId (required, string) - Airline id
+ routeId   (required,long)    - Route id
+ seatCount   (required,int)  - Capacity
+ departureAirportId   (required,long)  - Departure Airport Id
+ arrivalAirportId   (required,int)  - Arrival Airport Id
+ ticketPrice   (required,number)  - Ticket Price
+ departureDate   (required,object)  - Departure Date


+ Request (application/json;charset=utf-8)

    + Body

            {
                 "airlineId": "THY",
                 "routeId": 3,
                "seatCount": 120,
                "departureAirportId": 1,
                "arrivalAirportId": 2,
                "flightDate": null,
                "ticketPrice": 600,
                "departureDate": {
                                    "day": 1,
                                    "month": 2,
                                    "year": 2020,
                                    "hour": 5,
                                    "minute": 30
                                 }                               
            }

+ Response 204 (application/json;charset=utf-8)    

### Get Flights By Airline [GET][/airline/{id}]

+ Parameters
    + id: "THY" (required, string) - Airline id

+ Response 200 (application/json;charset=utf-8)

        [
    {
        "id": 3,
        "availableSeatCount": 120,
        "capacity": 120,
        "departureTime": "2020-02-01T05:30:00",
        "ticketPrice": 600.00,
        "created": "2020-07-27T04:23:49.709925"
    },
    {
        "id": 1,
        "availableSeatCount": 119,
        "capacity": 120,
        "departureTime": "2020-02-01T05:30:00",
        "ticketPrice": 600.00,
        "created": "2020-07-27T04:23:43.023479"
    },
    {
        "id": 2,
        "availableSeatCount": 120,
        "capacity": 120,
        "departureTime": "2020-02-01T05:30:00",
        "ticketPrice": 600.00,
        "created": "2020-07-27T04:23:45.928225"
    }
]

## Derleme

### Build

```
./gradlew clean build
```

### Birim Testleri çalıştırmak

```
./gradlew clean test
```

## Çalıştırma

Projeyi derlemek için

```
./gradlew clean bootJar
```

```
