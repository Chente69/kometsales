# Kontex Chat - Kometsales Project

## Date
Tue Aug 11 2026

## Project Overview
- **Directory**: `C:\localData\chatgptws\projects\kometsales`
- **Type**: Maven project - Spring Boot 2.7.10 REST API (Java 11)
- **Purpose**: Technical test for a senior Java fullstack developer position
- **Description**: Application to query product inventories with GET RESTful Web Service methods

## Project Structure

```
kometsales/
├─ .git/                  Git repo metadata
├─ .vs/                   Visual Studio settings
├─ README.md              Tech-test description (Spanish)
└─ pricerestapi/          MAIN Spring Boot Maven module
   ├─ pom.xml             Spring Boot 2.7.10, Java 11; deps: Data JPA, Web, H2, ModelMapper, test
   ├─ .gitignore, .idea/, .mvn/
   ├─ mvnw, mvnw.cmd      Maven wrapper
   ├─ target/             Build output
   └─ src/
      ├─ main/
      │  ├─ resources/application.properties   H2 config
      │  └─ java/co/micia/projects/restapi/pricelist/
      │     ├─ PriceListApplication.java       Main entry (Spring Boot)
      │     ├─ ApplicationConfig.java          ModelMapper bean config
      │     ├─ integrations/           DTOs returned by HTTP endpoints (4: CompanyInventoryDTO, CodigoProductoDTO, ProductCompanyDTO, ProductInventoryDTO)
      │     ├─ dalbusiness/            Services, utility logic, exceptions
      │     │    services/  ProductUtilityServiceImpl + ProductUtilityService interface (business logic)
      │     │    utilities/ ProductPriceUtility  (price/freight calc)
      │     │    exceptions/ ResourceNotFoundException
      │     ├─ daljpa/
      │     │    controllers/ ProductUtilityController  (3 GET endpoints)
      │     │    repositories/ (CustomerRepository, CompanyRepository, BoxTypeResository, InventoryRepository, ProductRepository)
      │     │    model/  JPA entities: TBLCompanyPT, TBLCustomerPT, TBLBoxTypePT, TBLinventoryPT, TBLProductPT
      └─ test/
         ├─ PriceTestConfiguration.java
         ├─ ApplicationConfigTest.java
         └─ daljpa/controller/PriceControllerTest.java
```

## REST Endpoints (from README.md)
Use `http://localhost:8080` with HTTP GET method:

1. `GET /api/products/companies/{idCompania}` - Query all products by company ID, calculating Final Freight of returned products
2. `GET /api/products/customers/{idCustomer}` - Query by customer ID, calculating the price of returned products
3. `GET /api/products/companies/{idCompania}/codes` - Query by company ID, calculating product codes of returned products

## Key Technical Details
- **Database**: H2 relational database (in-memory, no installation required, built into Java)
- **Persistence entities**: defined in `co.micia.projects.restapi.pricelist.daljpa.model` - define tables and relationships per supplied model
- **Data objects returned by HTTP requests**: defined in `co.micia.projects.restapi.pricelist.integrations`
- **Build/run command**: `mvn spring-boot:run`
- **Testing tool**: Postman suggested for accessing the web service endpoints

## Notes
- Project path uses a slightly different package group (`co.mch.projects.restapi`) in pom.xml vs source packages (`co.micia.projects.restapi.pricelist`)
- Docs mention `mvn spring-boot:run` in README

## Build & Test Results
- **Build**: SUCCESS (clean compile, JDK 21 at `C:\Program Files\Java\latest\jdk-21`)
- **Tests**: 3/3 passed (0 failures, 0 errors, 0 skipped)
- **Total build time**: ~9-20 seconds
- **Maven wrapper**: Used `mvnw.cmd` (mvn not installed system-wide)
- **Test data added** to `PriceTestConfiguration.java`:
  - 3 new products (ids 6-8)
  - 2 new companies (ids 6-7)
  - 2 new box types (ids 6-7)
  - 2 new inventory records (ids 6-7)
  - 2 new customers (ids 6-7)

## Current Status
- Waiting for new instructions. Context saved.