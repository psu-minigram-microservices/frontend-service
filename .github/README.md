<div align="center">
 <h1>Minigram Frontend Service</h1>
 <div>
  One-page front-end for Minigram built with Spring Framework 7.
 </div>
</div>

### Prerequisites

- Docker & Docker Compose

### Configuration

Copy the environment template and fill in the required values:

```bash
cp .env.example .env
```

| Variable                | Description          | Default |
|-------------------------|----------------------|---------|
| `FRONTEND_SERVICE_PORT` | Service exposed port | `8080`  |

### Run service

```bash
docker compose up --build
```

### Stop service

```bash
docker compose down
```
