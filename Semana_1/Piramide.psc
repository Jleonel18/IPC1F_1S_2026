Algoritmo Piramide
	
	Definir niveles, i, j Como Entero;
	Definir espacios, asteriscos Como Cadena;
	
	Escribir "Ingrese el número de niveles:";
	Leer niveles;
	
	Para i <- 1 Hasta niveles Con paso 1 Hacer
		
		espacios <- "";
		asteriscos <- "";
		
		// Generar espacios
		Para j <- 1 Hasta niveles - i Con Paso 1 Hacer
			espacios <- espacios + " ";
		Fin Para
		
		// Generar asteriscos
		Para j <- 1 Hasta (2*i - 1) Con Paso 1 Hacer
			asteriscos <- asteriscos + "*";
		Fin Para
		
		Escribir espacios, asteriscos;
		
	Fin Para
	
FinAlgoritmo
