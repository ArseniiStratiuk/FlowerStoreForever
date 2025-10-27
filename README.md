# Flower Store Demo

Simple Spring Boot application exposing REST APIs for browsing flowers, simulating deliveries/payments, and creating demo orders.

## Quick Start

```bash
cd lab7
./mvnw spring-boot:run
```

Application listens on `http://localhost:8080`.

## API Cheatsheet

| Method | Path | Description |
| ------ | ---- | ----------- |
| GET | `/api/demo/messages` | Sample text to verify server is running |
| GET | `/api/flowers` | Flower catalogue (name, color, price, etc.) |
| GET | `/api/deliveries` | Available delivery strategy names |
| GET | `/api/deliveries/simulate?method=post&itemType=ROSE` | Preview delivery message |
| GET | `/api/payments` | Payment strategy names |
| GET | `/api/payments/simulate?method=credit-card&amount=100` | Simulated payment attempt |
| POST | `/api/orders` | Process an order (`items`, `payment`, `delivery`) |

### Example Order Payload

```json
{
  "items": ["ROSE", "ROMASHKA"],
  "payment": "credit-card",
  "delivery": "dhl"
}
```

### Quick curl examples (WSL/Linux/macOS)

```bash
curl http://localhost:8080/api/flowers
curl "http://localhost:8080/api/deliveries/simulate?method=dhl&itemType=tulip"
curl -X POST -H "Content-Type: application/json" \
  -d '{"items":["ROSE"],"payment":"paypal","delivery":"post"}' \
  http://localhost:8080/api/orders
```
