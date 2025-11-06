# Flower Store Demo

Spring Boot application with PostgreSQL backend exposing REST APIs for browsing flowers, simulating deliveries/payments, and creating demo orders.

## Quick Start

### Using Docker Compose (Recommended)
```bash
cd lab7
docker-compose up -d  # Start PostgreSQL
./mvnw spring-boot:run
```

### Manual PostgreSQL Setup
```bash
docker run --name oop-course \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=flowerstore \
  -d -p 5432:5432 postgres
cd lab7
./mvnw spring-boot:run
```

Application listens on `http://localhost:8080`.

## API Endpoints

| Method | Path | Description |
| ------ | ---- | ----------- |
| GET | `/api/v1/flower` | Get all flowers from database |
| POST | `/api/v1/flower` | Create a new flower |
| GET | `/api/v1/flower/type/{type}` | Get first flower of specific type |
| GET | `/api/v1/delivery` | List available delivery methods |
| GET | `/api/v1/delivery/simulate` | Simulate delivery |
| GET | `/api/v1/payment` | List available payment methods |
| GET | `/api/v1/payment/simulate` | Simulate payment |
| POST | `/api/orders` | Process an order |

## Testing with test.http

Create a `test.http` file in your project root with these requests:

```http
### Get all flowers
GET http://localhost:8080/api/v1/flower

### Get flower by type
GET http://localhost:8080/api/v1/flower/type/ROSE

### Create a new flower
POST http://localhost:8080/api/v1/flower
Content-Type: application/json

{
  "type": "ROSE",
  "color": "RED",
  "sepalLength": 2.5,
  "price": 12.99
}

### List delivery methods
GET http://localhost:8080/api/v1/delivery

### Simulate delivery
GET http://localhost:8080/api/v1/delivery/simulate?method=dhl&itemType=ROSE

### List payment methods
GET http://localhost:8080/api/v1/payment

### Simulate payment
GET http://localhost:8080/api/v1/payment/simulate?method=credit-card&amount=100

### Create an order
POST http://localhost:8080/api/orders
Content-Type: application/json

{
  "items": ["ROSE", "TULIP"],
  "payment": "credit-card",
  "delivery": "dhl"
}
```

**Using test.http in IntelliJ IDEA / VS Code:**
- IntelliJ IDEA: Open the file and click the green play button next to each request
- VS Code: Install the "REST Client" extension by Huachao Mao, then click "Send Request" above each `###` section

## Example curl Commands

```bash
# Get all flowers
curl http://localhost:8080/api/v1/flower

# Create a flower
curl -X POST http://localhost:8080/api/v1/flower \
  -H "Content-Type: application/json" \
  -d '{"type":"ROSE","color":"RED","sepalLength":2.5,"price":12.99}'

# Simulate delivery
curl "http://localhost:8080/api/v1/delivery/simulate?method=dhl&itemType=ROSE"

# Process order
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"items":["ROSE","TULIP"],"payment":"paypal","delivery":"post"}'
```

## Running Tests

```bash
cd lab7
./mvnw test              # Run all tests
./mvnw checkstyle:check  # Check code style
```

## Architecture

- **API Layer**: REST controllers in `com.web.lab7.controller`
- **Service Layer**: Business logic in `com.web.lab7.service`
- **Data Access Layer**: JPA repositories in `com.web.lab7.repository`
- **Database**: PostgreSQL with JPA/Hibernate
- **Patterns**: Strategy (Payment/Delivery), Decorator (Item decorators)
