#language: pt
Funcionalidade: Retorno de mesagem para CEP inexistente.
    Funcionalidade para validar o retorno da mensagem ao 
    informar um CEP não existente

####################### CEP inexistente ##########################
Cenario: Buscar endereço informado um valor de cep inválido

Dado o determinado cep inexistente
    |    cep    |              
    | 00000-000 |

Então a mensagem de cep não encontrado deve ser retornada
    | mensagem                                            |                     
    | Nenhum endereço encontrado para o CEP [ 00000-000 ] | 

##################################################################