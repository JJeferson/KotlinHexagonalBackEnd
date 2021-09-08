# KotlinHexagonalBackEnd
Projeto para teste de arquitetura hexagonal com Kotlin. </br> 
Nele implemento testes com relatório de cobertura , </br>
autenticação via Interceptor . </br>
O Projeto será um cadastro de Clientes com objetos separados para </br>
os endereços e contatos. 
</br>

Requisitos: </br>
*MongoDB </br>
*Java11 </br>
*Maven </br>


<h3> Segue os exemplo de curl das funções do microsserviço</h3>
</br>
[POST] </br>
curl --location --request POST 'http://localhost:8080/api/novo_cliente' \
--header 'Authorization: MTIzNjU0Nzg5' \
--header 'Content-Type: application/json' \
--data-raw '{
"nome":"teddy teste",
"idade":33,
"contatos":[
{"fone":"9997712985"},
{"fone":"9997712985"}
],
"enderecos":[
{"rua":"rua teste",
"numero":123,
"tipoMoradia":"A"}
]
}'
</br></br>
[GET] - FindAll </br>
curl --location --request GET 'http://localhost:8080/api/todos' \
--header 'Authorization: MTIzNjU0Nzg5' \
--data-raw ''

</br></br>
[GET] - FindByID </br>
curl --location --request GET 'http://localhost:8080/api/findbyid?id=61329ed4d966a6130dc717d4' \
--header 'Authorization: MTIzNjU0Nzg5' \
--data-raw ''
</br></br>
[GET] - FindByNome </br>
curl --location --request GET 'http://localhost:8080/api/lista_cliente_por_nome?nome=t' \
--header 'Authorization: MTIzNjU0Nzg5' \
--data-raw ''
</br>
