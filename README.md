# Golf Club Membership & Tournament API

## Overview
This project provides a REST API for managing golf club members and tournaments, with support for member-tournament relationships. The system is built with Spring Boot and uses MySQL as its database.

## API Endpoints

### Member Endpoints
- `GET /members` - Get all members
- `POST /member` - Create a new member
- `GET /member/{id}` - Get a member by ID
- `PUT /member/{id}` - Update a member
- `DELETE /member/{id}` - Delete a member

#### Member Search API
- `GET /member_search` - Search members with parameters:
    - `name` - Search by name (case-insensitive partial match)
    - `phone` - Exact phone number match
    - `email` - Exact email match
    - `start_date` - Members who joined on specific date (ISO format: YYYY-MM-DD)
    - `duration` - Members with specific membership duration (in months)

### Tournament Endpoints
- `GET /tournaments` - Get all tournaments
- `POST /tournament` - Create a new tournament
- `GET /tournament/{id}` - Get a tournament by ID
- `PUT /tournament/{id}` - Update a tournament (including participating members)
- `DELETE /tournament/{id}` - Delete a tournament

#### Tournament Search API
- `GET /tournament_search` - Search tournaments with parameters:
    - `location` - Search by location (case-insensitive partial match)
    - `start_date` - Tournaments starting on specific date (ISO format)
    - `end_date` - Tournaments between start and end dates (requires both)

## Running with Docker

### Prerequisites
- Docker installed
- Docker Compose installed

### Setup Instructions

1. Clone the repository:
    ```bash
    git clone <url>
   cd DevOpsSDAT_QAP2
   ```
2. **Build the application**:
   ```bash
   mvn clean package
   ```
   Or you can manually build the package using "Maven > Lifecycle > package" from the Maven tool menu.

3. **Build and start the containers**:
    ```bash
    docker-compose up --build
   ```
   
4. **Verify the containers are running**:
    ```bash
    docker ps
   ```

5. **Access the API**:
    - Use your preferred tool (e.g., Postman)
    - The API will be available at `http://localhost:8080`
    - MySQL is exposed on port 3307 (for local access)

## Sample API Requests

### Create a Member
```bash
   {
        "name": "John Doe",
        "address": "123 Golf St",
        "email": "john@golf.com",
        "phone": "555-1234",
        "startDate": "2023-01-15",
        "membershipDuration": 12
    }
```
### Create a Tournament
```bash
   {
        "startDate": "2024-06-15",
        "endDate": "2024-06-18",
        "location": "Newfoundland",
        "entryFee": 250.00,
        "cashPrize": 10000.00,
        "participatingMembers": [
            {"id": 1},
            {"id": 2}
        ]
    }
```

### Search Member by Name
```bash
    http://localhost:8080/member_search?name=doe
```

### Search Tournament by Start Date and Location
```bash
    http://localhost:8080/tournament_search?start_date=2024-06-01&location=Newfoundland
```