# Enviro365 Waste Sorting REST API Documentation

This documentation provides instructions on setting up, testing, understanding the architecture,
and using the ORM models for the Enviro365 Waste Sorting REST API. This API provides functionalities 
to manage waste categories, disposal guidelines, and recycling tips within the Enviro365 Waste Sorting 
mobile application. The system helps users to make informed decisions about waste management, guiding 
them on proper disposal methods and promoting recycling efforts.
## Project Setup
Follow these steps to set up the project locally:
1. Clone the Repository 
Clone the repository from the GitHub repository link.
```bash
git clone https://github.com/enviro365/waste-sorting-api.git
cd waste-sorting-api

```
2. Install Dependencies

Ensure you have Java 17 or a higher version installed. Additionally, you'll need Maven to manage dependencies.

Run the following command to install the necessary dependencies:
```bash
mvn clean install
```

3. Run the Application

Start the Spring Boot application:

```bash 
mvn spring-boot:run
```

The application should now be running locally on http://localhost:8080.

## API Endpoints
### Waste Categories
#### 1. GET /api/categories

   Description: Retrieves all waste categories.
   Response: 200 OK with a list of waste categories.

#### 2. GET /api/categories/{id}

   Description: Retrieves details of a specific waste category by its id.
   Response: 200 OK with waste category details.

#### 3. POST /api/categories

   Description: Adds a new waste category.
   Request Body: JSON with name and description.
   Response: 201 Created with the created waste category.

#### 4. PUT /api/categories/{id}

   Description: Updates an existing waste category.
   Request Body: JSON with name and description.
   Response: 200 OK with updated category details.

#### 5. DELETE /api/categories/{id}

   Description: Deletes a waste category by its id.
   Response: 200 OK if deletion is successful.

### Disposal Guidelines
#### 1. GET /api/guidelines

   Description: Retrieves all disposal guidelines.
   Response: 200 OK with a list of disposal guidelines.

#### 2. GET /api/guidelines/category/{categoryId}

   Description: Retrieves disposal guidelines for a specific waste category.
   Response: 200 OK with disposal guidelines.

#### 3. POST /api/guidelines

   Description: Adds a new disposal guideline.
   Request Body: JSON with guideline and categoryId.
   Response: 201 Created with the created disposal guideline.

#### 4. PUT /api/guidelines/{id}

   Description: Updates an existing disposal guideline.
   Request Body: JSON with guideline and categoryId.
   Response: 200 OK with updated guideline details.

#### 5. DELETE /api/guidelines/{id}

   Description: Deletes a disposal guideline by its id.
   Response: 200 OK if deletion is successful.

### Recycling Tips
#### 1. GET /api/tips

   Description: Retrieves all recycling tips.
   Response: 200 OK with a list of recycling tips.

#### 2. GET /api/tips/category/{categoryId}

   Description: Retrieves recycling tips for a specific waste category.
   Response: 200 OK with recycling tips.

#### 3. GET /api/tips/{id}

   Description: Retrieves a specific recycling tip by id.
   Response: 200 OK with recycling tip details.

#### 4. POST /api/tips

   Description: Adds a new recycling tip.
   Request Body: JSON with tip and categoryId.
   Response: 201 Created with the created recycling tip.

#### 5. PUT /api/tips/{id}

   Description: Updates an existing recycling tip.
   Request Body: JSON with tip and categoryId.
   Response: 200 OK with updated recycling tip details.

#### 6. DELETE /api/tips/{id}

   Description: Deletes a recycling tip by its id.
   Response: 200 OK if deletion is successful.
