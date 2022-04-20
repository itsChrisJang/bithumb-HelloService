# bithumb-HelloService

# REST API에 반응하는 local 웹 서버 앱 구축

## Spec

1. Java 11
2. SpringBoot 2.66
3. WebFlux
4. R2dbc
5. Functional Endpoint

## HelloService Server Request

`GET localhost:8080/hello?name=$name`

## SayHello Server Response

`json
{
    "to": "$name", 
    "job": "Back-End Developer",
    "message": "hello $name"
}
`

## InfoService Server Request

`GET localhost:8081/info-service/job?name=$name`

## InfoService Server Response

`json
{ 
    "job": "Back-End Developer"
}
`
