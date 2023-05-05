# APIPagamentos

API que recebe e atualiza pagamentos, seguindo a regra de negócio apresentada.

* Exemplos de POST utilizando o endereço: localhost:8080/pagamentos

Com "Boleto" como método de Pagamento:

{
	"documento": "12332122",
	"metodoPagamento": "Boleto",
	"valorPagamento" : 3150.32
}

Com "cartao_credito" como método de Pagamento:

{
	"documento": "12332122",
	"metodoPagamento": "cartao_credito",
	"numeroCartao" : "321",
	"valorPagamento" : 3150.32
}

![image](https://user-images.githubusercontent.com/101297712/236374230-676d278e-c501-49af-8606-c87c8eeb80f0.png)


* Exemplos de GET utilizando o endereço: localhost:8080/pagamentos

![image](https://user-images.githubusercontent.com/101297712/236373900-1e9e781c-5c41-40fa-937a-aea8e9e8b04a.png)

* Exemplos de PUT utilizando o endereço: localhost:8080/pagamentos/4

Necessário passar o ID para update

![image](https://user-images.githubusercontent.com/101297712/236373974-790210d4-7e8b-4c67-a183-062dd70f47ec.png)

*Exemplo de DELETE utilizando o endereço: localhost:8080/pagamentos/1

![image](https://user-images.githubusercontent.com/101297712/236374144-72a83896-5f68-43d5-9d61-c6fec4b55bcd.png)





