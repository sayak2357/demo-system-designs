APIS:

1) Get Tiny Url

request:
curl --location 'http://localhost:8080/api/getTinyUrl' \
--header 'Content-Type: application/json' \
--data '{
    "type": "LONG",
    "url": "github.com/sayak2357"
}'

response:
{
    "url": "mmnnzzyn",
    "type": "SHORT"
}



2) Get Long Url

request:
curl --location --request GET 'http://localhost:8080/api/getLongUrl/mmnnzzyn' \
--header 'Content-Type: application/json' \
--data '{
    "type": "LONG",
    "url": "www.google.co.in"
}'

response:
{
    "url": "github.com/sayak2357",
    "type": "LONG"
}



Working:
1) Get a string in request body from getTinyUrl api.
2) Get MD5 hash of the input string and fetch the first 8 bytes.
3) Use this 8 character string as the short-url for the given data.
4) Collision is resolved by incresing the size of short-url by 1 character every time.
5) It supports 64 cahracters which are: A-Z,a-z,0-9 and '#','!'


Performance:
# 8 characters each having 64 options. In other words 64^8 shortUrls supported with length before it's increased to 9.
# With 1000 requests per second, takes more than 9000 years for exhausting all 8 chars long short-urls.
