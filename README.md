# Cobertura de vertices
## Trabalho - Projeto e análide de algoritmos (PAA)

### Autores:
- RICHARDY RODRIGUES TANURE - 22.2.8003
- MAYKE ANSELMO BRITO LELLIS - 22.2.8008

### Geração de instâncias
#### Compilação do código
```
javac gerarinstancias.java
```

#### Rodando o código

```
java gerarinstancias.java -n 1000 -d 0.5 -k 10 -nome "meu grafo"
```

-n: número de vértices

-d: densidade relativa

-k: cobertura de vertices mínima

-nome: nome do grafo que será salvo

### Resolvendo a instância
#### Compilação do código
```
javac resolverinstancia.java
```

#### Rodando o código

```
java resolverinstancia.java "meu grafo" -r
```

- 1º arg - Nome do grafo
- -r: mostrar os vértices que fazem parte da cobertura mínima