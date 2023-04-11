#language: pt
Funcionalidade: Consultar endereço passando CEP.
    Funcionalidade para testar a aplicação, buscando um determinado endereço, 
    enviando para consulta um valor de CEP.

####################### CEP sem máscara ##########################
Cenario: Buscar endereço informado um valor de cep sem máscara

Dado o determinado cep
    | cep       |              
    | 01001000  |

Quando cep 01001000 é passado para buscar endereço

Então o endereço com cep, estado e frete é retornado
    | cep       | estado | frete |   cidade  |                   
    | 01001-000 | SP     | 7.85  | São Paulo |

##################################################################

####################### CEP com máscara ##########################
Cenario: Buscar endereço informado um valor de cep com máscara

Dado o determinado cep
    | cep       |              
    | 01001-000 |

Quando cep 01001-000 é passado para buscar endereço

Então o endereço com cep, estado e frete é retornado
    | cep       | estado | frete |   cidade  |                     
    | 01001-000 | SP     | 7.85  | São Paulo |

##################################################################

#################### CEP Região Centro-Oeste #####################
Cenario: Retornar valor de frete da região Centro-Oeste dado um 
cep do estado de MT

Dado o determinado cep 
    | cep       |              
    | 78005-904 |

Quando cep 78005-904 é passado para buscar endereço

Então o endereço com cep, estado e frete é retornado
    | cep       | estado |  frete  | cidade |                    
    | 78005-904 |   MT   |  12.50  | Cuiabá |

##################################################################

#################### CEP Região Nordeste #####################
Cenario: Retornar valor de frete da região Nordeste dado um 
cep do estado de PB

Dado o determinado cep 
    | cep       |              
    | 58050-003 |

Quando cep 78005-904 é passado para buscar endereço

Então o endereço com cep, estado e frete é retornado
    | cep       | estado |  frete  |    cidade   |                   
    | 58050-003 |   PB   |  15.98  | João Pessoa |

##################################################################

######################## CEP Região Norte ########################
Cenario: Retornar valor de frete da região Norte dado um 
cep do estado de PA

Dado o determinado cep 
    | cep       |              
    | 66035-110 |

Quando cep 78005-904 é passado para buscar endereço

Então o endereço com cep, estado e frete é retornado
    | cep       | estado |  frete  | cidade |                    
    | 66035-110 |   PA   |  20.83  | Belém  |

##################################################################

####################### CEP Região Sudeste #######################
Cenario: Retornar valor de frete da região Sudeste dado um 
cep do estado de RJ

Dado o determinado cep 
    | cep       |              
    | 20260-300 |

Quando cep 78005-904 é passado para buscar endereço

Então o endereço com cep, estado e frete é retornado
    | cep       | estado | frete |     cidade     |                    
    | 20260-300 |   RJ   | 7.85  | Rio de Janeiro |

##################################################################

######################### CEP Região Sul #########################
Cenario: Retornar valor de frete da região Sul dado um 
cep do estado de PR

Dado o determinado cep 
    | cep       |              
    | 84010-100 |

Quando cep 78005-904 é passado para buscar endereço

Então o endereço com cep, estado e frete é retornado
    | cep       | estado |  frete  |    cidade    |                   
    | 84010-100 |   PR   |  17.30  | Ponta Grossa |

##################################################################