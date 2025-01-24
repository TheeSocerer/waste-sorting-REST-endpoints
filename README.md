# waste-sorting-REST-endpoints
### Background:
    Enviro365, a leading environmental solutions company, is developing a waste sorting
    mobile application aimed at promoting sustainable waste management practices. The
    application will serve as an educational tool for individuals and communities, providing
    guidance on proper waste disposal techniques and encouraging recycling habits. As a
    recent graduate developer at Enviro365, you've been tasked with implementing REST
    endpoints to enable communication between the frontend and backend systems of the
    application.
### Scenario:
    You are part of a dedicated team working on the development of the waste sorting mobile
    application at Enviro365. Your team is passionate about environmental sustainability and is
    committed to creating a user-friendly and informative application that empowers users to
    make environmentally conscious decisions. The application will feature various
    functionalities, including waste category lookup, disposal guidelines retrieval, and recycling
    tips display. Your role is to design and implement REST endpoints using Spring Boot that
    facilitate data exchange between the frontend mobile application and the backend server.

## API endpoints:

### Waste Categories:
    GET /api/categories - Get all waste categories.
    GET /api/categories/{id} - Get details of a specific waste category.
    POST /api/categories - Add a new waste category.
    PUT /api/categories/{id} - Update an existing waste category.
    DELETE /api/categories/{id} - Delete a waste category.
### Disposal Guidelines:
    GET /api/guidelines - Get all disposal guidelines.
    GET /api/guidelines/category/{categoryId} - Get guidelines for a specific category.
    POST /api/guidelines - Add new disposal guidelines.
    PUT /api/guidelines/{id} - Update an existing disposal guidelines.
    DELETE /api/guidelines/{id} - Delete a disposal guidelines.
### Recycling Tips:
    GET /api/tips - Get general recycling tips.
    GET /api/tips/category/{categoryId} - Get tips for a specific category.
    GET /api/tips/{Id} - Get tips for recycling tips.
    POST /api/tips - Add a new recycling tip.
    PUT /api/tips/{id} - Update an existing recycling tips.
    DELETE /api/tips/{id} - Delete a recycling tips.