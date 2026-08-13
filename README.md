# MicroPool

A small 8-ball pool system built as three Spring Boot microservices:

- **match-service** (port `8081`) — owns match state, turns and 8-ball rules
- **shot-service** (port `8082`) — turns angle/power/spin into a deterministic shot result
- **league-service** (port `8083`) — persists completed results and exposes a leaderboard (backed by PostgreSQL)

## Running the system

```bash
docker compose up --build
```

This starts `postgres`, `shot-service`, `league-service` and `match-service` on the shared Compose network. Services address each other by container name (e.g. `http://shot-service:8080`), not `localhost`.

## Health checks

```bash
curl http://localhost:8081/actuator/health
curl http://localhost:8082/actuator/health
curl http://localhost:8083/actuator/health
```

## Playing a match

### Create a match (Match Service)

```bash
curl -X POST http://localhost:8081/matches \
  -H "Content-Type: application/json" \
  -d "{\"player1\":\"alice\",\"player2\":\"bob\"}"
```

Copy the `matchId` from the response for the calls below.

### Read match state

```bash
curl http://localhost:8081/matches/<matchId>
```

### Take a shot

Example shot: angle=37, power=72:

```bash
curl -X POST http://localhost:8081/matches/<matchId>/shots \
  -H "Content-Type: application/json" \
  -d "{\"player\":\"alice\",\"angle\":37,\"power\":72,\"spin\":0}"
```

### Call Shot Service directly

Useful for checking determinism in isolation, bypassing Match Service:

```bash
curl -X POST http://localhost:8082/shots \
  -H "Content-Type: application/json" \
  -d "{\"angle\":37,\"power\":72,\"spin\":0}"
```

### League Service

```bash
curl -X POST http://localhost:8083/results \
  -H "Content-Type: application/json" \
  -d "{\"matchId\":\"<matchId>\",\"winner\":\"alice\",\"loser\":\"bob\"}"

curl http://localhost:8083/leaderboard
```

> **Windows PowerShell note:** `curl` is often aliased to `Invoke-WebRequest`, which does not accept `-X`/`-d` the same way. Run these from Git Bash/WSL, or use `curl.exe` explicitly for real curl behaviour.

## Running tests

```bash
mvn test
```

Runs unit tests, Spring integration tests, and the Testcontainers-based persistence test for League Service (requires Docker running locally).
