# kometsales
Prueba Technica Kametsale

Evaluación técnica para la solución para el puesto de desarrollador senior Java fullstack

Aplicación Spring Boot para consultar los inventarios de productos con métodos de servicios GET de  RESTFUL WS:
1. Por id de compañia, calculando el Final Freight de productos devueltos
2. Por id de Customer , calculando el precio de productos devueltos
3. Por id de compañia calculado código de productos devueltos



Para ejecutar la aplicación, el usuario debe compilar el proyecto maven que contiene las fuentes del código mediante el comando: mvn spring-boot:run

Una vez que la aplicación se está ejecutando, puede usar una herramienta como POSMAN para acceder a los puntos de entrada de los servicios web implementados, como se muestra a continuación:

use esta URL y HTTP GET METHOD para consultar todos: 
1.http://localhost:8080/api/products/companies/{idCompania}
2. http://localhost:8080/api/products/customers/{idCustomer}
3. http://localhost:8080/api/products/companies/{idCompania}/codes

NOtas:

Se empleo la Base de datos realcional H2 la cual permite almacenamiento en memoria 
no requiere instalación es propia de Java
Los Objetos de persistemcia de Entidades definidas en el modulo
co.micia.projects.restapi.pricelist.daljpa.model
Definen las tablas y las relaciones de acuerdo al modelo suministradp

Los Objeto de Datos devueltos por las peticiones HTTP se definen en el 
co.micia.projects.restapi.pricelist.integrations



	

